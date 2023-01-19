
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="engineernavbar.jsp" %>
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
<title>Update status</title>
</head>

    <form id="f" action="updateStatus" method="post">
        <input type="hidden" value=<%=request.getParameter("complainId")%> name="complainId" placeholder="Enter the complain Id">
        <input type="text" name="status" placeholder="status message">
        <input type="submit" style="background-color: rgb(13, 0, 96); color: azure;" value="UPDATE STATUS">
    </form>
</html>