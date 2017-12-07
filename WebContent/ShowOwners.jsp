<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List,java.io.BufferedReader,java.io.FileReader,java.nio.file.Paths,java.sql.Connection,java.sql.Date,java.sql.DriverManager,java.sql.PreparedStatement,java.sql.ResultSet,java.sql.SQLException,java.sql.Statement" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Customers</title>
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
<div class="container">
	<table class="table">
	<caption><h3>Customers</h3></caption>
  <tr>
    <th>OwnerId</th>
    <th>Owner Type</th>
    <th>Name</th>
    <th>Phone</th>
    <th>Address</th>
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
	String query="select ownerId,ownerType,name,phone,address from owner";
	PreparedStatement pr =con.prepareStatement(query);
	res=pr.executeQuery();
	while(res.next()){
  %>
  <tr>
    <td><%=res.getString(1) %></td>
    <td><%=res.getString(2) %></td>
    <td><%=res.getString(3) %></td>
    <td><%=res.getString(4) %></td>
    <td><%=res.getString(5) %></td>

  </tr>
  <%
	}
	con.close();
  %>
</table>

</div>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
</body>
</html>