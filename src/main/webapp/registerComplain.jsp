
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
		 <meta charset="ISO-8859-1">
<%@ include file="employeenavbar.jsp" %> 

<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<style>
h1{
text-align: center;
}
     #f2{
         display: grid;
         justify-content: center;
         row-gap: 20px;
         margin-top: 100px;
}
  #f2>input{
    height: 27px;
    width: 400px;
    border-radius: 20px;
  }
    #f2>option{
   margin: 0px 50px 0px 50px;
  }
  input::placeholder {
    font-size: 22px;
   
    text-align: center;
  }
  body{
    background-color:rgb(255, 255, 17) ;
  }

     
</style>
<body>
<h1>Register a new complain</h1>
<form id="f2" action="registerComplain" method="post">
    <input type="text" name="details" placeholder="explain your query in short">
    

   <select name="Etype" >
   <option  style="color:dimgrey">choose department</option>
  <option value="HARDWARE" style=" font-weight: 1000; font-size: 30px;">HARDWARE</option>
  <option value="SOFTWARE"style=" font-weight: 1000; font-size: 30px; "> SOFTWARE</option>
</select>
     <input type="submit" style="background-color: rgb(13, 0, 96); color: azure;" value="Register">

</form>

</body>
</html>