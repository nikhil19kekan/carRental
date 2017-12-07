import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.ManageCsv;

public class LoadData extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ManageCsv loader;
		try {
			loader = new ManageCsv();
			loader.loadData();
			resp.getWriter().write("<html>"
					+ "<body>"
					+ "<div class="+"container"+">"
					+ "<a class=\"btn btn-primary\" href=\"./index.jsp\">"
					+ "Return Home"
					+ "</a>"
					+ "</div>" + "<h3>Data Loaded Successfully</h3>"
					+ "</body>"
					+ "</html>");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
