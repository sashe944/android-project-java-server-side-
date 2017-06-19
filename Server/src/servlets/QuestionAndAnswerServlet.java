package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import objects.Question;
import services.QuestionService;

/**
 * Servlet implementation class QuestionAndAnswerServlet
 */
@WebServlet("/QuestionAndAnswerServlet")
public class QuestionAndAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	 private QuestionService questionService;
	 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionAndAnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
        questionService = new QuestionService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  String testType = request.getParameter("testType");
		
		 Question questionAndAnswer = questionService.getQuestionAndAnswerFromDB(testType);
		  
		  
		  if(questionAndAnswer!=null){
			  response.setContentType("application/json;charset=UTF-8");
			  Gson gson = gson_builder.create();
			  response.getWriter().write(gson.toJson(questionAndAnswer));
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
