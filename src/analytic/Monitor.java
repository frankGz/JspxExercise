package analytic;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;


/**
 * Application Lifecycle Listener implementation class Monitor
 *
 */
@WebListener
public class Monitor implements ServletContextListener, ServletRequestListener {

    /**
     * Default constructor. 
     */
    public Monitor() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre)  {
    	ServletContext context = sre.getServletContext();
    	context.setAttribute("ServiceUsed", ((int)context.getAttribute("ServiceUsed"))+1);
    	Map<String, Set<String>> m  = (Map<String, Set<String>>) context.getAttribute("UserAction");
    	
    	
    	HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
    	String session_id = request.getSession().getId();
    	String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);
		System.out.println("ServletRequest created: " +methodName);
		if (m.containsKey(session_id))
		{
			m.get(session_id).add(methodName);
		}else {
			Set<String> set = new HashSet<>();
			set.add(methodName);
			m.put(session_id, set);
		}
		if(methodName.equals("Drone")) {
			context.setAttribute("DroneUsed", ((int)context.getAttribute("DroneUsed"))+1);
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("ServletContext destoryed. ");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext context = sce.getServletContext();
    	context.setAttribute("ServiceUsed", 0);
    	context.setAttribute("DroneUsed", 0);
    	Map<String, Set<String>> m = new HashMap<>();
    	context.setAttribute("UserAction", m);
    	System.out.println("ServletContext created.");
    }
	
}
