import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.PrepareData;

/*
 * Enter Customer into DB
 */

public class GetCustomer extends HttpServlet{
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
		
	public void init() {
		try {
			Class.forName(JDBC_Driver).newInstance();
			con=DriverManager.getConnection(Db_Url, user, pwd);
		}catch(Exception e) {
			System.out.println("Exception in GetCar:"+e.getMessage());
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
			System.out.println("In doGet method");
			System.out.println(req.getParameter("name"));
			System.out.println(req.getParameter("phone"));
			System.out.println(req.getParameter("address"));
			System.out.println(req.getParameter("customerType"));
			int custId = 0;
			try {
			PrepareData data = new PrepareData();
			custId=data.addCustomer(req.getParameter("name"), req.getParameter("phone"), req.getParameter("address"), req.getParameter("customerType"));
			}catch(Exception e) {
				System.out.println("Exception while addCustomer:"+e.getMessage());
			}
			res.setContentType("text/html");
			res.getWriter().write("<html>"
					+ "<body>"
					+ "<div class="+"container"+">"
					+ "<a class=\"btn btn-primary\" href=\"./index.jsp\">"
					+ "Return Home"
					+ "</a>"
					+ "</div>"
					+ "Please note the customer id:"+custId
					+ "</body>"
					+ "</html>");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("servlet GetCustomer");
		doGet(req, res);
	}
}
