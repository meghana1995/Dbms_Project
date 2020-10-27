import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;



import Services.ConnectionService;
public class login {
	
	public static int universityuserlogin(Connection connection, String univid, String password, String type)
		throws SQLException {
				ResultSet resultSet = null;
				String query = "(SELECT * FROM nonvisitor N WHERE N.univid ='" + univid+ "' AND N.password = '" + password
						+ "')" ;
//              String query = "SELECT * FROM NONVISITOR";
//			  System.out.println(query);
				PreparedStatement preparedStatement = null;
				try {
					preparedStatement = connection.prepareStatement(query);
					resultSet = preparedStatement.executeQuery(query);
					if (!resultSet.next()) {
						return -1;
				}
//				while (resultSet.next()) {
//					    String s = resultSet.getString("UNIVID");
//				        String p = resultSet.getString("PASSWORD");
//					    System.out.println(s + "   " + p);
//					}
				}
				 catch (SQLException e) {
					e.printStackTrace();
				}
				
				return 1;
	}
	}
			

