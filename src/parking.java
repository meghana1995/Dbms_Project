import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import Services.ConnectionService;

public class parking {
	public static int cit_num = 125;
	public static void park(Connection con, String type, String univid) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet resultSet, rs1,rs2,rs3 = null;
	    PreparedStatement ps,ps1,ps2,ps3 = null;
	    String p_zone="", p_lnumber="", p_lot="", ep="", last_sent, cite_time, due_date;
		Home.sc.nextLine();
		
		System.out.println("Enter the lot: ");
		String park_lot = Home.sc.nextLine();
		System.out.println("Enter the zone which you are trying to park : ");
		String park_zone = Home.sc.nextLine();
		System.out.println("Enter the license number of the car you are parking");
		String lnumber = Home.sc.nextLine();	
		
		LocalDateTime park_time = java.time.LocalDateTime.now();
		String p = park_time.toString();
		
		String d = p.split("T")[0];
		String hours=p.split("T")[1].split(":")[0];
		String minutes=p.split("T")[1].split(":")[1];
		int dy = Integer.parseInt(d.split("-")[0]);
//		System.out.println(d.split("-")[2]);
		int dm = Integer.parseInt(d.split("-")[1]);
		int dd = Integer.parseInt(d.split("-")[2]);
//		last_sent = 
		int pp = Integer.parseInt(hours);
		int pm = Integer.parseInt(minutes);
		String cd = p.split("T")[0];
		//System.out.println(cd);
		String ct1 = p.split("T")[1];
//		System.out.println(ct1.split(regex));
		String ct = ct1.split("\\.")[0];
//		System.out.println(ct);
		
