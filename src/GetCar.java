import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.PrepareData;
/*
 * Generate a vehicle ID and enter owner and car into DB
 */
public class GetCar extends HttpServlet{
	PrintWriter out=null;
	public void init(HttpServletRequest req, HttpServletResponse res) throws IOException {
		out = res.getWriter();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("servlet GetCar");
		System.out.println(req.getParameter("ownerName"));
		System.out.println(req.getParameter("ownerType"));
		System.out.println(req.getParameter("isExistingOwner"));
		System.out.println(req.getParameter("ownerId"));
		System.out.println(req.getParameter("phone"));
		System.out.println(req.getParameter("carType"));
		System.out.println(req.getParameter("model"));
		System.out.println(req.getParameter("year"));
		int ownId = -1;
		if(!req.getParameter("ownerId").equalsIgnoreCase("")) {
			ownId=Integer.parseInt(req.getParameter("ownerId"));
		}
		PrepareData p;
		try {
			p = new PrepareData();
		if("on".equalsIgnoreCase(req.getParameter("isExistingOwner"))) {
				p.addOwner(req.getParameter("ownerName"), req.getParameter("ownerType"), req.getParameter("phone"), req.getParameter("address"), req.getParameter("carType"), req.getParameter("model"), req.getParameter("year"), true,ownId);
			
		}else {
				p.addOwner(req.getParameter("ownerName"), req.getParameter("ownerType"), req.getParameter("phone"), req.getParameter("address"), req.getParameter("carType"), req.getParameter("model"), req.getParameter("year"), false,ownId);
				
		}
		}catch(Exception e) {
				System.out.println("Exception in add Owner"+e.getMessage());
				e.printStackTrace();
		}
		res.getWriter().write("<html>"
				+ "<body>"
				+ "<div class="+"container"+">"
				+ "<a class=\"btn btn-primary\" href=\"./index.jsp\">"
				+ "Return Home"
				+ "</a>"
				+ "</div>"
				+ "</body>"
				+ "</html>");
		
	}
}
