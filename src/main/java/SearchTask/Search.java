package SearchTask;

import TaskCreation.TaskCreation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Search {

    public static void searchTask(List<TaskCreation> tasklist) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("\n Search Task by:");
        System.out.println("1. Task ID");
        System.out.println("2. Task Name");
        System.out.println("3. Priority");
        System.out.println("4. Due Date (dd-MM-yyyy)");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        boolean found = false;

        switch (choice) {
            case 1 -> {
                System.out.print("Enter Task ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                for (TaskCreation task : tasklist) {
                    if (task.getTaskId() == id) {
                        System.out.println(task);
                        found = true;
                        break;
                    }
                }
            }

            case 2 -> {
                System.out.print("Enter Task Name: ");
                String name = scanner.nextLine().trim().toLowerCase();
                for (TaskCreation task : tasklist) {
                    if (task.getTask_name().toLowerCase().contains(name)) {
                        System.out.println(task);
                        found = true;
                    }
                }
            }

            case 3 -> {
                System.out.print("Enter Priority (High/Medium/Low): ");
                String priority = scanner.nextLine().trim().toLowerCase();
                for (TaskCreation task : tasklist) {
                    if (task.getPriority().toLowerCase().equals(priority)) {
                        System.out.println(task);
                        found = true;
                    }
                }
            }

            case 4 -> {
                System.out.print("Enter Due Date (dd-MM-yyyy): ");
                String dateInput = scanner.nextLine().trim();
                try {
                    Date inputDate = sdf.parse(dateInput);
                    for (TaskCreation task : tasklist) {
                        if (sdf.format(task.getDue_date()).equals(dateInput)) {
                            System.out.println(task);
                            found = true;
                        }
                    }
                } catch (Exception e) {
                    System.out.println(" Invalid date format.");
                    return;
                }
            }

            default -> {
                System.out.println(" Invalid option.");
                return;
            }
        }

        if (!found) {
            System.out.println(" No matching tasks found.");
        }
    }


}
