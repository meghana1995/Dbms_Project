import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import Services.ConnectionService;

public class demoqueries {
	public static void displaydemoqueries(Connection con) throws SQLException {
		//Scanner sc = new Scanner(System.in);
		System.out.println("1. For each lot, generate the total number of citations given in all zones in the lot for a three month period (07/01/2020 - 09/30/2020)"
				+ "");
		System.out.println("2. For Justice Lot, generate the number of visitor permits in a date rangee: 08/12/2020 -08/20/2020, grouped by permit type e.g. regular, electric, handicapped.");
		System.out.println("3. For each visitor’s parking zone, show the total amount of revenue generated (including pending citation fines) for each day in August 2020");
		System.out.println("4. Sample query 1 - Show the list of zones for each lot as tuple pairs (lot, zone).");
		System.out.println("5. Sample query 2 - Get permit information for a given employee with UnivID: 1006020");
		System.out.println("6. Sample query 3 - Get vehicle information for a particular UnivID: 1006003");
		System.out.println("7. Sample query 4 - Find an available space# for Visitor for an electric vehicle in a specific parking lot: Justice Lot");
		System.out.println("8. Sample query 5 - Find any cars that are currently in violation");
		System.out.println("9. Sample query 6 - How many employees have permits for parking zone D");
		System.out.println("10. Go back");
		System.out.println("Please enter you choice: ");
		int choice = Home.sc.nextInt();
		switch (choice) {
		case 1:
			executeQuery1(con);
			break;
		case 2:
			executeQuery2(con);
			break;
		case 3:
			executeQuery3(con);
			break;
		case 4:
			samplequery1(con);			
		case 5:
			samplequery2(con);
			break;
		case 6:
			samplequery3(con);
			break;
		case 7:
			samplequery4(con);
			break;
		case 8:
			samplequery5(con);
			break;
		case 9:
			samplequery6(con);
			break;
		case 10:
			Home.displayHomePage();
//			break;
      }
    }

