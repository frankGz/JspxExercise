package ctrl;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int serviceUsed = (int)request.getServletContext().getAttribute("ServiceUsed");
		int droneUsed = (int)request.getServletContext().getAttribute("DroneUsed");
		Map<String, Set<String>> m = (Map<String, Set<String>>) request.getServletContext().getAttribute("UserAction");
		
		response.setContentType("text/html");
		response.getWriter().write("<a href=\"Dash.do\">Dashboard</a><br>");
		response.getWriter().write("Total Service Used: " + serviceUsed + "<br>");
		response.getWriter().write("Drone Service Used: " + droneUsed + "<br>");
		double per =  ((double) droneUsed/(double)serviceUsed) * 100.0;
		response.getWriter().write("Percentage: " + String.format("%.2f", per) + "% <br>");
		
		int total_user = m.size();
		int used_both = 0;
		for(Set<String> set : m.values()) {
			System.out.println(set.toString());
			if(set.contains("Drone") && set.contains("Ride")) {
				used_both++;
			}
		}
		
		response.getWriter().write(used_both + " out of " + total_user + " user used both S3 and S4");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
