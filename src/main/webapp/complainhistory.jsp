<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="employeenavbar.jsp" %> 

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<style type="text/css">
       #nav{
       background:  #160046;

       }
       #nav>.navbar-brand{
       background: white;
     
       }
  

	</style>
</head>
<body>



	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Complains</h3>
			<hr>
			<div class="container text-left">


			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th style="background-color: antiquewhite">ComplainID</th>
						<th style="color: blue">Complain Type</th>
						<th style="color: blue">Complain Details</th>
						<th style="background-color: #06ffe5">Complain Status</th>
						<th style="background-color: #24ff00">QUERIER</th>
						<th style="background-color: #ffcf03">Assigned Engineer</th>
						<th style="background-color: #008cff" >Assign to Engineer</th>
					</tr>
				</thead>
				<tbody>



    <c:forEach var="query" items="${query_list}">

           <tr>
					 <td>  <c:out value="${query.complainId}"/></td>
							<td>   <c:out value="${query.complainType}"/></td>
							<td>  <c:out value="${query.complainDetails}"/></td>
			                <td id="status"> <c:out value="${query.status != null ? query.status: 'NOT RESOLVED'}"/></td>
							<td>     <c:out value="${query.raisedBy}"/></td>
							<td id="assignedE"> <c:out value="${query.solveBy != null ? query.solveBy: 'NOT ASSIGNED YET'}"/></td>

						 	<td> <a href="assignengineer.jsp?complainId=${query.complainId}">ASSIGN TASK</a></td>
						</tr>
    </c:forEach>


    </tbody>

			</table>
		</div>
	</div>
</body>
</html>