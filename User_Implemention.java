package _221047001;

import java.util.Scanner;

public class User_Implemention {
	public static void main(String[] args)throws Exception {
		App_Implementation AI = new App_Implementation();
		
		Scanner sc=new Scanner(System.in);
		  System.out.println("Enter your choice: \n1.TO try SQL injection using normal Statement "
		  		+ "\n2.Preventing SQL Injection using PreparedStatement\n");
		 int n=sc.nextInt();
		  if(n==1) {
			  AI.add_inStatement();
		 }
		  else if(n==2){
			  AI.add_inPreStatement();
		}
		  sc.close();
	}
}
