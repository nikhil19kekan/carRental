package com.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageCsv {
	//JDBC driver and database URL Localhost
	static final String JDBC_Driver="com.mysql.jdbc.Driver";
	static final String Db_Url="jdbc:mysql://localhost:3306/carRental";
		
	//username and passsword localhost
	static final String user="root";
	static final String pwd="nk281992";
		
	//connection and statement
	static Connection con=null,conFetch=null;
	Statement stmt=null;
	ResultSet res = null;
	
	static FileReader f = null;
	static BufferedReader br = null;
	final String pathCarTypeRate = ".\\files\\carTypeRate.csv";
	final String pathCars = ".\\\\\\\\files\\\\\\\\cars.csv";
	final String pathOwner = ".\\\\\\\\files\\\\\\\\owner.csv";
	final String pathVehicleOwner = ".\\\\\\\\files\\\\\\\\vehicleOwner.csv";
	final String pathCustomer = ".\\\\\\\\\\\\\\\\files\\\\\\\\\\\\\\\\customer.csv";
	final String pathRental = ".\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\files\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\rental.csv";
	final String pathDailyRental = ".\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\files\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\dailyRental.csv";	
	final String pathWeeklyRental = ".\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\files\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\weeklyRental.csv";
	
	public ManageCsv() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName(JDBC_Driver).newInstance();
		con=DriverManager.getConnection(Db_Url, user, pwd);
		conFetch=DriverManager.getConnection(Db_Url, user, pwd);
		System.out.println("loaddata created");
	}
	
	public void loadData() {
		loadCarTypeRate();
		loadcar();
		loadOwner();
		loadVehicleOwner();
		loadCustomer();
		loadRental();
		loadDailyRental();
		loadWeeklyRental();
	}
	private void loadCarTypeRate() {
		try {
		f = new FileReader("C:\\Users\\Nikhil\\Desktop\\practice\\DatabaseProject2\\files\\carTypeRate.csv");
		br = new BufferedReader(f);
		String line;
		PreparedStatement pr=con.prepareStatement("insert into carTypeRate(carType,dailyRate,weeklyRate) values(?,?,?)");
		while((line=br.readLine()) != null) {
			String[] params = line.split(",");
			pr.setString(1, params[0]);
			pr.setDouble(2, Double.parseDouble(params[1]));
			pr.setDouble(3, Double.parseDouble(params[2]));
			pr.execute();
			
		}	
		}catch(Exception e) {
			System.out.println("exception in loadcarTypeRate"+e.getMessage());
		}
	}
	private void loadcar() {
		try {
			f=new FileReader("C:\\Users\\Nikhil\\Desktop\\practice\\DatabaseProject2\\files\\cars.csv");
			br=new BufferedReader(f);
			String line;
			PreparedStatement pr =con.prepareStatement("insert into car(vehicleId,carType,model,vehicleYear) values(?,?,?,?)");
			while((line=br.readLine()) != null) {
				String[] params = line.split(",");
				pr.setInt(1, Integer.parseInt(params[0]));
				pr.setString(2, params[1]);
				pr.setString(3, params[2]);
				pr.setString(4, params[3]); // setting Year in mysql
				pr.execute();
			}
		}catch(Exception e) {
			System.out.println("exception in loadcar"+e.getMessage());
		}
	}
	
	private void loadOwner() {
		try {
			f=new FileReader("C:\\Users\\Nikhil\\Desktop\\practice\\DatabaseProject2\\files\\owner.csv");
			br=new BufferedReader(f);
			String line;
			PreparedStatement pr =con.prepareStatement("insert into owner(ownerId,ownerType,name,phone,address) values(?,?,?,?,?)");
			while((line=br.readLine()) != null) {
				String[] params = line.split(",");
				pr.setInt(1, Integer.parseInt(params[0]));
				pr.setString(2, params[1]);
				pr.setString(3, params[2]);
				pr.setString(4, params[3]);
				pr.setString(5, params[4]);
				pr.execute();
			}
		}catch(Exception e) {
			System.out.println("exception in loadcar"+e.getMessage());
		}
	}
	private void loadVehicleOwner() {
		try {
			f=new FileReader("C:\\\\Users\\\\Nikhil\\\\Desktop\\\\practice\\\\DatabaseProject2\\\\files\\\\vehicleOwner.csv");
			br=new BufferedReader(f);
			String line;
			PreparedStatement pr =con.prepareStatement("insert into vehicleOwner(ownerId,vehicleId) values(?,?)");
			while((line=br.readLine()) != null) {
				String[] params = line.split(",");
				pr.setInt(1, Integer.parseInt(params[0]));
				pr.setString(2, params[1].trim().toString());
				pr.execute();
			}
		}catch(Exception e) {
			System.out.println("exception in loadVehicleOwner"+e.getMessage());
		}
	}
	private void loadCustomer() {
		try {
			f=new FileReader("C:\\\\\\\\Users\\\\\\\\Nikhil\\\\\\\\Desktop\\\\\\\\practice\\\\\\\\DatabaseProject2\\\\\\\\files\\\\\\\\customer.csv");
			br=new BufferedReader(f);
			String line;
			PreparedStatement pr =con.prepareStatement("insert into customer(IdNo,name,phone,address,customerType) values(?,?,?,?,?)");
			while((line=br.readLine()) != null) {
				String[] params = line.split(",");
				pr.setInt(1, Integer.parseInt(params[0]));
				pr.setString(2, params[1]);
				pr.setString(3, params[2]);
				pr.setString(4, params[3]);
				pr.setString(5, params[4]);
				pr.execute();
			}
		}catch(Exception e) {
			System.out.println("exception in loadCustomer"+e.getMessage());
		}
	}
	private void loadRental() {
		try {
			f=new FileReader("C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Nikhil\\\\\\\\\\\\\\\\Desktop\\\\\\\\\\\\\\\\practice\\\\\\\\\\\\\\\\DatabaseProject2\\\\\\\\\\\\\\\\files\\\\\\\\\\\\\\\\rental.csv");
			br=new BufferedReader(f);
			String line;
			PreparedStatement pr =con.prepareStatement("insert into rental(IdNo,rentType) values(?,?)");
			while((line=br.readLine()) != null) {
				String[] params = line.split(",");
				pr.setInt(1, Integer.parseInt(params[0]));
				pr.setString(2, params[1]);
				pr.execute();
			}
		}catch(Exception e) {
			System.out.println("Exception in loadCustomer)"+e.getMessage());
		}
	}
	private void loadDailyRental() {
		try {
			f=new FileReader("C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Nikhil\\\\\\\\\\\\\\\\Desktop\\\\\\\\\\\\\\\\practice\\\\\\\\\\\\\\\\DatabaseProject2\\\\\\\\\\\\\\\\files\\\\\\\\\\\\\\\\dailyRental.csv");
			br=new BufferedReader(f);
			String line;
			PreparedStatement pr =con.prepareStatement("insert into dailyRental(IdNo,vehicleId,noOfdays,amountDue,startDate,returnDate) values(?,?,?,?,?,?)");
			while((line=br.readLine()) != null) {
				System.out.println(line);
				String[] params = line.split(",");
				pr.setInt(1, Integer.parseInt(params[0]));
				pr.setInt(2, Integer.parseInt(params[1]));
				pr.setInt(3, Integer.parseInt(params[2]));
				int vehicleId=Integer.parseInt(params[1]);
				PreparedStatement prFetch=conFetch.prepareStatement("select carType from car where vehicleId="+String.valueOf(vehicleId));
				System.out.println("------------vehicleId:"+vehicleId);
				ResultSet resultSet=prFetch.executeQuery();
				resultSet.next();
				String carType=resultSet.getString(1);
				System.out.println("-------------------car type"+carType);
				prFetch=conFetch.prepareStatement("select dailyRate from carTypeRate where carType='"+carType+"'");
				resultSet=prFetch.executeQuery();
				resultSet.next();
				double amountDue=resultSet.getDouble(1)*Integer.parseInt(params[2]);
				System.out.println("-------------------amount due:"+amountDue);
				pr.setDouble(4,amountDue);
				pr.setString(5, params[4].substring(1, params[4].length()-1));
				pr.setString(6, null);
				pr.execute();
				updateReturnDateDaily();
			}
		}catch(Exception e) {
			System.out.println("Exception in loadDailyRental:"+e.getMessage());
			e.printStackTrace();
		}
	}
	public static void updateReturnDateDaily() throws SQLException {
		String q= "update dailyRental set returnDate=DATE_ADD(dailyrental.startDate, INTERVAL dailyrental.noOfDays DAY)";
		PreparedStatement p =con.prepareStatement(q);
		p.execute();
	}
	public static void updateReturnDateWeekly() throws SQLException {
		String q= "update weeklyRental set returnDate=DATE_ADD(weeklyrental.startDate, INTERVAL weeklyrental.noOfweeks WEEk)";
		PreparedStatement p =con.prepareStatement(q);
		p.execute();
	}
	private void loadWeeklyRental() {
		try {
			f=new FileReader("C:\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Nikhil\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Desktop\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\practice\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\DatabaseProject2\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\files\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\weeklyRental.csv");
			br=new BufferedReader(f);
			String line;
			PreparedStatement pr =con.prepareStatement("insert into weeklyRental(IdNo,vehicleId,noOfweeks,amountDue,startDate,returnDate) values(?,?,?,?,?,?)");
			while((line=br.readLine()) != null) {
				System.out.println(line);
				String[] params = line.split(",");
				pr.setInt(1, Integer.parseInt(params[0]));
				pr.setInt(2, Integer.parseInt(params[1]));
				pr.setInt(3, Integer.parseInt(params[2]));
				int vehicleId=Integer.parseInt(params[1]);
				PreparedStatement prFetch=conFetch.prepareStatement("select carType from car where vehicleId="+String.valueOf(vehicleId));
				System.out.println("------------vehicleId:"+vehicleId);
				ResultSet resultSet=prFetch.executeQuery();
				resultSet.next();
				String carType=resultSet.getString(1);
				System.out.println("-------------------car type"+carType);
				prFetch=conFetch.prepareStatement("select weeklyRate from carTypeRate where carType='"+carType+"'");
				resultSet=prFetch.executeQuery();
				resultSet.next();
				double amountDue=resultSet.getDouble(1)*Integer.parseInt(params[2]);
				System.out.println("-------------------amount due:"+amountDue);
				pr.setDouble(4,amountDue);
				pr.setString(5, params[4].substring(1, params[4].length()-1));
				pr.setString(6, null);
				pr.execute();
				updateReturnDateWeekly();
			}
		}catch(Exception e) {
			System.out.println("Exception in loadWeeklyRental)"+e.getMessage());
		}
	}
}
