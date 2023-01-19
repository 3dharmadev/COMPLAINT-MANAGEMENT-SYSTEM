<%@page import="com.queryresolvingsystem.bean.RaisedComplain"%>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
		 <%@ include file="employeenavbar.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<style>
    #f{
         display: grid;
         justify-content: center;
         row-gap: 20px;
         margin-top: 100px;
}
   #f>input{
    height: 27px;
    width: 400px;
    border-radius: 20px;
  }
  input::placeholder {
    font-size: 22px;
    text-align: center;
  }
  body{
    background-color:rgb(6, 186, 251) ;
  }

     
</style>
<title>Assign to engineer</title>
</head>
 
    <form id="f" action="checkstatus" method="post">
        <input type="number" name="statusId" placeholder="Enter your complain Id">
        <input type="submit" style="background-color: rgb(13, 0, 96); color: azure;" value="search">
    </form>
    

</html>