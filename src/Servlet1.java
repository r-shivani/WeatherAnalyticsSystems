

import java.io.IOException;
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
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
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
		String zipcode=(String)context.getAttribute("zipcode");
		request.setAttribute("zipcode1", zipcode);
		
	@SuppressWarnings("unchecked")
	Map<String, Object> weatherMap =(Map<String, Object>) session.getAttribute("weatherMap");
		
		for (String name: weatherMap.keySet()){

            String key =name.toString();
            String value = weatherMap.get(name).toString();  
            System.out.println("data before "+key + " " + value);  


}
		request.getRequestDispatcher("weather.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
