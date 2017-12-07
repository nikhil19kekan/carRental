package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.classes.Car;

public class PrepareData {
	static int vehicleId=0;
	static int ownerId=0;
	static int customerId=0;
	//JDBC driver and database URL Localhost
	static final String JDBC_Driver="com.mysql.jdbc.Driver";
	static final String Db_Url="jdbc:mysql://localhost:3306/carRental";
	
	//username and passsword localhost
	static final String user="root";
	static final String pwd="nk281992";
	
	//connection and statement
	Connection con=null,tempcon=null;
	Statement stmt=null;
	ResultSet res = null;
	
	public PrepareData() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName(JDBC_Driver).newInstance();
	}
	private int generateVehicleId() {
			try {
				con = DriverManager.getConnection(Db_Url,user,pwd);
				String query="select max(vehicleId) from car";
				PreparedStatement pr = con.prepareStatement(query);
				ResultSet resultSet=pr.executeQuery();
				if(resultSet.first()) {
					vehicleId=resultSet.getInt(1);
				}
				vehicleId++;
				System.out.println("current vehicles id is :"+vehicleId);
				con.close();
			}catch(Exception e) {
				System.out.println("exception occurred:"+e.getMessage());
			}
			return vehicleId++;
	}
	private int generateOwnerId() {
			try {
				con = DriverManager.getConnection(Db_Url,user,pwd);
				System.out.println("fetching highest ownerId from db");
				String query="select max(ownerId) from owner";
				PreparedStatement pr = con.prepareStatement(query);
				ResultSet rs = pr.executeQuery();
				if(rs.first()) {
					ownerId = rs.getInt(1);
				}
				ownerId++;
				System.out.println("maximum ownerId from db is"+ownerId);
				con.close();
			}catch(Exception e) {
				System.out.println("Exception occurred:"+e.getMessage());
			}
	return ownerId++;
	}
	private int generateCustomerId() {
		if(customerId != 0) {
			System.out.println("customerId found zero so not fetching from DB");
			customerId++;
		}else {
			try {
				con = DriverManager.getConnection(Db_Url,user,pwd);
				System.out.println("Fetching highest customerId from db");
				String query = "select max(Idno) from customer";
				PreparedStatement pr = con.prepareStatement(query);
				ResultSet rs = pr.executeQuery();
				if(rs.first()) {
					customerId = rs.getInt(1);
				}
				customerId++;
				System.out.println("maximum customerId in db:"+customerId);
				con.close();
			}catch(Exception e) {
				System.out.println("Exception occurred:"+e.getMessage());
				e.printStackTrace();
			}
		}
	return customerId;
	}
	public int addCustomer(String name,String phone, String address, String customerType) {
		try {
			customerId = generateCustomerId();
			con = DriverManager.getConnection(Db_Url,user,pwd);
			String query = "insert into customer(IdNo,name,phone,address,customerType) values(?, ?, ?, ?, ?)";
			PreparedStatement pr = con.prepareStatement(query);
			pr.setInt(1,customerId);
			pr.setString(2, name);
			pr.setString(3,phone);
			pr.setString(4, address);
			pr.setString(5, customerType);
			pr.execute();
			con.close();
		}catch(Exception e) {
			System.out.println("Exception occurred while adding customer:"+e.getMessage());
		}
		return customerId;
	}
	public void addOwner(String name, String ownerType, String phone, String address, String carType, String model, String year, boolean alreadyOwner,int owId) {
		try {
			Connection connection = DriverManager.getConnection(Db_Url,user,pwd);
			if(alreadyOwner == false) {
				ownerId = generateOwnerId();
				vehicleId = generateVehicleId();
				String query = "insert into owner(ownerId,ownerType,name,phone,address) values(?, ?, ?, ?, ?)";
				PreparedStatement pr = connection.prepareStatement(query);
				pr.setInt(1, ownerId);
				pr.setString(2, ownerType);
				pr.setString(3, name);
				pr.setString(4, phone);
				pr.setString(5, address);
				pr.execute();
				
				query = "insert into car(vehicleId,carType,model,vehicleYear) values(?,?,?,?)";
				pr.clearParameters();
				pr = connection.prepareStatement(query);
				pr.setInt(1, vehicleId);
				pr.setString(2, carType);
				pr.setString(3, model);
				pr.setInt(4, Integer.parseInt(year));
				pr.execute();
				
				query = "insert into vehicleOwner(ownerId,vehicleId) values(?,?)";
				pr.clearParameters();
				pr=connection.prepareStatement(query);
				pr.setInt(1, ownerId);
				pr.setInt(2, vehicleId);
				pr.execute();
			}else {
				String query1 = "insert into car(vehicleId,carType,model,vehicleYear) values(?,?,?,?)";
				PreparedStatement p1 =connection.prepareStatement(query1);
				p1.clearParameters();
				p1 = connection.prepareStatement(query1);
				p1.setInt(1, vehicleId);
				p1.setString(2, carType);
				p1.setString(3, model);
				p1.setInt(4, Integer.parseInt(year));
				p1.execute();
				
				String query2 = "insert into vehicleOwner(ownerId,vehicleId) values(?,?)";
				p1.clearParameters();
				p1=connection.prepareStatement(query2);
				p1.setInt(1, owId);
				p1.setInt(2, vehicleId);
				p1.execute();
			}
			connection.close();
		}catch(Exception e) {
			System.out.println("Exception occurred :"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public double getOwnerWeeklyEarning(int ownerId,String carType) throws SQLException {
		double amount=0;
			List<Integer> vehicles = getVehiclesOfOwner(ownerId);
			Connection conForTypeOfVehicle = DriverManager.getConnection(Db_Url,user,pwd);
			for(int vehicleId : vehicles) {
				String queryToGetVehicleType = "select vehicleId from car where vehicleId="+vehicleId+" and carType='"+carType+"'";
				PreparedStatement prForVehicleType = conForTypeOfVehicle.prepareStatement(queryToGetVehicleType);
				ResultSet rs=prForVehicleType.executeQuery();
				if(rs.next()) {
					String queryToFetchEarning="select SUM(amount) from (\r\n" + 
							"select SUM(amountDue) as amount from dailyRental where vehicleid="+vehicleId+" and returnDate>DATE_ADD(CURDATE(), INTERVAL - WEEKDAY(CURDATE()) DAY)\r\n" + 
							"UNION ALL\r\n" + 
							"select SUM(amountDue) as amount from weeklyRental where vehicleId="+vehicleId+" and returnDate>DATE_ADD(CURDATE(), INTERVAL - WEEKDAY(CURDATE()) DAY)\r\n" + 
							") as T";
					PreparedStatement prToFetchEarning = conForTypeOfVehicle.prepareStatement(queryToFetchEarning);
					ResultSet earnings=prToFetchEarning.executeQuery();
					if(earnings.next()) {
						amount += earnings.getDouble(1);
					}
				}
			}
		conForTypeOfVehicle.close();
		return amount;
	}
	public List<Integer> getVehiclesOfOwner(int ownerId) throws SQLException{
		List<Integer> vehicleList = new ArrayList<Integer>();
		Connection con = DriverManager.getConnection(Db_Url,user,pwd);
		String getVehicles="select vehicleId from vehicleOwner where ownerId="+ownerId;
		PreparedStatement prVehicles = con.prepareStatement(getVehicles);
		ResultSet allVehicles = prVehicles.executeQuery();
		while(allVehicles.next()) {
			vehicleList.add(allVehicles.getInt(1));
		}
		con.close();
		return vehicleList;
	}
}