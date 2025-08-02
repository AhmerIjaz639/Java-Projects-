package ViewTasks;

import java.util.Scanner;

public class ViewTaskMain {

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- View Task Menu ---");
            System.out.println("1. Search by Name");
            System.out.println("2. Filter by Priority");
            System.out.println("3. Filter by Task Type");
            System.out.println("4. Filter by Due Date (Today/This Week)");
            System.out.println("5. Filter by Completion Status");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice (1-6): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    SearchbyName.searchTaskByName(name);
                }
                case "2" -> {
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    PirioritySearch.searchTaskByPriority(priority);
                }
                case "3" -> {
                    System.out.print("Enter task type (Exam/Assignment/General): ");
                    String type = scanner.nextLine();
                    TaskType.searchByType(type);
                }
                case "4" -> {
                    System.out.println("a. Today");
                    System.out.println("b. This Week");
                    System.out.println("c. Custom Range");
                    System.out.print("Choose (a/b/c): ");
                    String dateChoice = scanner.nextLine();

                    if (dateChoice.equalsIgnoreCase("a")) {
                        ByDates.filterTasksByDueDate("today", null, null);
                    } else if (dateChoice.equalsIgnoreCase("b")) {
                        ByDates.filterTasksByDueDate("week", null, null);
                    } else if (dateChoice.equalsIgnoreCase("c")) {
                        System.out.print("Enter start date (dd-MM-yyyy): ");
                        String start = scanner.nextLine();
                        System.out.print("Enter end date (dd-MM-yyyy): ");
                        String end = scanner.nextLine();
                        ByDates.filterTasksByDueDate("custom", start, end);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }

                case "5" -> {
                    System.out.print("Enter status (Completed/Incomplete): ");
                    String status = scanner.nextLine();
                    ByStatus.filterByCompletionStatus(status);
                }
                case "6" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
