<%@page import="com.services.PrepareData"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List,java.io.BufferedReader,java.io.FileReader,java.nio.file.Paths,java.sql.Connection,java.sql.Date,java.sql.DriverManager,java.sql.PreparedStatement,java.sql.ResultSet,java.sql.SQLException,java.sql.Statement" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>owner Earning</title>
</head>
<body>
<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="./index.jsp">Car Rental</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li><a href="./index.jsp">Home</a></li>
	      <li><a href="./loadDataUi.jsp">Load Data</a></li>
	      <li><a href="./ShowCustomers.jsp">Show Customers</a></li>
	      <li><a href="./ShowOwners.jsp">Show Owners</a></li>
	      <li><a href="./ownerForReport.jsp">Show Owner Earning</a></li>
	      <li><a href="./GetCustomerUi.jsp">Add Customer</a></li>
	      <li><a href="./GetCarUi.jsp">Add a Car</a></li>
	      <li><a href="./showCarsUi.jsp">Show Cars</a></li>
	      <li><a href="./returnCar.jsp">Return Car</a></li>
	      <li><a href="./updateRate.jsp">Update Rental rates</a></li>
	    </ul>
	  </div>
</nav>
  <% 
  //fetching request parameter
  	int ownerId=Integer.parseInt(request.getParameter("ownerId"));
	PrepareData p = new PrepareData();
  	double amountCompact=p.getOwnerWeeklyEarning(ownerId, "compact");
  	double amountMedium=p.getOwnerWeeklyEarning(ownerId, "medium");
  	double amountLarge=p.getOwnerWeeklyEarning(ownerId, "large");
  	double amountSuv=p.getOwnerWeeklyEarning(ownerId, "suv");
  	double amountTruck=p.getOwnerWeeklyEarning(ownerId, "truck");
  	double amountVan=p.getOwnerWeeklyEarning(ownerId, "van");
  %>
  <div class="container">
	<table class="table">
	<h3></h3>
	<h2></h2>
	<h2></h2>
  <tr>
    <th>Compact</th>
    <th>Medium</th>
    <th>Large</th>
    <th>SUV</th>
    <th>Truck</th>
    <th>Van</th>
    <th>Total</th>
  </tr>
  <tr>
    <td><%=amountCompact%>$</td>
    <td><%=amountMedium%>$</td>
    <td><%=amountLarge%>$</td>
    <td><%=amountSuv%>$</td>
    <td><%=amountTruck%>$</td>
	<td><%=amountVan%>$</td>
	<td><%=amountCompact+amountLarge+amountMedium+amountSuv+amountTruck+amountVan%>$</td>
  </tr>

</table>

</div>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
</body>
</html>