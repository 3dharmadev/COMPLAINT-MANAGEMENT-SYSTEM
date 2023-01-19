
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login after</title>
 <style type="text/css">
 
 div{
 display: grid;
 justify-content: center;
 }
 div>h4{
 display: flex;
 justify-content: center;
 margin:auto;
 padding:auto;
 margin-top: 20px;
 }
 div>h4>form{
 margin-top: 30px;
 }
 </style>
</head>
<body>
   
				
				   <%

   response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
   
   response.setHeader("Pragma", "no-cache"); //HTTP 1.0
   
   response.setHeader("Expires", "0"); //Proxy server


   if(session.getAttribute("username")==null) response.sendRedirect("HOD_login.jsp");
   
%>
			  
			  
			  <div>
			    <h4>1. Want to see number of complain raised by employees :<a href="<%=request.getContextPath()%>/complainlist" class="nav-link">Number Of Complains</a></h4>  
					<h4>2. Want to see number of engineers working on employee query :  <a href="<%=request.getContextPath()%>/engineerdata" class="nav-link">Engineers list</a>  </h4> 
		          <h4>3. Want to add a new engineer :<a href="registerengineer.jsp" class="nav-link">Register New Engineer</a>   </h4>
		
			  </div>
</body>
</html>