	private static void executeQuery1(Connection connection) {
		ResultSet resultSet = null;
		String query = "SELECT  lotname, COUNT(*) as totalcitations\r\n"
				+ "		FROM citations\r\n"
				+ "		WHERE citeTime BETWEEN TIMESTAMP'2020-07-01 00:00:00' AND TIMESTAMP'2020-09-30 11:59:59'\n"
				+ "		GROUP BY lotname";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			// print resultset
			if (resultSet.isBeforeFirst()) {
				System.out.println("Total citations for each lot");
				System.out.println("-----------------");
				while (resultSet.next()) {
					String lotname = resultSet.getString(1);
					int tCites = resultSet.getInt("totalcitations");
					System.out.println(lotname + " : " + tCites);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	private static void executeQuery2(Connection connection) {
		ResultSet resultSet = null;
		//Scanner in=new Scanner(System.in);
		//System.out.println("Enter Lot name: ");
		//String Lot = Home.sc.nextLine();
		//System.out.println("Enter start date-time of period: ");
		//String start_period = Home.sc.next();
		//e: 08/12/2020 -08/20/2020
		//System.out.println("Enter end date-time of period: ");
		//String end_period =Home.sc.next();
		String query =" SELECT spacetype,COUNT(*) as count\r\n"
				+ "FROM Permits\r\n"
				+ "WHERE zoneid = 'V' AND lotname = 'Justice Lot' AND st BETWEEN TIMESTAMP'2020-08-12 00:00:00' AND TIMESTAMP'2020-08-20 11:59:59'\n"
				+ "GROUP BY spacetype";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			// print resultset
			if (resultSet.isBeforeFirst()) {
				System.out.println("Number of citations in Justice lot grouped by space type");
				System.out.println("-----------------");
				while (resultSet.next()) {
					String spaceType = resultSet.getString("spacetype");
					int count = resultSet.getInt("count");
					System.out.println(spaceType + " : " + count);
					System.out.println("-----------------");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//in.close();
	}
	
	private static void executeQuery3(Connection connection) throws SQLException {
		ResultSet resultSet = null;		
		String query="SELECT EXTRACT(DAY FROM citeTime) as dd, SUM(amount) AS total_amt FROM Citations  WHERE citeTime BETWEEN TIMESTAMP'2020-08-01 00:00:00' AND TIMESTAMP'2020-09-01 00:00:00' GROUP BY EXTRACT(DAY FROM citeTime)\n";
				
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			System.out.println("Amount generated for each day in the month of August - ");
			if (resultSet.isBeforeFirst()) {
				while (resultSet.next()) {
					String month ="August";
					String day = resultSet.getString(1);					
					String amt = "Amount ";
					int tamt = resultSet.getInt("total_amt");
					System.out.println(month+" " + day + " : " + tamt  );
					System.out.println("-----------------");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static void samplequery1(Connection connection) {
		ResultSet resultSet = null;
		String query=" select lotname,zonedes from parkinglots\n ";
		PreparedStatement preparedStatement = null;
	try {
		 preparedStatement = connection.prepareStatement(query);
		 resultSet = preparedStatement.executeQuery();
		 System.out.println("Tuple pair ");
					if (resultSet.isBeforeFirst()) {
						while (resultSet.next()) {
							String month ="Lotname";
							String day = resultSet.getString("lotname");					
							String amt = "Zone";
							int tamt = resultSet.getInt("Zone");
							System.out.println(month+" " + day + " : " + tamt  );
							System.out.println("-----------------");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
	private static void samplequery2(Connection connection) {
		ResultSet resultSet = null;
		String query=" select p.permitid, p.zoneid, p.st, p.exp, p.lotname, p.spacenum, p.spacetype, h.lnumber  from permits p , haspermits h where p.permitid = h.permitid and h.id='1002'\n ";
		PreparedStatement preparedStatement = null;
	try {
		 preparedStatement = connection.prepareStatement(query);
		 resultSet = preparedStatement.executeQuery();
		 System.out.println("Tuple pair ");
					if (resultSet.isBeforeFirst()) {
						while (resultSet.next()) {
							String month ="Lotname";
							String day = resultSet.getString("lotname");					
							String amt = "Zone";
							int tamt = resultSet.getInt("Zone");
							System.out.println(month+" " + day + " : " + tamt  );
							System.out.println("-----------------");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	private static void samplequery3(Connection connection) {
		ResultSet resultSet = null;
		String query = "SELECT  lotname, COUNT(*) as totalcitations\r\n"
				+ "		FROM citations\r\n"
				+ "		WHERE citeTime BETWEEN TIMESTAMP'2020-07-01 00:00:00' AND TIMESTAMP'2020-09-30 11:59:59'\n"
				+ "		GROUP BY lotname";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			// print resultset
			if (resultSet.isBeforeFirst()) {
				System.out.println("Total citations for each lot");
				System.out.println("-----------------");
				while (resultSet.next()) {
					String lotname = resultSet.getString(1);
					int tCites = resultSet.getInt("totalcitations");
					System.out.println(lotname + " : " + tCites);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	private static void samplequery4(Connection connection) {
		ResultSet resultSet = null;
		String query = "SELECT  lotname, COUNT(*) as totalcitations\r\n"
				+ "		FROM citations\r\n"
				+ "		WHERE citeTime BETWEEN TIMESTAMP'2020-07-01 00:00:00' AND TIMESTAMP'2020-09-30 11:59:59'\n"
				+ "		GROUP BY lotname";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			// print resultset
			if (resultSet.isBeforeFirst()) {
				System.out.println("Total citations for each lot");
				System.out.println("-----------------");
				while (resultSet.next()) {
					String lotname = resultSet.getString(1);
					int tCites = resultSet.getInt("totalcitations");
					System.out.println(lotname + " : " + tCites);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	private static void samplequery5(Connection connection) {
		ResultSet resultSet = null;
		String query = "SELECT  lotname, COUNT(*) as totalcitations\r\n"
				+ "		FROM citations\r\n"
				+ "		WHERE citeTime BETWEEN TIMESTAMP'2020-07-01 00:00:00' AND TIMESTAMP'2020-09-30 11:59:59'\n"
				+ "		GROUP BY lotname";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			// print resultset
			if (resultSet.isBeforeFirst()) {
				System.out.println("Total citations for each lot");
				System.out.println("-----------------");
				while (resultSet.next()) {
					String lotname = resultSet.getString(1);
					int tCites = resultSet.getInt("totalcitations");
					System.out.println(lotname + " : " + tCites);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
   
	private static void samplequery6(Connection connection) {
		ResultSet resultSet = null;
		String query = "SELECT  lotname, COUNT(*) as totalcitations\r\n"
				+ "		FROM citations\r\n"
				+ "		WHERE citeTime BETWEEN TIMESTAMP'2020-07-01 00:00:00' AND TIMESTAMP'2020-09-30 11:59:59'\n"
				+ "		GROUP BY lotname";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			// print resultset
			if (resultSet.isBeforeFirst()) {
				System.out.println("Total citations for each lot");
				System.out.println("-----------------");
				while (resultSet.next()) {
					String lotname = resultSet.getString(1);
					int tCites = resultSet.getInt("totalcitations");
					System.out.println(lotname + " : " + tCites);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}


}