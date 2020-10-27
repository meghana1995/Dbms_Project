package Services;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {
	private static Connection connection = null;

    public static Connection getConnection() {
    	if(connection == null) {
            try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection(
	                    "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01", "ssingal", "abcd1234");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        	}
        return connection;
    }
    
    public static void closeConnection()
    {
    	try
    	{
    		if(connection != null)
    			connection.close();
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	
    	connection = null;

}
}