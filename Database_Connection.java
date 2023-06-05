//referred
//https://stackoverflow.com/questions/27168842/database-connection-in-java-with-separated-class

package _221047001;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database_Connection {
		
	    // Database Variables
		@SuppressWarnings("unused")
		private final String Url;                // = "jdbc:mysql://localhost/javadb"
	    private Connection connect = null;
	    

		public Connection getConnect() {
			return connect;
		}

		private Statement sqlStatement;
		

	    
	    // Connect to Database
	    public Database_Connection( String DatabaseUrl) throws SQLException,  ClassNotFoundException, FileNotFoundException {
	        
	    	this.Url = DatabaseUrl;


	        this.connect = DriverManager.getConnection(DatabaseUrl);
	        
	        this.sqlStatement = (Statement) connect.createStatement();
	        
	    }

		public ResultSet query_normalStatement(String givenStatement) throws SQLException{
	    	ResultSet resultSet = ((java.sql.Statement) sqlStatement).executeQuery(givenStatement);
	        return resultSet;
	    }

		
	    /*public String getDatabaseUserName() {
	        return databaseUserName;
	    }*/
	    
	    public void closeConnection() throws SQLException{
	        connect.close();
	    }

	
}
