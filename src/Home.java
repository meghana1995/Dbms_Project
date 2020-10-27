import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import Services.ConnectionService;

public class Home {
	public static final Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException {
		displayHomePage();
	}

	public static void displayHomePage() throws SQLException {
		Connection con= ConnectionService.getConnection();
//		Scanner sc = new Scanner(System.in);
		

		int choice = 0;
	    
		do {
			System.out.println("1. Student");
			System.out.println("2. Employees");
			System.out.println("3. UPS Admin");
			System.out.println("4. Visitor");
			System.out.println("5. Demo Queries");
			System.out.println("6. Exit");
			System.out.println("Please enter you choice: ");

			choice = sc.nextInt();
			if (choice == 1) {
				student.login(con);
			} else if (choice == 2) {
				employees.login(con);
			} else  if(choice == 3){
				admin.login(con);
			}else  if(choice == 4){
				visitor.main(con);
			}else  if(choice == 5){
				demoqueries d = new demoqueries();
				d.displaydemoqueries(con);
			}
			else if(choice == 6) {
				System.out.println("Thank you!");
				ConnectionService.closeConnection();
				break;
			}
			else {
				System.out.println("Invalid choice");
			}
			
		} while (choice != 6 );
		
		ConnectionService.closeConnection();
//		sc.close();
	}

}