package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.Question;
import objects.TestType;
import objects.User;

public class ConnectionDB {
	Connection conn = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
     public boolean connection(){
		
		
		  try{
			  
		      Class.forName("org.sqlite.JDBC");
		      conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/Sasaho/Desktop/Quiz_Server.db");
		      return true;
		    }
		      catch(Exception e)
		      {
			    e.printStackTrace();
			    return false;
		      }finally{
			    	  try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		      }
	}
     
     public User getUser(String facNum) {
    	 User user = new User();
		return user; 
    }
  
    public Question getQuestionAndAnswerFromDB(String nextQuestion){
    	 Question question = new Question();
    	 return question;
     }
     
     public TestType getTest(){
    	 TestType typeOfTest = new TestType();
		return typeOfTest;
     }

}
