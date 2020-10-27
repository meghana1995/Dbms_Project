import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Services.ConnectionService;

public class student {
	public static void login(Connection con) throws SQLException {
				//Connection con = ConnectionService.getConnection();
				String univid, password, random;
				Home.sc.nextLine();
				System.out.println("Enter univid");
				univid = Home.sc.nextLine();
				System.out.println("Enter password");
				password = Home.sc.nextLine();
				int userid = login.universityuserlogin(con, univid, password, "S");
				if (userid == -1) {
					System.out.println("Sign in incorrect!");	
					Home.displayHomePage();
				}
				//ConnectionService.closeConnection();
//				sc.close();
				displayHomePage(univid,con);
			
		}

			public static void displayHomePage(String univid,Connection con) throws SQLException {
				//Connection con = ConnectionService.getConnection();
//				final Scanner sc  = new Scanner(System.in);
                
				int choice = 0;
				do {
					System.out.println("1. Paycitation");
					System.out.println("2. Getpermit");
					System.out.println("3. Park the car");
					System.out.println("4. Exit");
					System.out.println("Please enter you choice: ");

  				    choice = Home.sc.nextInt();
//					if(sc.hasNextInt() )
//					     choice = sc.nextInt(); // if there is another number  
//					else 
//					     choice = 3; 
					if (choice == 1) {
						citation citation=new citation(); 
						citation.paycitation(con,univid,"S");

					} else if (choice == 2) {
					
						permits.getpermit("S",univid,con);
					} 
					else if(choice == 3) {
						parking.park(con,"S", univid);
					}else if(choice == 4) {
						System.out.println("Thank you!");
						break;
					}
					else {
						System.out.println("Invalid choice");
					}
					
				} while (choice != 3);
				
				//ConnectionService.closeConnection();
//				sc.close();
			}


	}

