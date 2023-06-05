//referred
//https://www.digitalocean.com/community/tutorials/sql-injection-in-java
//https://stackoverflow.com/questions/29285493/primary-key-and-unique-key-seperate-exception-handling-asp-net-c-sharp
//https://stackoverflow.com/questions/4085420/how-do-i-read-a-properties-file-and-connect-a-mysql-database


package _221047001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;




public class App_Implementation {
	
	private static final String PROPERTIES_FILE = "db.properties";
	Properties p = new Properties();
	
	
	public void add_inStatement() throws IOException, ClassNotFoundException {
		try {
			
			FileInputStream in = new FileInputStream(PROPERTIES_FILE);
			p.load(in);
			in.close();
			
			Database_Connection db = new Database_Connection(p.getProperty("jdbcUrl"));
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter your Credential\n");
			
			System.out.println("Enter user name: ");
			String userID = sc.nextLine();
			
			System.out.println("Enter the password: ");
			String password = sc.nextLine();
			
			sc.close();
			
			String query = "select count(*) from college_administrators where userName = '"+ userID +"' and password = '"+password+"'";
			ResultSet resultSet = db.query_normalStatement(query);
			
			resultSet.next();
			int count = resultSet.getInt(1);
			
			if(count == 1) {
				System.out.println("connected...!\n");
				String pdetails = "Select * from students";
				ResultSet rs = db.query_normalStatement(pdetails);
				
				while (rs.next()) {
					System.out.println(rs.getInt("id")+"   "+rs.getString("name")+"   "+rs.getString("course"));
	            }
			}else {
				System.out.println("Wrong Credential");
			}
	
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	public void add_inPreStatement() throws IOException, ClassNotFoundException {
		try {
			
			FileInputStream in = new FileInputStream(PROPERTIES_FILE);
			p.load(in);
			in.close();
			
			Database_Connection db = new Database_Connection(p.getProperty("jdbcUrl"));
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your Credential\n");
			System.out.println("Enter user name: ");
			String userID = sc.nextLine();
			
			System.out.println("Enter the password: ");
			String password = sc.nextLine();
			
			
			String query = "select count(*) from college_administrators where userName = ?  and password = ?;";
			PreparedStatement ps = db.getConnect().prepareStatement(query);
			ps.setString(1, userID);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();
			
			resultSet.next();
			int count = resultSet.getInt(1);
			
			if(count == 1) {
				System.out.println("\nconnected.....!");
				
				String sDetails = "insert into  students values (?,?,?);";
				
				PreparedStatement ps1 = db.getConnect().prepareStatement(sDetails);
				
				 System.out.println("Enter student id: ");
				 int pid = sc.nextInt();
				 ps1.setInt(1, pid);
				 
				 System.out.println("Enter student name: ");
				 String sName = sc.next();
				 ps1.setString(2, sName);
				 
				 System.out.println("Enter student Course: ");
				 
				 Scanner sc1 = new Scanner(System.in);
				 String sCourse = sc1.nextLine();
				 ps1.setString(3, sCourse);
				 
				 sc.close();
				 sc1.close();
				 
				 int n=ps1.executeUpdate();
				 if(n==1){
					 System.out.println("\nrecord inserted");
				 }
				 else {
				     System.out.println("\nrecord not inserted");
				 }
				 
				 db.closeConnection();
				
			}else {
				System.out.println("Wrong Credential");
			}
		
		}catch (SQLException ex){
			if (ex.getErrorCode() == 2627){
                if (ex.getMessage().contains("UNIQUE")){
                	System.out.println(" Unique key violation");
                }
                else if (ex.getMessage().contains("PRIMARY")){
                	System.out.println("\n Primary key violation");
                }
            }
            else
            	ex.printStackTrace();
		}
	}
}
	
	
	




