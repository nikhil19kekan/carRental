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
<div class="container">
			<form action="GetCustomer" method = "POST">
					<label class="col-form-label" for="formGroupExampleInput">Customer Name:</label>
		        	<input type = "text" name = "name">
		         	<br/><br/>
		         	<label class="col-form-label" for="formGroupExampleInput">Phone:</label>
		         	<input type="number" name="phone" /><br/><br/>
		         	<label class="col-form-label" for="formGroupExampleInput">Address:</label>
		         	<input type="text" name="address" /><br/><br/>
		         	<label class="col-form-label" for="formGroupExampleInput">Customer Type:</label>
		         		<select name="customerType">
				  			<option value="individual">Individual</option>
				  			<option value="Company">Rental Company</option>
						</select><br/><br/>
					<input type="submit" value="Submit" />
		    </form>
</div>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>