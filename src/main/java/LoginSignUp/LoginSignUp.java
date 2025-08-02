package LoginSignUp;
import java.util.Scanner;

public class LoginSignUp {

        public static boolean authenticateUser() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to Smart Academic Planner!");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Choose an option (1 or 2): ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (UserDatabase.authenticate(username, password)) {
                    System.out.println("Login successful! Welcome, " + username + "!");
                    return true;
                } else {
                    System.out.println("Invalid credentials. Try again next time.");
                    return false;
                }

            } else if (choice.equals("2")) {
                System.out.print("Choose a username: ");
                String username = scanner.nextLine();

                if (UserDatabase.userExists(username)) {
                    System.out.println("Username already exists. Try logging in.");
                    return false;
                }

                System.out.print("Choose a password: ");
                String password = scanner.nextLine();
                UserDatabase.register(username, password);
                System.out.println("Sign up successful! Please log in next time.");
                return false;
            } else {
                System.out.println("Invalid choice. Exiting...");
                return false;
            }
        }
    }

