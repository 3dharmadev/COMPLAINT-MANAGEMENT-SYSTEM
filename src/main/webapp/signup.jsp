<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" errorPage="Error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>User Management Application</title>
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
<title>sign up</title>
</head>
  <h1> Sign up as New Employee</h1>
    <form action="signupemp" method="post">
   
   Enter username: <input type="text" name="username" placeholder="must have valid email" >  
   Enter password: <input type="password" name="password" placeholder="Type in Number" >  
   
   <input type="submit" style="background-color: blue; color:white;" value="signup">
     </form>
     <h1><a href="home.jsp">Home</a></h1>
      <h1><a href="loginemployee.jsp">Login</a></h1>
     
</html>