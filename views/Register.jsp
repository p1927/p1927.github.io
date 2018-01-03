<%@ page language="java" import="com.cg.airspacetelecomm.dao.CustomerDAOImpl" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AirSpace Telecomm</title>
<link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<link rel='stylesheet' type='text/css' href='css/style1.css'>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

</head>

<body>
	<div >
	<h1 class="myHeader" align="center">AirSpace Telecomm</h1>
	<div id="box" align="center">
<c:url var="myAction" value="Register.obj"></c:url>
	<form action="${myAction}" method="post"  onSubmit="validatePassword"> 
		<div class="box1">	
		<br>
		<hr>
		<% if(session.getAttribute("info")=="NR"){%>
			<p>You are not registered with us. Please Register.</p>
			<%}; %>
<table >
				<h2 align="center">New User Registration Form</h2>
<tr>
					<td align="left">Enter Your name</td>
					<td><input type="text" name="name" pattern="[A-Za-z][A-Za-z ]*"  value="Pratyush Mishra"required/></td>
				</tr> 

				<tr>
					<td align="left">Mobile Number</td>
					<td><input type="text" pattern="[1-9][0-9]{9}" name="mobileno" value= "9639217588" required/></td> 
				</tr>
		
				<tr>
					<td align="left">Username</td>
					<td><input type="text" name="uname" oninput="dbcheck(this.value);" required/></td>
				</tr>
				<tr><td><div id="message"></div></td>
</tr>
				<tr>
					<td align="left">Password</td>
					<td><input type="password" id="password" name="pwd" required/></td> 
				</tr>
		
				<tr>
					<td align="right">Reenter Password</td>
					<td><input type="password" id="confirmPassword" required/></td> 
				</tr>
		
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Register" class="text-center btn-block btn btn-info text-right"></input></td>
				</tr>
				
				
				
			 </table></div>
		</form>
		
<div id="msg" ></div>
	</div>
	</div>
	 <script>
function dbcheck(uname)
{ 
   $.ajax({
		type: "POST",
		url:  'valid.obj',
		data: {userN:uname},
		success: function(resp) {
		if(resp=='1')
		$('#message').text("The Username already exists. Try Again.");  
		else  $('#message').text("Pretty Good."); 
		
				}
		}); 
	

	 
	};

</script>  
</body>
</html>