package Main;
import LoginSignUp.LoginSignUp;
import MoodSelection.MoodSelectorSugeestionmain;
import Notifications.TaskReminderScheduler;
import PomodoroTimer.PomodoroTimer;
import PomodoroTimer.TMain;
import SummaryOFTask.SummaryOfWork;
import ViewTasks.ViewTaskMain;
import EditOrDelete.EditOrDeleteMain;
import SearchTask.Search;
import TaskCreation.TaskCreationMain;
import TaskCreation.TaskCreation;
import NotesManager.NotesManager;
import AI.ChatService;
import AI.ChatApp;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        TaskReminderScheduler.startReminderCheck();
        Scanner scanner = new Scanner(System.in);


        boolean loggedIn = LoginSignUp.authenticateUser();

        if (!loggedIn) {
            System.out.println("Goodbye!");
            scanner.close();
            return;
        }

        boolean running = true;

        while (running) {
            clearConsole();

            System.out.println("\n==== Smart Academic Planner ====");
            System.out.println("1. Task Creation");
            System.out.println("2. Task Edit or Delete");
            System.out.println("3. Task Searcher");
            System.out.println("4. Notes");
            System.out.println("5. View Tasks ");
            System.out.println("6. Mood Based Suggestions");
            System.out.println("7. Summary of Tasks");
            System.out.println("8. Pomodoro Timer");
            System.out.println("9. AI Study Assistant ");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidatedChoice(scanner);

            switch (choice) {
                case 1 -> runTaskCreationModule(TaskCreationMain.tasklist, scanner);
                case 2 -> EditOrDeleteMain.showEditOrDeleteMenu(TaskCreationMain.tasklist, scanner);
                case 3 -> Search.searchTask(TaskCreationMain.tasklist);
                case 4 -> NotesManager.showNotesMenu();
                case 5 -> ViewTaskMain.showMenu();
                case 6 -> MoodSelectorSugeestionmain.MoodSelector();
                case 7 -> SummaryOfWork.showSummary();
                case 8 -> TMain.Timer();
                case 9 -> ChatApp.start();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting Smart Academic Planner. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    public static void runTaskCreationModule(List<TaskCreation> tasklist, Scanner scanner) {
        boolean inTaskModule = true;

        while (inTaskModule) {
            clearConsole();
            TaskCreationMain.displayMenu();
            System.out.println("6. Return to Main Menu");
            System.out.print("Choose an option: ");

            int subChoice = getValidatedChoice(scanner);

            if (subChoice == 6) {
                inTaskModule = false;
            } else {
                TaskCreationMain.handleUserInput(subChoice, tasklist);
            }

            if (inTaskModule) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private static int getValidatedChoice(Scanner scanner) {
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {

        }
    }
}
