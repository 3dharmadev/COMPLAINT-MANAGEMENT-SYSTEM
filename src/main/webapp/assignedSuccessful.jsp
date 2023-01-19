<%--
  Created by IntelliJ IDEA.
  User: devom
  Date: 27-12-2022
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SUCESS</title>
   <style type="text/css">
   h1{
  text-align: center;
  margin-top:300px; 
  color:white;
   }
   body{
   background-color:rgb(1, 30, 253) ;
   }
   </style>
</head>
<body>


<%

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1

    response.setHeader("Pragma", "no-cache"); //HTTP 1.0

    response.setHeader("Expires", "0"); //Proxy server


    if(session.getAttribute("username")==null) response.sendRedirect("ErrorAssignTask.jsp");

%>

 <h1> Task Successfully assigned to  ${ username} ✔ </h1>
  




</body>
</html>
