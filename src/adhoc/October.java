package adhoc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class October
 */
@WebFilter({"/Ride.do", "/Sis.do"})
public class October implements Filter {

    /**
     * Default constructor. 
     */
    public October() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String action = request.getRequestURI();
		if(action.contains("/Ride.do")) {
			response.setContentType("text/html");
			response.getWriter().write("Service is not available ... <a href=\"Dash.do\">Dashboard</a>");
		}else if(action.contains("/Sis.do")) {
			String sortby = (String) request.getParameter("sortBy");
			if(sortby!=null && !sortby.equals("NONE")) {
				response.setContentType("text/html");
				response.getWriter().write("Sorting is not available ... <a href=\"Dash.do\">Dashboard</a>");
			}else {
				chain.doFilter(request, response);
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
