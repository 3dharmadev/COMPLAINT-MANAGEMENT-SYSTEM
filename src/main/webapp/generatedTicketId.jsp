<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
 h1{
  text-align: center;
  margin-top: 200px;
  
 }
 body{
 background-color: silver;
 }
</style>
</head>
<body>
<h1> Your generated Ticket ID is <% String message=(String) session.getAttribute("generatedCID"); 
     out.print(message);
     %> </h1>
    
  
</body>
</html>