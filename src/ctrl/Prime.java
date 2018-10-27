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
			System.out.println("execute Prime.do recalc");
				int min = Integer.parseInt(request.getParameter("last"));
				int max = Integer.parseInt(request.getParameter("max"));
				request.setAttribute("min", min);
				request.setAttribute("max", max);
				Engine engine = Engine.getInstance();
				try {
					int prime = engine.doPrime(min, max);
					if (prime != -1) {
						request.setAttribute("result", prime);
						request.setAttribute("found", 1);
					} else {
						request.setAttribute("found", 2);
					}
				} catch (Exception e) {
					request.setAttribute("found", 4);
					request.setAttribute("error", e.getMessage());
					e.printStackTrace();
				} 
			request.getRequestDispatcher("/Prime.jspx").forward(request, response);
			
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
						// we have a result
						request.setAttribute("result", prime);
						request.setAttribute("found", 1);
					} else {
						//no result in range
						request.setAttribute("found", 2);
					}
				} catch (Exception e) {
					// exception found, put in error
					request.setAttribute("found", 4);
					request.setAttribute("error", e.getMessage());
					e.printStackTrace();
				} 
			}else {
				// cannot start with negative
				int max = Integer.parseInt(request.getParameter("max"));
				request.setAttribute("min", min);
				request.setAttribute("max", max);
				request.setAttribute("found", 3);
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
