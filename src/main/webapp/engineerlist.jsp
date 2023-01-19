 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<style type="text/css">
  #delete{
	  background-color: #f14646;
	  color: aliceblue;

  }
  #delete>a{
	  color: white;
  }
  #delete:hover{
	  background-color: white;
  }
  #delete>a:hover{
	  color: #f14646;
  }
	</style>
</head>
<body>


	<div class="row">


		<div class="container">
			<h3 class="text-center">List of Engineers</h3>
			<hr>
			<div class="container text-left">


			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th style="background-color: wheat">Email</th>
						<th style="background-color: blue; color: #e7e7e7">Password</th>
						<th style="background-color: blue;color: antiquewhite">Engineer Department</th>
						 <th style="background-color: red;color: white">Delete Engineer</th>

					</tr>
				</thead>
				<tbody>

    <c:forEach var="query" items="${engineer_list}">
                    <tr>
					 <td>  <c:out value="${query.email}"/></td>
							<td>     <c:out value="${query.password}"/></td>
							<td>  <c:out value="${query.type}"/></td>

							<td id="delete"> <a
				 	href="delete?email=<c:out value='${query.email}' />">DELETE ENGINEER</a></td>
						</tr>
    </c:forEach>


    </tbody>

			</table>
		</div>
	</div>
</body>
</html>
