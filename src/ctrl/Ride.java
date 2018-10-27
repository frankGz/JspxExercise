package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

/**
 * Servlet implementation class Ride
 */
@WebServlet("/Ride.do")
public class Ride extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("calc") == null)
		  {
		     this.getServletContext().getRequestDispatcher("/Ride.jspx").forward(request, response);
		  }
		else
		{
			System.out.println("execute ride.do");
			String from = (String) request.getParameter("from");
			String dest = (String) request.getParameter("dest");
			request.setAttribute("from", from);
			request.setAttribute("dest", dest);
			Engine engine = Engine.getInstance();
			try {
				Double cost = engine.doRide(from, dest);
				request.setAttribute("result", "¥" + String.format("%.2f", cost));
			} catch (Exception e) {
				request.setAttribute("result", e.getMessage());
				e.printStackTrace();
			} 
			request.getRequestDispatcher("/Ride.jspx").forward(request, response);
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
