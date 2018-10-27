package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

/**
 * Servlet implementation class Prime
 */
@WebServlet("/Prime.do")
public class Prime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("calc") == null && request.getParameter("recalc") ==null) // new to this page
		  {
		     this.getServletContext().getRequestDispatcher("/Prime.jspx").forward(request, response);
		  }
		else if (request.getParameter("calc") == null) // submitted by recalc
		{
			
		}
		else // submitted by calc
		{
			//valueFound: 1 - found, 2 - no more, 3 - illegal input 
			System.out.println("execute Prime.do calc");
			int min = Integer.parseInt(request.getParameter("min"));
			if (min >= 0) {
				int max = Integer.parseInt(request.getParameter("max"));
				request.setAttribute("min", min);
				request.setAttribute("max", max);
				Engine engine = Engine.getInstance();
				try {
					int prime = engine.doPrime(min, max);
					if (prime != -1) {
						request.setAttribute("result", prime);
						request.setAttribute("found", "found");
					} else {
						request.setAttribute("found", "nomore");
					}
				} catch (Exception e) {
					request.setAttribute("result", e.getMessage());
					e.printStackTrace();
				} 
			}else {
				request.setAttribute("found", "negative");
			}
			request.getRequestDispatcher("/Prime.jspx").forward(request, response);
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
