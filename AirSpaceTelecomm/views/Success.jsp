<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
<link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<link rel='stylesheet' type='text/css' href='css/style.css'>
</head>
<body>


	<div id="content">
<h1 class="myHeader" align="center">AirSpace Telecomm</h1>
		<div class="box1" align="center"><c:if test="${sessionScope.balance <= 0}">
	Thank You for Paying your Bill & Bill Pay Date is <%
         Date date = new Date();
         out.print(date.toString());
      %><br>
      No balance amount 
</c:if>
<c:if test="${sessionScope.balance > 0}">
Thank You for Paying your Bill.<br>
Your Balance amount is ${sessionScope.balance}.<br>
 Bill Pay Date is <%
         Date date = new Date();
         out.print(date.toString());
      %><br>
</c:if>
<br/>
</div>
</div>
</body>
</html>