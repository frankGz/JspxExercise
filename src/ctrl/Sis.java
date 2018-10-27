package ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;
import model.StudentBean;

/**
 * Servlet implementation class Sis
 */
@WebServlet("/Sis.do")
public class Sis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("calc") == null)
		  {
		     this.getServletContext().getRequestDispatcher("/Sis.jspx").forward(request, response);
		  }
		else
		{
			System.out.println("execute Sis.do");
			String prefix = (String) request.getParameter("prefix");
			String minGpa = (String) request.getParameter("minGpa");
			String sortBy = (String) request.getParameter("sortBy");
			request.setAttribute("prefix", prefix);
			request.setAttribute("minGpa", minGpa);

			Engine engine = Engine.getInstance();
			try {
				List<StudentBean> list = engine.doSis(prefix, minGpa, sortBy);
				request.setAttribute("message", "Sorted by " + sortBy);
				request.setAttribute("list", list);
			} catch (Exception e) {
				request.setAttribute("message", e.getMessage());
				e.printStackTrace();
			} 
			
			request.getRequestDispatcher("/Sis.jspx").forward(request, response);
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
