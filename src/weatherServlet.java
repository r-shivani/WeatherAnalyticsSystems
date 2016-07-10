

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class weatherServlet
 */
@WebServlet("/weatherServlet")
public class weatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public weatherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	// TODO Auto-generated method stub
    	  ServletContext context = getServletContext(); 
    	}

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		request.setAttribute("weatherMap", session.getAttribute("weatherMap"));
		ServletContext context = getServletContext();
		
		if(context.getAttribute("zipcode") != null){
		String zipcode=(String)context.getAttribute("zipcode");
		request.setAttribute("zipcode1", zipcode);
		}
	@SuppressWarnings("unchecked")
	Map<String, Object> weatherMap =(Map<String, Object>) session.getAttribute("weatherMap");
		
	
		
		
		
		
		
		request.getRequestDispatcher("weather.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String zipcode = request.getParameter("zipcode");
		Map<String, Object> weatherMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		//String host = null;
		try {
		    Registry registry = LocateRegistry.getRegistry("localhost", 1099);
		    Hello stub = (Hello) registry.lookup("Hello");
		    weatherMap = stub.getRemoteTemp(zipcode);
		    session.setAttribute("weatherMap", weatherMap);
		} catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
		
ServletContext context = getServletContext(); 
		
		context.setAttribute("zipcode", zipcode); 
		
		for (String name: weatherMap.keySet()){

        String key =name.toString();
        String value = weatherMap.get(name).toString();  
        System.out.println("in POST data before "+key + " " + value);  


}
	
	   // response.sendRedirect("Servlet1");
		doGet(request,response);
	}

}
