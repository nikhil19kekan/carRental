<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Update rates</title>
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
final String JDBC_Driver="com.mysql.jdbc.Driver";
final String Db_Url="jdbc:mysql://localhost:3306/carRental";
	
//username and passsword localhost
final String user="root";
final String pwd="nk281992";
Class.forName(JDBC_Driver).newInstance();
if(request.getParameter("daily")!=null && request.getParameter("weekly")!=null && request.getParameter("carType")!=null){
	System.out.println("inside if");
	Connection con1=DriverManager.getConnection(Db_Url,user,pwd);
	String query="update carTypeRate set dailyRate="+request.getParameter("daily")+", weeklyRate="+request.getParameter("weekly")+" where carType='"+request.getParameter("carType")+"'";
	System.out.println(query);
	PreparedStatement pr =con1.prepareStatement(query);
	pr.execute();
	con1.close();
	response.sendRedirect("./updateRate.jsp");
}else{
	System.out.println("inside else");
%>
<div class="container">
	<table class="table">
		<tr>
		<th>Vehicle Type</th>
		<th>Daily Rate</th>
		<th>Weekly Rate</th>
		<th>Update</th>
		</tr>
		<%
		Connection con = DriverManager.getConnection(Db_Url,user,pwd);
		String query="select carType,dailyRate,weeklyRate from carTypeRate";
		PreparedStatement pr=con.prepareStatement(query);
		ResultSet set=pr.executeQuery();
		double vand=0,vanw=0,compactd=0,compactw=0,mediumd=0,mediumw=0,larged=0,largew=0,suvd=0,suvw=0,truckd=0,truckw=0;
		while(set.next()){
			if("van".equalsIgnoreCase(set.getString("carType"))){
				vand=set.getDouble("dailyrate");
				vanw=set.getDouble("weeklyRate");
			}else if("compact".equalsIgnoreCase(set.getString("carType"))){
				compactd=set.getDouble("dailyrate");
				compactw=set.getDouble("weeklyRate");
			}else if("medium".equalsIgnoreCase(set.getString("carType"))){
				mediumd=set.getDouble("dailyRate");
				mediumw=set.getDouble("weeklyrate");
			}else if("large".equalsIgnoreCase(set.getString("carType"))){
				larged=set.getDouble("dailyRate");
				largew=set.getDouble("weeklyRate");
			}else if("suv".equalsIgnoreCase(set.getString("carType"))){
				suvd=set.getDouble("dailyRate");
				suvw=set.getDouble("weeklyRate");
			}else if("truck".equalsIgnoreCase(set.getString("carType"))){
				truckd=set.getDouble("dailyRate");
				truckw=set.getDouble("weeklyRate");
				
			}
		}
		%>
		<tr>
		<form action="updateRate.jsp" method="POST">
			<td>Van</td>
			<td><input type="text" name="daily" value=<%=vand %>>$</td>
			<td><input type="text" name="weekly" value=<%=vanw %>>$</td>
			<input type="hidden" name="carType" value="van">
			<td><input type="submit" value="update"></td>
		</form>
		</tr>
		<tr>
		<form action="updateRate.jsp" method="POST">
			<td>Compact</td>
			<td><input type="text" name="daily" value=<%=compactd%>>$</td>
			<td><input type="text" name="weekly" value=<%=compactw %>>$</td>
			<input type="hidden" name="carType" value="compact">
			<td><input type="submit" value="update"></td>
		</form>
		</tr>
		<tr>
		<form action="updateRate.jsp" method="POST">
			<td>Medium</td>
			<td><input type="text" name="daily" value=<%=mediumd%>>$</td>
			<td><input type="text" name="weekly" value=<%=mediumw %>>$</td>
			<input type="hidden" name="carType" value="medium">
			<td><input type="submit" value="update"></td>
			<input>
		</form>
		</tr>
		<tr>
		<form action="updateRate.jsp" method="POST">
			<td>Large</td>
			<td><input type="text" name="daily" value=<%=larged%>>$</td>
			<td><input type="text" name="weekly" value=<%=largew %>>$</td>
			<input type="hidden" name="carType" value="large">
			<td><input type="submit" value="update"></td>
		</form>
		</tr>
		<tr>
		<form action="updateRate.jsp" method="POST">
			<td>Suv</td>
			<td><input type="text" name="daily" value=<%=suvd%>>$</td>
			<td><input type="text" name="weekly" value=<%=suvw %>>$</td>
			<input type="hidden" name="carType" value="suv">
			<td><input type="submit" value="update"></td>
		</form>
		</tr>
		<tr>
		<form action="updateRate.jsp" method="POST">
			<td>Truck</td>
			<td><input type="text" name="daily" value=<%=truckd %>>$</td>
			<td><input type="text" name="weekly" value=<%=truckw %>>$</td>
			<input type="hidden" name="carType" value="truck">
			<td><input type="submit" value="update"></td>
		</form>
		</tr>
	</table>
</div>

<% 
} 
%>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
</body>
</html>