		cite_time = cd + " "+ct;
		last_sent = Integer.toString(dy)+"-"+Integer.toString(dm)+"-"+Integer.toString(dd);	
		
//		System.out.println("dy = "+dy+" ; dm = "+dm+" : dd = "+dd);

//		System.out.println("cite_time = "+cite_time+" ; last_sent = "+last_sent);
		if (dm == 12){
			int dynew = dy +1;
			due_date = Integer.toString(dynew)+"-"+Integer.toString(dm)+"-"+Integer.toString(dd);	

//			System.out.println("duedate = "+due_date);
		}else {
			int dmnew = dm + 1;
			due_date = Integer.toString(dy)+"-"+Integer.toString(dmnew)+"-"+Integer.toString(dd);
			System.out.println("duedate = "+due_date);
		}
//		String time = OffsetDateTime.parse(park_time).toLocalTime().toString();
		if(type=="S") {
			String query = "select * from haspermits where id = '"+univid+"'";
//			System.out.println(query);
			try {
				ps = con.prepareStatement(query);
				resultSet = ps.executeQuery(query);
				while(resultSet.next()) { //If ? else
					String pid = resultSet.getString("permitid");
					p_lnumber = resultSet.getString("lnumber");
					String q2 = "select * from permits where permitid = '"+pid+"'";
					ps1 = con.prepareStatement(q2);
					rs1 = ps1.executeQuery(q2);
					while(rs1.next()) {
						ep  = rs1.getString("exp");
					//	System.out.println("ep = "+ ep);
						p_zone = rs1.getString("zoneid");
						System.out.println(p_zone);
						p_lot = rs1.getString("lotname");
					}
				}
				
			}
			
			catch(SQLException e) {
					e.printStackTrace();	
			}

			int pdy = Integer.parseInt(ep.split(" ")[0].split("-")[0]);
			int pdm = Integer.parseInt(ep.split(" ")[0].split("-")[1]);
			int pdd = Integer.parseInt(ep.split(" ")[0].split("-")[2]);
			int pph = Integer.parseInt(ep.split(" ")[1].split(":")[0]);
			int ppm = Integer.parseInt(ep.split(" ")[1].split(":")[1]);
			int pps = Integer.parseInt(ep.split(" ")[1].split(":")[2]);
		//	System.out.println("pdy = "+pdy+" ; pdm = "+pdm+" : pdd = "+pdd);
//			pp = 12;
			if(pp<9 || pp >16){
				System.out.println("Happy parking!!");
				Home.displayHomePage();
			}
			else if(((park_zone.equals(p_zone.split(" ")[0]) && park_lot.equals(p_lot) && lnumber.equals(p_lnumber.split(" ")[0]))) && (pdy>=dy)&&(pdm>=dm)&&(pdd>=dd)&&(pph>=pp)&&(ppm>=pm)) {
				System.out.println("Happy parking!!");
				Home.displayHomePage();
			}
			else {
				if ((park_zone.equals(p_zone.split(" ")[0]) && park_lot.equals(p_lot) && lnumber.equals(p_lnumber.split(" ")[0]))) {
					System.out.println("permit is expired!");
					String cq = "insert into citations values (3010, '"+lnumber+"','"+park_lot+"',TIMESTAMP'"+cite_time+"',DATE'"+last_sent+"',DATE'"+due_date+"','U', 20,'"+park_zone+"','EP')";
					ps2 = con.prepareStatement(cq);
					rs2 = ps2.executeQuery(cq);
				}
				else {
					//issue citation based on zone
					System.out.println("parking is not permitted here!!");
					String cq = "insert into citations values (3010, '"+lnumber+"','"+park_lot+"',TIMESTAMP'"+cite_time+"',DATE'"+last_sent+"',DATE'"+due_date+"','U', 25,'"+park_zone+"','IP')";
					ps3 = con.prepareStatement(cq);
					rs3 = ps3.executeQuery(cq);
					
				}
			}
		}
		else if(type=="V"){
			String query = "select * from haspermits where id = '"+univid+"'";
			System.out.println(query);
			try {
				ps = con.prepareStatement(query);
				resultSet = ps.executeQuery(query);
				while(resultSet.next()) { //If ? else
					String pid = resultSet.getString("permitid");
					p_lnumber = resultSet.getString("lnumber");
					String q2 = "select * from permits where permitid = '"+pid+"'";
					ps1 = con.prepareStatement(q2);
					rs1 = ps1.executeQuery(q2);
					while(rs1.next()) {
						ep  = rs1.getString("exp");
					//	System.out.println("ep = "+ ep);
						p_zone = rs1.getString("zoneid");
						p_lot = rs1.getString("lotname");
						System.out.println(ep);
					}
				}
				
			}
			
			catch(SQLException e) {
					e.printStackTrace();	
			}
			int pdy = Integer.parseInt(ep.split(" ")[0].split("-")[0]);
			int pdm = Integer.parseInt(ep.split(" ")[0].split("-")[1]);
			int pdd = Integer.parseInt(ep.split(" ")[0].split("-")[2]);
			int pph = Integer.parseInt(ep.split(" ")[1].split(":")[0]);
			int ppm = Integer.parseInt(ep.split(" ")[1].split(":")[1]);
			int pps = Integer.parseInt(ep.split(" ")[1].split(":")[2]);
			if(pp<9 || pp >16){
				System.out.println("Happy parking2!!");
				Home.displayHomePage();
			} else if((park_zone.equals(p_zone.split(" ")[0]) && park_lot.equals(p_lot) && lnumber.equals(p_lnumber.split(" ")[0])) && ((pdy>=dy)&&(pdm>=dm)&&(pdd>=dd)&&(pph>=pp)&&(ppm>=pm))) {
				System.out.println("Happy parking!!");
				Home.displayHomePage();
			}
		
			else {			
//				if((park_zone.equals(p_zone)) && (park_lot.equals(p_lot)) && (lnumber.equals(p_lnumber))) {
				if ((park_zone.equals(p_zone.split(" ")[0]) && park_lot.equals(p_lot) && lnumber.equals(p_lnumber.split(" ")[0]))) {
					System.out.println("permit date is expired!!");
					String cq = "insert into citations values (7002, '"+lnumber+"','"+park_lot+"',TIMESTAMP'"+cite_time+"',DATE'"+last_sent+"',DATE'"+due_date+"','U', 20,'"+park_zone+"','EP')";
					ps2 = con.prepareStatement(cq);
					rs2 = ps2.executeQuery(cq);
				}
				else {
					//issue citation based on zone
					System.out.println("permit is invalid!");
					String cq = "insert into citations values (7002, '"+lnumber+"','"+park_lot+"',TIMESTAMP'"+cite_time+"',DATE'"+last_sent+"',DATE'"+due_date+"','U', 25,'"+park_zone+"','IP')";
					ps3 = con.prepareStatement(cq);
					rs3 = ps3.executeQuery(cq);
				}
			}
			
		}
	}
	
	public static void exit(Connection con) {
		PreparedStatement pss1,ps1, ps2, ps3;
		ResultSet resultSet,rs1;
		String ep = "", due_date="", lname="";
		LocalDateTime park_time = java.time.LocalDateTime.now() ;
		String p = park_time.toString();
		String hours=p.split("T")[1].split(":")[0];
		String d = p.split("T")[0];
//		String cd = p.split("T")[0];
		String zid="",snum = "";
		int dy = Integer.parseInt(d.split("-")[0]);
//		System.out.println(d.split("-")[2]);
		int dm = Integer.parseInt(d.split("-")[1]);
		int dd = Integer.parseInt(d.split("-")[2]);
		String cd = p.split("T")[0];
		//System.out.println(cd);
		String ct1 = p.split("T")[1];
//		System.out.println(ct1.split(regex));
		String ct = ct1.split("\\.")[0];
//		System.out.println(ct);
		
		String cite_time = cd + " "+ct;
		String last_sent = Integer.toString(dy)+"-"+Integer.toString(dm)+"-"+Integer.toString(dd);	
		
//		System.out.println("dy = "+dy+" ; dm = "+dm+" : dd = "+dd);

//		System.out.println("cite_time = "+cite_time+" ; last_sent = "+last_sent);
		if (dm == 12){
			int dynew = dy +1;
			due_date = Integer.toString(dynew)+"-"+Integer.toString(dm)+"-"+Integer.toString(dd);	

//			System.out.println("duedate = "+due_date);
		}else {
			int dmnew = dm + 1;
			due_date = Integer.toString(dy)+"-"+Integer.toString(dmnew)+"-"+Integer.toString(dd);
			//System.out.println("duedate = "+due_date);
		}
		System.out.println("Enter your  permit ID : ");
		String p_id = Home.sc.nextLine();
		String query="SELECT zoneid,lotname, spacenum,exp FROM PERMITS WHERE permitid='"+p_id+"'";
		try {
			pss1 = con.prepareStatement(query);
			resultSet = pss1.executeQuery(query);
			while(resultSet.next()) { //If ? else
				zid = resultSet.getString("zoneid");
				lname = resultSet.getString("lotname");
				snum = resultSet.getString("spacenum");
				ep=resultSet.getString("exp");
				String q2 = "UPDATE hasSpaces h set h.status = 'V' where h.spacenum ='"+snum+"' AND h.lotName = '"+lname+"'";
				ps1 = con.prepareStatement(q2);
				rs1 = ps1.executeQuery(q2);
				
				
				}
			}
			
		catch(SQLException e) {
				e.printStackTrace();	
		}
		int pph = Integer.parseInt(ep.split(" ")[1].split(":")[0]);
		int pp = Integer.parseInt(hours);
        if (pph >= pp && pph>16) {
    	 System.out.println(" You may exit the lot !! Thank you for visiting ");
    	 }
        else {
        System.out.println(" Expired time!! Please check the citations ");
        String qu2="SELECT lnumber FROM haspermits WHERE permitid='"+p_id+"'";
		try {
			pss1 = con.prepareStatement(qu2);
			resultSet = pss1.executeQuery(qu2);
			while(resultSet.next()) {
				String lnumber=resultSet.getString("lnumber");
			    String q1=" INSERT INTO CITATIONS VALUES(3100,'"+lnumber+"','"+lname+"',TIMESTAMP'"+cite_time+"',DATE'"+last_sent+"',DATE'"+due_date+"','U', 25,'"+zid+"','EP')";
				ps1 = con.prepareStatement(q1);
				rs1 = ps1.executeQuery(q1);
			}
		}
			catch (SQLException e) {
				e.printStackTrace();
				}
			
			
		}
        String delhpermit = "DELETE HASPERMITS WHERE permitid = '"+p_id+"'";
		try {
			ps2 = con.prepareStatement(delhpermit);
			ps2.executeUpdate(delhpermit);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		String delpermit = "DELETE PERMITS WHERE permitid = '"+p_id+"'";
		try {
			ps3 = con.prepareStatement(delpermit);
			ps3.executeUpdate(delpermit);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		//before permit expires we allow him to go , make space vacant , delete his entry(permits , haspermits) , if its not valid exit issue citation(inserting)
		
	}

}
