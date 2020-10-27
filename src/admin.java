import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Services.ConnectionService;
import java.util.Date;


public class admin {

	public static void login(Connection con) throws SQLException {
		//Connection con = ConnectionService.getConnection();
//		Scanner sc = new Scanner(System.in);
		String univid, password;
		Home.sc.nextLine();
		System.out.println("Enter univid");
		univid = Home.sc.nextLine();			
		System.out.println("Enter password");
		password = Home.sc.nextLine();
		int userid = login.universityuserlogin(con, univid, password, "A");
		if (userid == -1) {
			System.out.println("Sign in incorrect");	
			Home.displayHomePage();
		}
		//ConnectionService.closeConnection();
//		sc.close();
		displayHomePage(univid,con);
	}

	public static void displayHomePage(String univid,Connection con) throws SQLException {
		
	//	Connection con = ConnectionService.getConnection();
//		Scanner sc = new Scanner(System.in);
//		Scanner sc = ScannerService.getScanner();
		int choice = 0;
		int number = 0;
		//String choice_2="";
		ResultSet resultSet = null;
		do {
			System.out.println("1. Add new Parking lot");
			System.out.println("2. Check for citations");
			//System.out.println("3. Get permit");
			System.out.println("3. Exit");
			System.out.println("Please enter you choice: ");

			choice = Home.sc.nextInt();
			if (choice == 1) {
				Home.sc.nextLine();
				System.out.println("Enter the name of the Parking lot : ");
				String pname = Home.sc.nextLine();
				System.out.println("Enter the address of the lot: ");
				String addr = Home.sc.nextLine();
				System.out.println("Enter the zone designation for the lot: ");
				String zone = Home.sc.nextLine();
				System.out.println("Enter the number of spaces: ");
				int spaces = Home.sc.nextInt();
				System.out.println("Enter the starting number of space: ");
				int start_space_no = Home.sc.nextInt();
				System.out.println("Are there any special type of spaces in this lot (Ex: Handicapped, Electric etc) (Enter 0/1): ");
				int choice_sp = Home.sc.nextInt();
				
				ArrayList<Integer> special_space_number = new ArrayList<Integer>();
				ArrayList<String> special_space_type = new ArrayList<String>();
				for(int i=start_space_no;i<spaces+start_space_no;i++){
					special_space_number.add(i);
					special_space_type.add("R");
				}
				while(choice_sp == 1){
					System.out.println("Enter the space number and the corresponding type: ");
					System.out.println("Enter the space number: ");
					int update_space_num = Home.sc.nextInt();
					Home.sc.nextLine();
					System.out.println("Enter the space type: ");
					String update_space_type = Home.sc.nextLine();
					special_space_type.set(special_space_number.indexOf(update_space_num),update_space_type);
					System.out.println("Press 1 to add more: Press any key to stop adding more");
					choice_sp = Home.sc.nextInt();
				}
				String query = "INSERT INTO ParkingLots VALUES ('"+pname+"','"+addr+"','"+zone+"')";
        		PreparedStatement preparedStatement = null;
				try {
        			preparedStatement = con.prepareStatement(query);
        			resultSet = preparedStatement.executeQuery(query);
        			for(int i=0;i<spaces;i++){
        				String query1 = "INSERT INTO hasSpaces VALUES ('"+pname+"',"+i+1+",'"+special_space_type.get(i)+"','"+zone+"','V')";
        				System.out.println(query1);
        				preparedStatement = con.prepareStatement(query1);
            			resultSet = preparedStatement.executeQuery(query1);
        				//stmt.executeUpdate("INSERT INTO hasSpaces VALUES ("+pname+","+i+1+","+special_space_type[i]+","+zone+")");
        			}
        			
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
				finally {
        			System.out.println("New Parking lot created");
				}
            } else if (choice == 2) {
            	System.out.println("... CITATIONS TABLE ...");
            	String query = "SELECT * FROM CITATIONS";
        		PreparedStatement preparedStatement = null;
            	try {
        			preparedStatement = con.prepareStatement(query);
        			resultSet = preparedStatement.executeQuery(query);
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
            	if(resultSet!=null) {
            		while (resultSet.next()) {
        				int citnum = resultSet.getInt("citnum");
        				String lnumber = resultSet.getString("lnumber");
        				String lotname = resultSet.getString("lotname");
        				java.sql.Timestamp citeTime = resultSet.getTimestamp("citeTime");
        				java.sql.Date lastSent = resultSet.getDate("lastSent");
        				java.sql.Date duedate = resultSet.getDate("duedate");
        				String paystatus = resultSet.getString("paystatus");
        				int amount = resultSet.getInt("amount");
        				String category = resultSet.getString("category");
        				System.out.println(citnum+"   "+lnumber + "   "+lotname+"   "+citeTime+"   "+lastSent+"   "+duedate+"   "+paystatus+"   "+amount+"   "+category);
        			}
            	}
            	System.out.println("\n\n... CITATIONS THAT ARE STILL DUE");
            	String query1 = "SELECT * from CITATIONS where paystatus='U' AND lastSent>= duedate AND duedate <(select sysdate from dual)";
            	try {
        			preparedStatement = con.prepareStatement(query1);
        			resultSet = preparedStatement.executeQuery(query1);
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
            	java.sql.Date duedate;
            	java.sql.Date lastSent;
            	java.sql.Timestamp citeTime;
            	String paystatus;
            	int amount;
            	if(resultSet!=null) {
            		while (resultSet.next()) {
        				int citnum = resultSet.getInt("citnum");
        				String lnumber = resultSet.getString("lnumber");
        				String lotname = resultSet.getString("lotname");
        				citeTime = resultSet.getTimestamp("citeTime");
        				lastSent = resultSet.getDate("lastSent");
                    	duedate = resultSet.getDate("duedate");
        				paystatus = resultSet.getString("paystatus");
        				amount = resultSet.getInt("amount");
        				String category = resultSet.getString("category");
        				System.out.println(citnum+"   "+lnumber + "   " + lotname+ "   "+citeTime+"   "+lastSent+"   "+duedate+"   "+paystatus+"   "+amount+"   "+category);
        			}
            	}
            	
            	System.out.println("\nSend notification to pay the due amount ? (1/0)");
    			//sc = new Scanner(System.in);
            	//Home.sc.nextLine();
    			int choice_2 = Home.sc.nextInt();
//            	System.out.println(choice_2);

    			if(choice_2 ==1) {
    				//System.out.println("inside choice y");	
    				String query4 = "SELECT (SYSDATE -c.duedate)\r\n"
                			+ "\n"+
                			"FROM dual,citations c\n";
                			
                	PreparedStatement ps =null; 
                	int day=0;
                	String Days="";
                			try {
                				ps = con.prepareStatement(query4);
                				resultSet = ps.executeQuery();
                				// print resultset
                				if (resultSet.next()) {
//                					System.out.println("Days");
//                					System.out.println("-----------------");
                					
                					Days= resultSet.getString(1);
//                					System.out.println(Days);
                					double d = Double.parseDouble(Days);
                					day = (int) d;
                					//Days =Days.split(".")[0];
                					day = (int)(day/Math.pow(10, Math.floor(Math.log10(day))));
//                            		System.out.println(day);
                						
                				}
                			} catch (SQLException e) {
                				// TODO Auto-generated catch block
                				e.printStackTrace();
                			}
                		
                	//int d = Integer.parseInt(Days);		
    				String query2 = "UPDATE CITATIONS set amount = amount +"+day+" where paystatus='U' AND lastSent>= duedate AND duedate <(select sysdate from dual)";
                	String query3 = "UPDATE CITATIONS SET lastSent =(select sysdate from dual)  where paystatus='U' AND lastSent>= duedate AND duedate <(select sysdate from dual)";                	
                		try {
            			preparedStatement = con.prepareStatement(query2);
            			resultSet = preparedStatement.executeQuery(query2);
            			preparedStatement = con.prepareStatement(query3);
            			resultSet = preparedStatement.executeQuery(query3);
            		} catch (SQLException e) {
            			e.printStackTrace();
            		}
                	finally {
        				System.out.println("NOTIFICATION SENT SUCCESSFULLY. ");
                	}
    			}
            }
			else if(choice == 3) {
				System.out.println("Thank you!");
				break;
			}
			else {
				System.out.println("Invalid choice");
			}
			
		} while (choice != 4 );
		
//		ConnectionService.closeConnection();
//		sc.close();
	}

}
