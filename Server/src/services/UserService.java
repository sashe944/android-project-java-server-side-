package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.ConnectionDB;
import objects.User;

public class UserService {

	public User find(String facNum){
		User user = new User();
    	  Connection conn = null;
    	    Statement stmt = null;
    	    try{
    	    	Class.forName("org.sqlite.JDBC");
    	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/Sasaho/Desktop/Quiz_Server.db");
    	        conn.setAutoCommit(false);
    	        stmt = conn.createStatement();
    	        ResultSet rs = stmt.executeQuery("SELECT * FROM Student_table where FacultyNumber LIKE " + facNum );

    	        while (rs.next()){
    	        	user.name = rs.getString("FirstName");
    	        	user.family = rs.getString("LastName");
    	        	user.facNum = rs.getString("FacultyNumber");
    	        }  
    	    }
    	    catch(Exception e){
    	    	e.printStackTrace();
    	    }
		return user;
	}
	       	
}
