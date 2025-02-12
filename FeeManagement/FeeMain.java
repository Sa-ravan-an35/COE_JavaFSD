package FeeDetails;

import java.sql.SQLException;
import java.util.Scanner;

public class FeeMain {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    	try {
        while (true) {
            System.out.println("\n--- Fee Report System ---");
            System.out.println("1. Admin Login");
            System.out.println("2. Accountant Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    accountantMenu();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
           }
    	}catch (SQLException e){
            e.printStackTrace();
         }
    }

    private static void adminMenu() throws SQLException {
        System.out.print("Enter Admin Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = sc.nextLine();

        if (Admin.validateAdmin(username, password)) {
            System.out.println("\nWelcome Admin!");
            while (true) {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. Add Accountant");
                System.out.println("2. View Accountants");
                System.out.println("3. Logout");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                try {
			switch (choice) {
			case 1:
				System.out.print("Enter Name: ");
			        String name = sc.nextLine();
			        System.out.print("Enter Email: ");
				String email = sc.nextLine();
			        System.out.print("Enter Phone: ");
			        String phone = sc.nextLine();
				System.out.print("Enter Password: ");
			        String pass = sc.nextLine();
				try {
					Admin.addAccountant(name, email, phone, pass);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			 case 2:
				Admin.viewAccountants();
			        break;
			 case 3:
				return;
			default:
				System.out.println("Invalid choice! Try again.");
			}
		  } catch (SQLException e) {
			e.printStackTrace();
		}			
            }
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private static void accountantMenu() throws SQLException {
        System.out.println("\n--- Accountant Menu ---");
        System.out.println("1. Add Student");
        System.out.println("2. Check Due Fee");
        System.out.println("3. Logout");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Email: ");
                String email = sc.nextLine();
                System.out.print("Enter Course: ");
                String course = sc.nextLine();
                System.out.print("Enter Fee: ");
                double fee = sc.nextDouble();
                System.out.print("Enter Paid Amount: ");
                double paid = sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter Address: ");
                String address = sc.nextLine();
                System.out.print("Enter Phone: ");
                String phone = sc.nextLine();
                Student.addStudent(name, email, course, fee, paid, address, phone);
                break;
            case 2:
                Student.checkDueFee();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice! Try again.");
        }
    }
}

