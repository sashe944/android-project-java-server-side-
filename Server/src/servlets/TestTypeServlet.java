package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import objects.TestTypes;
import services.TestTypeService;


/**
 * Servlet implementation class TestTypeServlet
 */
@WebServlet("/TestTypeServlet")
public class TestTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
		 private TestTypeService testTypeService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
        testTypeService = new TestTypeService();
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		TestTypes typeOfTest = testTypeService.getTestType();
	    response.setContentType("application/json;charset=UTF-8");
	    Gson gson = gson_builder.create();
	    response.getWriter().write(gson.toJson(typeOfTest));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
