package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

/**
 * Servlet implementation class Gps
 */
@WebServlet("/Gps.do")
public class Gps extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("calc") == null)
		  {
		     this.getServletContext().getRequestDispatcher("/Gps.jspx").forward(request, response);
		  }
		else
		{
			System.out.println("execute gps.do");
			Double t1 = Double.parseDouble(request.getParameter("lat1"));
			Double n1 = Double.parseDouble(request.getParameter("lon1"));
			Double t2 = Double.parseDouble(request.getParameter("lat2"));
			Double n2 = Double.parseDouble(request.getParameter("lon2"));
			
			request.setAttribute("lat1", t1.intValue());
			request.setAttribute("lon1", n1.intValue());
			request.setAttribute("lat2", t2.intValue());
			request.setAttribute("lon2", n2.intValue());
			
			Engine engine = Engine.getInstance();
			try {
				String distance = String.format("%d", (int)Math.round(engine.doGps(t1, n1, t2, n2)));
				
				request.setAttribute("result", distance.charAt(0) + "," + distance.substring(1, distance.length()) + " km");
			} catch (Exception e) {
				request.setAttribute("result", e.getMessage());
				e.printStackTrace();
			} 
			request.getRequestDispatcher("/Gps.jspx").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
