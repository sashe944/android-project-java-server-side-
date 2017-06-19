package connection;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import objects.User;
import services.UserService;

// Extend HttpServlet class
public class HelloWorld extends HttpServlet {
 
	 private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
    private UserService userService;

@Override
public void init() throws ServletException {
//TODO Auto-generated method stub
userService = new UserService();
}

public void doGet(HttpServletRequest request,
                HttpServletResponse response)
        throws ServletException, IOException
{
  String password = request.getParameter("facNum");
  User user = userService.find(password);

  if (user != null) {
	  response.setContentType("application/json;charset=UTF-8");
      Gson gson = gson_builder.create();
      response.getWriter().write(gson.toJson(user));
  }
  else {
      request.setAttribute("error", "Unknown user, please try again");
  }	
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

}
}