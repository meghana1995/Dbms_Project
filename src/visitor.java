import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Services.ConnectionService;

public class visitor {

	public static void main(Connection con) throws SQLException {
//		Connection con = ConnectionService.getConnection();
		//Scanner sc = new Scanner(System.in);
		ResultSet resultSet = null;
		String name, pnumber;
		Home.sc.nextLine();
		System.out.println("Enter first name");
		name = Home.sc.nextLine();			
		System.out.println("Enter phone number");
		pnumber = Home.sc.nextLine();	
		//Insert into visitor table
		String checkvisitor = "SELECT * FROM vistor N WHERE N.name ='" + name+ "' AND N.pnumber = '" + pnumber+"'";
//      String query = "SELECT * FROM NONVISITOR";
//	  System.out.println(query);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(checkvisitor);
			resultSet = preparedStatement.executeQuery(checkvisitor);
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
//		String query = "SELECT *  FROM VISTOR";
		if(resultSet==null) {
		//resultSet = null;
		String query = "INSERT INTO VISTOR VALUES('"+name+"','"+pnumber+"')";
		preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(query);
			resultSet = preparedStatement.executeQuery(query);	
//			while (resultSet.next()) {
//				String p = resultSet.getString("NAME");
//			    String n = resultSet.getString("PNUMBER");
//			    System.out.println(p+" "+n);
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		displayHomePage(pnumber, con);

	}
	
	public static void displayHomePage(String pnumber, Connection con) throws SQLException {
		
//		Connection con = ConnectionService.getConnection();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		int number = 0;
		ResultSet resultSet = null;
		do {
			System.out.println("1. Pay citation");
			System.out.println("2. Get permit");
			System.out.println("3. Park the car");
			System.out.println("4. Exit the lot");
			System.out.println("5. Exit");
			System.out.println("Please enter you choice: ");

			choice = sc.nextInt();
			if (choice == 1) {
				citation.paycitation(con, pnumber, "V");
            } else if (choice == 2) {
            	//Print existing vehicles
            	permits.getpermit("V", pnumber,con);
			} else if(choice == 3) {
				parking.park(con,  "V", pnumber);
			} else if(choice == 4) {
				parking.exit(con);
			}
			else if(choice == 5) {
				System.out.println("Thank you!");
				Home.displayHomePage();
			}
			else {
				System.out.println("Invalid choice");
			}
			
		} while (choice != 4 );
		
//		ConnectionService.closeConnection();
		
	}

}
