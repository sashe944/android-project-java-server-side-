package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import objects.Answer;
import objects.Question;
import objects.TestType;

public class QuestionService {

	Connection conn;
	Statement stmt;

	 //Getting the question and the answers  
		public Question getQuestionAndAnswerFromDB(String testType){
		
			Question randomQuestion = getRandomQuestion(testType);
			List<Answer> answers = getAnswersByQuestionId(testType, randomQuestion.id);
			randomQuestion.answers = answers;
			return randomQuestion;
		}
	
    //Take Database Location. Common for all methods
	private void goToDatabaseLocation(){
		conn = null;
		stmt = null;
		try{
		Class.forName("org.sqlite.JDBC");
	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/Sasaho/Desktop/Quiz_Server.db");
	        conn.setAutoCommit(false);
	        stmt = conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Question getRandomQuestion(String testTableName){
		goToDatabaseLocation();
		try{
				Question question = new Question();
				ResultSet rsQuestion = stmt.executeQuery("SELECT * FROM "+ testTableName + " order by random() limit 1");
		        while (rsQuestion.next()){
		        question.id = rsQuestion.getInt("ID");
		        question.questionText = rsQuestion.getString("Question");
		        question.type =  rsQuestion.getInt("Type");
	           }
		        return question;
	        }catch(Exception e){
	    	  e.printStackTrace(); 
	       }    
		return null;
	}
	
   
    private List<Answer> getAnswersByQuestionId(String testType, int questionId) {
    	List<Answer> answers = new ArrayList<Answer>(3);
    	
    	String answerTableName= null;
    	switch (testType) {
    	case "Question_table":
			answerTableName = "Answer_table";
			break;
    	 case "CSharpQuestion_table":
    		 answerTableName = "CSharpAnswer_table";
    		 break;
    	 case "JavaQuestion_table":
    		 answerTableName = "JavaAnswer_table";
    		 break;

		default:
			break;
		}
    	
    	try{
	    	ResultSet rsAnswer = stmt.executeQuery("SELECT * FROM " + answerTableName +" WHERE QuestionID = " + questionId );
	        while (rsAnswer.next()){
	        	Answer answer = new Answer();
	        	answer.id = rsAnswer.getInt("AnswerID");
		        answer.answerText = rsAnswer.getString("Answer");
		        answer.questionId = questionId;
		        answer.isCorrect = rsAnswer.getBoolean("IsCorrect");
		       
		        answers.add(answer);
	        }	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }	
    	
    	return answers;
    }
}