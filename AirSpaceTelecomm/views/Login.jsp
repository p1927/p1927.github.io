<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AirSpace Telecomm</title>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<link rel='stylesheet' type='text/css' href='views/css/stylelogin.css'>

</head>

<body>
	<div >
	<h1 class="myHeader" align="center">AirSpace Telecomm</h1>
	</div>
	<c:url var="myAction" value="Login.obj"></c:url>
	<form id="form" action="${myAction}"  method="post" > 
	
	<div class="box">
      <h1 id="logintoregister">Login</h1>
    <div class="group">      
      <input id="una" class="inputMaterial" type="text" name="uname" oninput="" required>
      <span class="highlight"></span>
      <span class="bar"></span>
      <label>Username</label>
      </div>
	  <div class="group">      
      <input class="inputMaterial" type="password" name="pwd" id="password" required>
      <span class="highlight"></span>
      <span class="bar"></span>
      <label>Password</label>
      </div>
        	  <div class="group show">      
      <input class="inputMaterial" type="password" id="confirmPassword"  disabled required>
      <span class="highlight"></span>
      <span class="bar"></span>
      <label>Confirm Password</label>
      </div>
	  <div class="group show">      
      <input class="inputMaterial" type="text" name="name" id="name" oninput="validatePassword();" pattern="[A-Za-z][A-Za-z ]*" disabled required>
      <span class="highlight"></span>
      <span class="bar"></span>
      <label>Name</label>
      </div>
	  <div class="group show">      
      <input class="inputMaterial" type="text" pattern="[1-9][0-9]{9}" name="mobileno" id="mbno" disabled required>
      <span class="highlight"></span>
      <span class="bar"></span>
      <label>Mobile No.</label>
      </div>
      
      <button id="buttonlogintoregister" type="submit">Login</button>
      </form>
      <p id="plogintoregister">Not a registered user? </p>
      <p id="textchange" onclick="register();" > Register Here</p>
    </div>
    
///////////////////////////////////////////////////
		<script>
		
		
		<% if(session.getAttribute("info")=="NR"){%>
		document.querySelector("#plogintoregister").innerHTML="You are not registered with us. Please Register.";
		<%}%>
		////////////////////////
		<% if(session.getAttribute("info")=="WRONG PASS"){%>
		document.querySelector("#plogintoregister").innerHTML="The Password in incorrect. Please try again.";
		document.querySelector("#una").value=session.getAttribute("unret");
		<%}%>
		////////////////////////////
		var cont = 0;

function register(){
console.log("inside");
     cont++;
		
		if(cont==1){////////////////////////////////////USER CLICKS ON REGISTER
		 	$('.box').animate({height:'595px'}, 550);
			$('.show').css('display','block');
			$('#logintoregister').text('Registration');
			$('#buttonlogintoregister').text('Register');
			$('#plogintoregister').text("Already registered?");
			$('#textchange').text('Login');
			document.querySelector('#name').disabled=false;
			document.querySelector('#mbno').disabled=false;
			document.querySelector('#confirmPassword').disabled=false;
			
			$('#una').attr('oninput', 'dbcheck(this.value);');

			/////////////////////////////////////////////////
			
			
		}
		else
		{///////////////////////////////////////////USER CLICKS ON LOGIN 
			$('.show').css('display','none');
			$('.box').animate({height:'365px'}, 550);
			$('#logintoregister').text('Login');
			$('#buttonlogintoregister').text('Login');
			$('#plogintoregister').text("Not a registered User?");
			$('#textchange').text('Register');
			$('una').removeAttr('oninput');
			document.querySelector('#name').disabled=true;
			document.querySelector('#mbno').disabled=true;
			document.querySelector('#confirmPassword').disabled=true;
			cont = 0;
		}
}
		
		function validatePassword(){
			var a=document.querySelector('#password').value;
			var b=document.querySelector('#confirmPassword').value;
			if (a!=b){ $('#plogintoregister').text("Passwords do not match. Try Again"); 
			document.querySelector('#password').value="";
			document.querySelector('#confirmPassword').value='';}
			else $('#plogintoregister').text("Password looks secure!");
		}
		
		function dbcheck(uname)
		{ 
		   $.ajax({
				type: "POST",
				url:  'valid.obj',
				data: {userN:uname},
				success: function(resp) {
				if(resp=='1')
					$('#plogintoregister').text("The Username already exists. Try Again.");  
				else  $('#plogintoregister').text("Pretty Good."); 
				
						}
				}); 
			}
		</script>
</body>
</html>