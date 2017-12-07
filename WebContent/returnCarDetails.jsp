<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Insert title here</title>
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
<h2>Car Return</h2>
<div class="container">
	<table class="table">
	<tr>
	<td>Vehicle ID</td>
	<td>start Date</td>
	<td>Return Date</td>
	<td>Rental Type</td>
	<td>Period</td>
	<td>Amount Due</td>
	<td>Vehicle Returned?</td>
	</tr>
	<%
	 final String JDBC_Driver="com.mysql.jdbc.Driver";
	 final String Db_Url="jdbc:mysql://localhost:3306/carRental";
		
	//username and passsword localhost
	 final String user="root";
	 final String pwd="nk281992";
	 Class.forName(JDBC_Driver).newInstance();
	 Connection con = DriverManager.getConnection(Db_Url,user,pwd);
	 int customerId=Integer.parseInt(request.getParameter("customerId"));
	 String queryForReturn="select vehicleId,amountDue,startDate,returnDate,rtype,period,returned from(\n"
			 +"select vehicleId,amountDue,startDate,returnDate,'dailyRental' as rtype,noOfDays as period,returned from dailyRental\n"
			 +"where Idno="+customerId+"\n"
			 +"UNION ALL\n"
			 +"select vehicleId,amountDue,startDate,returnDate,'weeklyRental' as rtype,noOfWeeks as period ,returned from weeklyRental\n"
			 +"where Idno="+customerId+"\n"
			 +")as T\n";
	PreparedStatement pr = con.prepareStatement(queryForReturn);
	ResultSet set=pr.executeQuery();
	while(set.next()){
	%>
	<tr>
	<td><%=set.getInt("vehicleId") %></td>
	<td><%=set.getString("startDate") %></td>
	<td><%=set.getString("returnDate") %></td>
	<td><%=set.getString("rtype") %></td>
	<td><%=set.getInt("period") %></td>
	<td><%=set.getDouble("amountDue") %></td>
	<td><%=set.getString("returned") %>
		<% if("no".equals(set.getString("returned"))){%>
			<form action="returnCarTransaction.jsp" method="POST">
				<input type="hidden" name="vehicleId" value=<%=set.getInt("vehicleId") %>>
				<input type="hidden" name="rtype" value=<%=set.getString("rtype") %>>
				<input type="submit" value="Return">
			</form>
		<% }%>
	</td>
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