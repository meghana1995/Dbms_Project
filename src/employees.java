import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import Services.ConnectionService;

public class employees {
	

			public static void login(Connection con) throws SQLException {
				//Connection con = ConnectionService.getConnection();
				//Scanner sc = new Scanner(System.in);
				String univid, password;
				Home.sc.nextLine();
				System.out.println("Enter univid");
				univid = Home.sc.nextLine();	
				System.out.println("Enter password");
				password = Home.sc.nextLine();
				int userid = login.universityuserlogin(con, univid, password, "E");
				if (userid == -1) {
					System.out.println("Sign in incorrect");	
					ConnectionService.closeConnection();
					Home.displayHomePage();
				}
				//ConnectionService.closeConnection();
				
				displayHomePage(univid,con);
			}

			public static void displayHomePage(String univid, Connection con) throws SQLException {
				
				//Connection con = ConnectionService.getConnection();
				Scanner sc = new Scanner(System.in);
				int choice = 0;
				int number = 0;
				ResultSet resultSet = null;
				
				do {
					System.out.println("1. Pay citation");
					System.out.println("2. Change Vehicle List");
					System.out.println("3. Get permit");
					System.out.println("4. Exit");
					System.out.println("Please enter you choice: ");

					choice = sc.nextInt();
					if (choice == 1) {
						citation.paycitation(con,univid,"E");
                    } else if (choice == 2) {
                    	changeVehicleList(univid,con);            	
					} else if (choice == 3) {
						permits.getpermit("E",univid,con);
					} 
					else if(choice == 4) {
						System.out.println("Thank you!");
						break;
					}
					else {
						System.out.println("Invalid choice");
					}
					
				} while (choice != 4 );
				
				//ConnectionService.closeConnection();
				
			}
			
			
			public static void changeVehicleList(String univid, Connection con) throws SQLException {
					//Print existing vehicles
					int count=0;
					String p="";
					System.out.println("Existing vehicles in permit\n");
					String query = "select permitid, lnumber\n"
							+ "from haspermits h\n"
							+ "where h.id='"+univid+"'";      
					//System.out.println(query);
					PreparedStatement preparedStatement = null;
					ResultSet resultSet = null;
					try {
						preparedStatement = con.prepareStatement(query);
						resultSet = preparedStatement.executeQuery(query);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(resultSet.next()) {
						p = resultSet.getString("permitid");
					    String l = resultSet.getString("lnumber");
					    count++;
					    //count = resultSet.getInt("count");
					    System.out.println(p + "   "+l); 
					   while (resultSet.next()) {
					    p = resultSet.getString("permitid");
					    l = resultSet.getString("lnumber");
					    System.out.println(p + "   "+l);     
					    count++;
					}
					}else {
						System.out.println("You dont have any permits currently, get a permit!"); 
						
					}
					String lchoice, ln, model, manufacturer, manYear, color, vchoice;
					System.out.println("Do you want to 1)Add vehicles to the list 2) Delete current list  3) Exit");  
					int choice=0;
					choice = Home.sc.nextInt();
					System.out.println(choice);
					do {
						 
				         if(choice==1) {
							if(count==2) {
				    			System.out.println("\nYou have maximum vehicles alloted. Cannot add more vehicles");
							}
							else {
								do {
									if(count==2) {
										System.out.println("\nYou have maximum vehicles alloted. Cannot add more vehicles");
										break;
									}
									Home.sc.nextLine();
									System.out.println("\nEnter the license number ; ");
									ln = Home.sc.nextLine();
									System.out.println("Enter model of vehicle");
						  		    model = Home.sc.nextLine();
						  		    System.out.println("Enter vehicle manufacturer");
								    manufacturer = Home.sc.nextLine();
								    System.out.println("Enter year of manufacturing");
						  		    manYear = Home.sc.nextLine();
						  		    System.out.println("Enter color of vehicle");
								    color = Home.sc.nextLine();
									//update tables
								    PreparedStatement preparedStatement3 = null;
									String query3 =  "INSERT INTO VEHICLES VALUES ('"+ln+"','"+color+"','"+manYear+"','"+model+"','"+manufacturer+"')";
									System.out.println(query3);
									try {
										preparedStatement3 = con.prepareStatement(query3);
										preparedStatement3.executeUpdate(query3);
									} catch (SQLException e) {
									e.printStackTrace();
									}
									
									PreparedStatement preparedStatement4 = null;
									String query4 =  "INSERT INTO HASVEHICLES VALUES ('"+ln+"','"+univid+"')";
									try {
										preparedStatement4 = con.prepareStatement(query4);
										preparedStatement4.executeUpdate(query4);
									} catch (SQLException e) {
									e.printStackTrace();
									}
									
									PreparedStatement preparedStatement5 = null;
									//check whether he has permit
									String query5 =  "INSERT INTO HASPERMITS VALUES ('"+univid+"','"+p+"','"+ln+"')";
									try {
										preparedStatement5 = con.prepareStatement(query5);
										preparedStatement5.executeUpdate(query5);
									} catch (SQLException e) {
									e.printStackTrace();
									}
								    count++;
								    Home.sc.nextLine();
								    System.out.println("\ndo you want to add more veehicles(y/n)");
								    vchoice = Home.sc.nextLine();
								    System.out.println(vchoice);
								}while(vchoice!="n"||vchoice!="N");
							}
						}
						else if(choice==2) {
							System.out.println("\nEnter the license number of the vehicle you want to delete?");
							lchoice = Home.sc.nextLine();
							String query_hasvehicles = "delete from hasVehicles where lnumber = '"+lchoice+"'";
							preparedStatement = null;
				    		try {
				    			preparedStatement = con.prepareStatement(query_hasvehicles);
				    			preparedStatement.executeUpdate(query_hasvehicles);
				    			
				    		} catch (SQLException e) {
				    			e.printStackTrace();
				    		}
							String query_haspermits = "delete from hasPermits where lnumber = '"+lchoice+"'";
							preparedStatement = null;
				    		try {
				    			preparedStatement = con.prepareStatement(query_haspermits);
				    			preparedStatement.executeUpdate(query_haspermits);
				    			
				    		} catch (SQLException e) {
				    			e.printStackTrace();
				    		}
							String query_vehicles = "delete from vehicles where lnumber = '"+lchoice+"'";
							preparedStatement = null;
				    		try {
				    			preparedStatement = con.prepareStatement(query_vehicles);
				    			preparedStatement.executeUpdate(query_vehicles);
				    			
				    		} catch (SQLException e) {
				    			e.printStackTrace();
				    		}
				    		finally {
				    			count--;
				    		}
						}
				        Home.sc.nextLine();
						System.out.println("Do you want to 1)Add vehicles to the list 2) Delete current list  3) Exit"); 
						choice = Home.sc.nextInt();
						System.out.println(choice);
					}while (choice!=3);
					
					if(choice ==3) {
						displayHomePage(univid,con);
					}			
		}				
} 

