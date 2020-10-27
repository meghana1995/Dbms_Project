import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import Services.ConnectionService;

public class permits {
public static int id_num = 202;
public static String id_str = "A";
private Connection connection;
	
public static void getpermit(String type, String univid, Connection con) throws SQLException
	{
		//Connection con = ConnectionService.getConnection();
//		Scanner sc = new Scanner(System.in);
	    
		Timestamp ts1, ts2;
		int numVehicles, spaceNum = 0;
		String spaceType, startDate, expDate, vstartTime, vexitTime, lotName,query, zoneid = "", permit_id, model, manufacturer, color, lnumber, manYear; 
		ResultSet resultSet = null;
//		List<String> vehicles = new ArrayList<String>(); 
		String checkPermit = "SELECT * FROM HASPERMITS WHERE id = '"+univid+"'";
	    PreparedStatement preparedStatement1 = null;
		try {
			preparedStatement1 = con.prepareStatement(checkPermit);
			resultSet = preparedStatement1.executeQuery(checkPermit);
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		if(resultSet.next()) {
			System.out.println("You already have a permit!");
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
		else{
			Home.sc.nextLine();
		System.out.println("Enter the type of space\n");
		spaceType = Home.sc.nextLine();		
		//Home.sc.nextLine();
		// check 
		if(type == "S") {
			System.out.println("Enter date to start permit in YYYY-MM-DD format(Permit starts from 9:00AM on the given date)\n");
			startDate = Home.sc.nextLine();
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	Calendar c = Calendar.getInstance();
	    	try{
	    	   //Setting the date to the given date
	    	   c.setTime(sdf.parse(startDate));
	    	}catch(ParseException e){
	    		e.printStackTrace();
	    	 }
	    	   
	    	//Number of Days to add
	    	c.add(Calendar.DAY_OF_MONTH, 120);  
	    	//Date after adding the days to the given date
	    	expDate = sdf.format(c.getTime());
	    	startDate += " 09:00:00";
	    	expDate += " 11:59:59";
	    	ts1 = Timestamp.valueOf(startDate);
	    	ts2 = Timestamp.valueOf(expDate);
			numVehicles = 1;
			query = "SELECT h.lotname, h.spacenum, h.zoneid\n"+
			"FROM hasSpaces h \n"+
			"WHERE h.zoneid ='"+type+ "' AND h.status ='V'";			
		}else if ( type == "E") {
			System.out.println("Enter date to start permit in YYYY-MM-DD format(Permit starts from 9:00AM on the given date)\n");
			startDate = Home.sc.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	Calendar c = Calendar.getInstance();
	    	try{
	    	   //Setting the date to the given date
	    	   c.setTime(sdf.parse(startDate));
	    	}catch(ParseException e){
	    		e.printStackTrace();
	    	 }
	    	   
	    	//Number of Days to add
	    	c.add(Calendar.DAY_OF_MONTH, 365);  
	    	//Date after adding the days to the given date
	    	expDate = sdf.format(c.getTime());
	    	startDate += " 09:00:00";
	    	expDate += " 11:59:59";
	    	ts1 = Timestamp.valueOf(startDate);
	    	ts2 = Timestamp.valueOf(expDate);
			//do {
//			System.out.println("Enter number of vehicles(max 5 vehicles are permitted for an employee)\n");
			numVehicles = 1;	
//			if (numVehicles > 5) {
//				System.out.println("Please enter a number below 5");
//			}
//			}while(numVehicles > 5);
			query = "SELECT h.lotname, h.spacenum, h.zoneid\n"+
					"FROM hasSpaces h \n"+
					"WHERE h.status ='V'";			}
		else {
			//Visitor
			System.out.println("Enter date to start permit in YYYY-MM-DD format\n");
			startDate = Home.sc.nextLine();
			int h, m, s, h1, m1, s1;
			do {
			System.out.println("Enter start time in hours, minutes and seconds in hh:mm:ss(Permit hours for visitors are valid from 9:00 AM to 6:00PM)\n");
			Home.sc.useDelimiter(":|\\s+");
			h = Home.sc.nextInt();
			m = Home.sc.nextInt();
			s = Home.sc.nextInt();
			System.out.println("Enter end time in hours, minutes and seconds in hh:mm:ss(Permit hours for visitors are valid from 9:00 AM to 6:00PM)\n");
			Home.sc.useDelimiter(":|\\s+");
			h1 = Home.sc.nextInt();
			m1 = Home.sc.nextInt();
			s1 = Home.sc.nextInt();
			if(h<9 && h1>18) {
				System.out.println("Enter hours between 9:00 AM to 6:00PM\n");
			}
			//Check if starttitme and exittime are valid
			}while(h<9 && h1>18);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	Calendar c = Calendar.getInstance();
	    	try{
	    	   //Setting the date to the given date
	    	   c.setTime(sdf.parse(startDate));
	    	}catch(ParseException e){
	    		e.printStackTrace();
	    	 }
	    	   
	    	//Number of Days to add  
	    	//Date after adding the days to the given date
	    	expDate = startDate;
	    	startDate += " " + Integer.toString(h)+":"+Integer.toString(m)+":"+Integer.toString(s);
	    	expDate += " " + Integer.toString(h1)+":"+Integer.toString(m1)+":"+Integer.toString(s1);
	    	
	    	ts1 = Timestamp.valueOf(startDate);
	    	ts2 = Timestamp.valueOf(expDate);			
			numVehicles = 1;
			query = "SELECT h.lotname, h.spacenum, h.zoneid\n"+
					"FROM hasSpaces h \n"+
					"WHERE h.zoneid = '"+type+"' AND h.status ='V'";
			Home.sc.nextLine();
		}
		String query_lotName;
		do {
		System.out.println("Enter the Lot\n");
		lotName = Home.sc.nextLine();
		query_lotName = query +" AND h.lotName = '"+lotName+"'";
		System.out.println(query_lotName);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(query_lotName);
			resultSet = preparedStatement.executeQuery(query_lotName);
			if (resultSet.next()) {
				//if(resultSet.next()) {
					System.out.println("Enter....");
					zoneid = resultSet.getString("zoneid");
					System.out.println(zoneid);
					spaceNum = resultSet.getInt("spacenum");
					System.out.println(spaceNum);
//					String query1 = "UPDATE hasSpaces h set h.status = 'O' where h.spacenum ='"+spaceNum+"' AND h.lotName = '"+lotName+"'";
//					preparedStatement1 = null;
//					try {
//						preparedStatement1 = con.prepareStatement(query1);
//						resultSet = preparedStatement1.executeQuery(query1);
//					} catch (SQLException e) {
//						e.printStackTrace();	
//					}	
					//}
					break;
				// TO DO: give an option to exit
			}
			else {
				System.out.println("Sorry! The given lot does not have empty spaces.\n");
//				System.out.println("Do you want to exit? (y/n)\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		}while(!resultSet.next());
		
		
		//insert into permit, has permits and vehicles and has vehicles
		PreparedStatement preparedStatement2 = null;
		permit_id = generatePermitId();
		String query2 =  "INSERT INTO PERMITS VALUES ('"+permit_id+"','"+zoneid+"',TIMESTAMP'"+ts1+"',TIMESTAMP'"+ts2+"','"+numVehicles+"','"+spaceType+"','"+lotName+"','"+spaceNum+"')";
		System.out.println(query2);
		try {
			preparedStatement2 = con.prepareStatement(query2);
			resultSet = preparedStatement2.executeQuery(query2);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		for (int i = 0; i < numVehicles; i++) {
			System.out.println("Enter model of vehicle");
  		    model = Home.sc.nextLine();
  		    System.out.println("Enter vehicle manufacturer");
		    manufacturer = Home.sc.nextLine();
		    System.out.println("Enter year of manufacturing");
  		    manYear = Home.sc.nextLine();
  		    //int inum = Integer.parseInt(manYear);
  		    System.out.println("Enter color of vehicle");
		    color = Home.sc.nextLine();
		    System.out.println("Enter license number of vehicle");
		    lnumber = Home.sc.nextLine();
		    PreparedStatement preparedStatement3 = null;
			String query3 =  "INSERT INTO VEHICLES VALUES ('"+lnumber+"','"+color+"','"+manYear+"','"+model+"','"+manufacturer+"')";
			System.out.println(query3);
			try {
				preparedStatement3 = con.prepareStatement(query3);
				preparedStatement3.executeUpdate(query3);
			} catch (SQLException e) {
			e.printStackTrace();
			}
			
			PreparedStatement preparedStatement4 = null;
			String query4 =  "INSERT INTO HASVEHICLES VALUES ('"+lnumber+"','"+univid+"')";
			try {
				preparedStatement4 = con.prepareStatement(query4);
				preparedStatement4.executeUpdate(query4);
			} catch (SQLException e) {
			e.printStackTrace();
			}
			
			PreparedStatement preparedStatement5 = null;
			//check whether he has permit
			String query5 =  "INSERT INTO HASPERMITS VALUES ('"+univid+"','"+permit_id+"','"+lnumber+"')";
			try {
				preparedStatement5 = con.prepareStatement(query5);
				preparedStatement5.executeUpdate(query5);
			} catch (SQLException e) {
			e.printStackTrace();
			}
			String query1 = "UPDATE hasSpaces h set h.status = 'O' where h.spacenum ='"+spaceNum+"' AND h.lotName = '"+lotName+"'";
			preparedStatement1 = null;
			try {
				preparedStatement1 = con.prepareStatement(query1);
				resultSet = preparedStatement1.executeQuery(query1);
			} catch (SQLException e) {
				e.printStackTrace();	
			}
		    
		    
  		}
		
		
	
    }
}		
	public static String generatePermitId(){
		permits.id_num+=1;
	
		return permits.id_str+Integer.toString(permits.id_num);
		
	}
		
	}

/*CREATE TABLE hasSpaces(
lotName VARCHAR(10),
spacenum INT,
spacetype VARCHAR(10),
zoneid CHAR(2),
PRIMARY KEY(lotName, spacenum),
FOREIGN KEY (lotName) References ParkingLots(lotName) ON DELETE CASCADE);

CREATE TABLE vehicles(
lnumber CHAR(10) PRIMARY KEY,
color CHAR(10),
yr YEAR,
model CHAR(10),
manufacturer CHAR(10)
);
permitid VARCHAR(10) PRIMARY KEY,
zoneid CHAR(2),
startdate DATE,
expdate DATE,
numVehicles INT,
spaceType VARCHAR(10),
lotname VARCHAR(10),
spacenum INT,
FOREIGN KEY (lotname) REFERENCES ParkingLots(lotName)*/