<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<h1 class="myHeader" align="center">AirSpace Telecomm</h1>
	<h2 align="center">Welcome ${sessionScope.user.name}</h2>
<div class="box1"> <div  align="center" style="font-size:20px">	
The pending bill amount for your Mobile Number: ${sessionScope.user.mobileNo} is Rs.${sessionScope.user.bill}<br>
	<a href="Payment.obj?mobileNo=${sessionScope.user.mobileNo}"><input type = "submit" style="width:150px;" value="Pay Bill" class="text-center btn-block btn btn-info text-right" /></a>
</div> </div>
</div>
</body>
</html>