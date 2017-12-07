import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.ManageCsv;
public class RentCar extends HttpServlet{
	 final String JDBC_Driver="com.mysql.jdbc.Driver";
	 final String Db_Url="jdbc:mysql://localhost:3306/carRental";
		
	//username and passsword localhost
	 final String user="root";
	 final String pwd="nk281992";
		
	//connection and statement
	Connection con=null;
	Statement stmt=null;
	ResultSet res = null;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
		Class.forName(JDBC_Driver).newInstance();
		con=DriverManager.getConnection(Db_Url, user, pwd);
		String vehicleId=req.getParameter("vehicleId");
		String vehicleYear=req.getParameter("vehicleYear");
		String rentType=req.getParameter("carRentType");		
		String period = req.getParameter("period");
		String customerId = req.getParameter("customerId");
		String q = "insert into rental values("+customerId+",'"+rentType+"')";
		PreparedStatement prnew= con.prepareStatement(q);
		prnew.execute();
		System.out.println(q);
		if(rentType.equalsIgnoreCase("dailyrental")) {
					
			String amountQuery="insert into dailyRental(IdNo,vehicleId,noOfdays,amountDue,startDate,returnDate) values(?,?,?,?,CURDATE(),NULL)";
			PreparedStatement ptemp=con.prepareStatement(amountQuery);
			ptemp.setInt(1,Integer.parseInt(customerId));
			ptemp.setString(2, vehicleId);
			ptemp.setInt(3, Integer.parseInt(period));
			PreparedStatement prFetch=con.prepareStatement("select carType from car where vehicleId="+vehicleId);
			System.out.println("------------vehicleId:"+vehicleId);
			ResultSet resultSet=prFetch.executeQuery();
			resultSet.next();
			String carType=resultSet.getString(1);
			System.out.println("-------------------car type"+carType);
			prFetch=con.prepareStatement("select dailyRate from carTypeRate where carType='"+carType+"'");
			resultSet=prFetch.executeQuery();
			resultSet.next();
			double amountDue=resultSet.getDouble(1)*Double.parseDouble(period);
			System.out.println("-------------------amount due:"+amountDue);
			ptemp.setDouble(4, amountDue);
			ptemp.execute();
			ManageCsv m = new ManageCsv();
			m.updateReturnDateDaily();
		}else {
			String amountQuery="insert into weeklyRental(IdNo,vehicleId,noOfWeeks,amountDue,startDate,returnDate) values(?,?,?,?,CURDATE(),NULL)";
			PreparedStatement ptemp=con.prepareStatement(amountQuery);
			ptemp.setInt(1,Integer.parseInt(customerId));
			ptemp.setString(2, vehicleId);
			ptemp.setInt(3, Integer.parseInt(period));
			PreparedStatement prFetch=con.prepareStatement("select carType from car where vehicleId="+vehicleId);
			System.out.println("------------vehicleId:"+vehicleId);
			ResultSet resultSet=prFetch.executeQuery();
			resultSet.next();
			String carType=resultSet.getString(1);
			System.out.println("-------------------car type"+carType);
			prFetch=con.prepareStatement("select weeklyRate from carTypeRate where carType='"+carType+"'");
			resultSet=prFetch.executeQuery();
			resultSet.next();
			double amountDue=resultSet.getDouble(1)*Double.parseDouble(period);
			System.out.println("-------------------amount due:"+amountDue);
			ptemp.setDouble(4, amountDue);
			ptemp.execute();
			ManageCsv m = new ManageCsv();
			m.updateReturnDateWeekly();
		}
		resp.getWriter().write("<html>"
				+ "<body>"
				+ "<div class="+"container"+">"
				+ "<a class=\"btn btn-primary\" href=\"./index.jsp\">"
				+ "Return Home"
				+ "</a>"
				+ "</div>"
				+ "</body>"
				+ "</html>");
		con.close();
		}catch(Exception e) {
			System.out.println("Exception in RentCar"+e.getMessage());
			e.printStackTrace();
		}
	}
}
