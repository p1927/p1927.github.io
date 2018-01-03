<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<link rel='stylesheet' type='text/css' href='css/style.css'>
</head>
<body>
<div class="content">
	<c:if test="${sessionScope.user ne null}">
		<h1 class="myHeader" align="center">AirSpace Telecomm</h1>
		<h2 align="center">Pay Your Bill Here</h2>
		<div class="box1"> <div  align="center" style="font-size:20px">Payment for the mobile number ${sessionScope.user.mobileNo}</div> 
		<br/>
		<form action = "Result.obj" method = "post">
		<table align="center" >
				<tr>
					<td>Enter the amount to Pay</td>
					<td><input type = "text" name="result" pattern="[0-9]+" min="1" required/></td>
				</tr>		
				<tr>
					<td colspan="2"><input type="submit" value="Pay" class="text-center btn-block btn btn-info text-right"></td>
				</tr>
			</table>
		</form>
</div>
	</c:if>
</div>
</body>
</html>