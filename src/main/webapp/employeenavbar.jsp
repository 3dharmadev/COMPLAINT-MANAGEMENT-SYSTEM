<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 

<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<style type="text/css">
       #nav{
       background: #160046;
       }
       h1{
       color: #e7e7e7;
       }
       
     #form1>input{
        background-color: rgb(239, 0, 0);
        color: rgb(255, 255, 255);
         
        font-size: 20px;
     }
	</style>
</head>
<body>

  <%

   response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
   
   response.setHeader("Pragma", "no-cache");
   
   response.setHeader("Expires", "0");  


   if(session.getAttribute("Empusername")==null) response.sendRedirect("loginemployee.jsp");
   
%>
	<header>
		<nav id="nav" class="navbar navbar-expand-md navbar-dark">
			<div>
				<a class="navbar-brand"> <h1 id="h1" >QUERY RESOLVING SYSTEM</h1> </a>

			</div>

			<ul class="navbar-nav">
				<li><a href="registerComplain.jsp"	class="nav-link">register a complain</a></li>
				<li> <a href="checkcstatus.jsp" class="nav-link">Check Status</a> </li>
				<li><a href="<%=request.getContextPath()%>/allrcomplains"
					class="nav-link">All Raised Complains</a></li>
				<li> <a href="changeEmpPassword.jsp" class="nav-link">Change Password</a> </li>
			  
			  <form id="form1" action="emplogout"><input type="submit"  class="nav-link" value="logout"></form>
			
			</ul>
		</nav>
		 
	</header>
	<br>

</body>
</html>