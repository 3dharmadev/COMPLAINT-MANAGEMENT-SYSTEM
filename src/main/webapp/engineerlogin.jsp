<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>Engineer login</title>
<meta charset="ISO-8859-1">
<style>
    form{
         display: grid;
         justify-content: center;
         row-gap: 20px;
         margin-top: 100px;
}
  form>input{
    height: 27px;
    width: 400px;
  }
  input::placeholder {
    font-size: 22px;
    text-align: center;
  }
  body{
    background-color:rgb(6, 186, 251) ;
  }
  h1{
  margin-top:50px;
  text-align: center;
  color:white;
  
  }

     
</style>
<title>login</title>
</head>
  <h1> Engineer login</h1>
    <form action="engineerlogin" method="post">
   
   Enter username: <input type="text" name="username" placeholder="Enter your email" >  
   Enter password: <input type="password" name="password" placeholder="Type in Number">  
   
   <input type="submit" style="background-color: blue; color:white;" value="login">
     </form>
     <h1><a href="home.jsp">Home</a></h1>
</html>