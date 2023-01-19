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
			<h3 class="text-center">List of new Queries</h3>
			<hr>
			<div class="container text-left">


			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
					<th style="background-color: wheat">Query Id</th>
					 <th style="background-color: wheat">Query Type</th>
					 <th style="background-color: silver; color: #e7e7e7">Query Summary</th>
					 <th style="background-color: blue;color: antiquewhite">Querier</th>
					 <th style="background-color: blue;color: antiquewhite">status</th>
				

					</tr>
				</thead>
				<tbody>

    <c:forEach var="task" items="${allcomplains}">
                    <tr>
                     <td>  <c:out value="${task.complainId}"/></td>
					 <td>  <c:out value="${task.complainType}"/></td>
				   	<td><c:out value="${task.complainDetails}"/></td>
							<td>  <c:out value="${task.raisedBy}"/></td>
							   <td id="status"> <c:out value="${task.status != null ? query.status: 'NOT RESOLVED'}"/></td>
				 
						</tr>
    </c:forEach>


    </tbody>

			</table>
		</div>
	</div>
</body>
</html>