import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import Services.ConnectionService;

public class citation {
	
	public static void paycitation(Connection con, String univid, String type) throws SQLException {
		ResultSet resultSet = null;
		int choice=0;
//		Scanner sc = new Scanner(System.in);
		System.out.println("Citations for " + univid+" -");
		String query = "select paystatus,amount,duedate,citnum\n"
				+ "from citations c,hasvehicles h\n"
				+ "where c.lnumber=h.lnumber and h.id='"+univid+"' AND c.paystatus ='U'";
				
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(query);
			resultSet = preparedStatement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultSet.next()) {
			String s = resultSet.getString("paystatus");
			System.out.println(s);
//		    if (s=="P"){
//		    		s = "Paid";
//		    }
//		    else {
//		    	s = "Unpaid";	
//		    }
			int n = resultSet.getInt("amount");
		    int c =resultSet.getInt("citnum");
		    System.out.println("Citation number: "+c +"\nAmount: " + n +"\nStatus: "+s);
		    System.out.println("\n");
			while (resultSet.next()) {
			    s = resultSet.getString("paystatus");
//			    if (s=="P"){
//			    		s = "Paid";
//			    }
//			    else {
//			    	s = "Unpaid";	
//			    }
			    n = resultSet.getInt("amount");
			    c =resultSet.getInt("citnum");
			    System.out.println("Citation number: "+c +"\nAmount: " + n +"\nStatus: "+s);
			    System.out.println("\n");
			}
			    int ch;
//			    Home.sc.nextInt();
			    System.out.println("Do you want to 1) Pay Citation or 2) Exit");
			    ch=Home.sc.nextInt();
			  	
			    if(ch==1) {
//			    	Home.sc.nextInt();	
			    	System.out.println("Enter the Citaition num to be paid: ");
				    choice = Home.sc.nextInt();	
				    String query2="update citations set paystatus='P' where citnum="+choice;
				    System.out.println(query2);

				    System.out.println("\n Citaition num "+ choice + " has been paid! ");
				    preparedStatement = null;
//				    resultSet = null;
					try {
						preparedStatement = con.prepareStatement(query2);
						preparedStatement.executeUpdate(query2);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			    }
			    else {
			    	if(type =="E") {
			    		employees.displayHomePage(univid, con);
			    	}
			    	else if(type =="S") {
			    		student.displayHomePage(univid, con);
			    	}
			    	else {
			    		visitor.displayHomePage(univid, con);
			    	}
			    	//check type and return to the corresponding page (Ex; Type == 'E' return to employee.java;
			    } 
		}
		else {
			System.out.println("You have no citations!\n");
			if(type =="E") {
	    		employees.displayHomePage(univid, con);
	    	}
	    	else if(type =="S") {
	    		student.displayHomePage(univid, con);
	    	}
	    	else {
	    		visitor.displayHomePage(univid, con);
	    	}
		}
//		ConnectionService.closeConnection();
	}
		//return to respective pages;
	}

