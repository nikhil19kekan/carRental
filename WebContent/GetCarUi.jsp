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
      <form action="GetCar" method="POST">
        Existing Owner: <input id="isAlreadyOwner" type="checkbox" name="isExistingOwner" checked onClick="vis();">
		<span id="ownerid">Owner Id: <input type="text" name="ownerId"></span><br/><br/>
        <p id="ownerDetails" style="visibility:hidden">Owner Name:<input type="text" name="ownerName"><br/><br/>
        Owner Type:<select name="ownerType" >
		    <option value="individual">Individual</option>
		    <option value="rental Company">Rental Company</option>
		    <option value="bank">Bank</option>
		</select><br/><br/>
		Phone: <input type="number" name="phone"><br/><br/>
		Address: <input type="text" name="address"><br/><br/></p>
		Car Type: <select name="carType">
		    <option value="Compact">Compact</option>
		    <option value="Medium">Medium</option>
		    <option value="Van">Van</option>
		    <option value="Suv">SUV</option>
		    <option value="Truck">Truck</option>
		    <option value="Large">Large</option>
		</select><br/><br/>
		Car Model: <input type="text" name="model"><br/><br/>
		Car Year: <input type="text" name="year"><br/><br/>
		<input type="submit" value="Submit"/>
</form>
</div>
					<!--  custom scripts   -->
	 <script language="javascript" type="text/javascript">
	 	function vis() {
 				
		    	var input = document.getElementById("isAlreadyOwner");
	            var isChecked = input.checked;
	            if(!isChecked){
	            	document.getElementById("ownerid").style.visibility="hidden";
	            	document.getElementById("ownerDetails").style.visibility="visible";
	            }else{
	            	document.getElementById("ownerid").style.visibility="visible";
	            	document.getElementById("ownerDetails").style.visibility="hidden";
	            }
		}
	 </script>
	 				<!--  custom scripts   -->
 <!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
</body>
</html>