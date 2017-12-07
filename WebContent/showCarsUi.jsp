<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List,java.io.BufferedReader,java.io.FileReader,java.nio.file.Paths,java.sql.Connection,java.sql.Date,java.sql.DriverManager,java.sql.PreparedStatement,java.sql.ResultSet,java.sql.SQLException,java.sql.Statement" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Available cars</title>
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
	 <div class="container" id="compact">
	<table class="table">
	<caption><h3>Compacts</h3></caption>
  <tr>
    <th>Id</th>
    <th>Type</th>
    <th>Model</th>
    <th>Year</th>
    <th>Rent Type</th>
    <th>Period</th>
    <th>Customer Id</th>
    <th>Book</th>
  </tr>
  <% 
//JDBC driver and database URL Localhost
	 final String JDBC_Driver="com.mysql.jdbc.Driver";
	 final String Db_Url="jdbc:mysql://localhost:3306/carRental";
		
	//username and passsword localhost
	 final String user="root";
	 final String pwd="nk281992";
		
	//connection and statement
	Connection con=null,conFetch=null;
	Statement stmt=null;
	ResultSet res = null;
	Class.forName(JDBC_Driver).newInstance();
	con=DriverManager.getConnection(Db_Url, user, pwd);
	conFetch=DriverManager.getConnection(Db_Url, user, pwd);
	String query="select vehicleId,cartype,model,vehicleyear from car where vehicleId not in (\n"
			+"select vehicleId from dailyRental where returnDate > CURDATE()\n" 
			+"UNION\n" 
			+"select vehicleId from weeklyRental where returnDate > CURDATE()\n"
			+") order by carType\n";
	PreparedStatement pr =con.prepareStatement(query);
	res=pr.executeQuery();
	while(res.next()){
  %>
  <tr>
  <form action="RentCar" method="POST">
    <td><%=res.getString(1) %></td>
    <td><%=res.getString(2) %></td>
    <td><%=res.getString(3) %></td>
    <td><%=res.getString(4) %></td>
    <input type="hidden" name="vehicleId" value="<%=res.getString(1)%>">
    <input type="hidden" name="vehicleType" value="<%=res.getString(2)%>">
    <input type="hidden" name="vehicleModel" value="<%=res.getString(3)%>">
    <input type="hidden" name="vehicleYear" value="<%=res.getString(4)%>">
    <td><select name="carRentType">
	  <option value="dailyrental">Daily</option>
	  <option value="weeklyrental">Weekly</option>
	</select></td>
    <td><input type="text" name="period"></td>
    <td><input type="text" name="customerId"></td>
    <td><input type="submit" value="Book"/></td>
  </form>
  </tr>
  <%
	}
  %>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
</body>
</html>