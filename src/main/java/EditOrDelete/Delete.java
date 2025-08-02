package EditOrDelete;

import TaskCreation.TaskCreation;
import java.util.List;
import java.util.Scanner;

public class Delete {


    public static void deleteTask(List<TaskCreation> tasklist) {
        Scanner scanner = new Scanner(System.in);

        if (tasklist.isEmpty()) {
            System.out.println("No tasks to delete.");
            return;
        }

        System.out.println("Available Tasks:");
        for (TaskCreation task : tasklist) {
            System.out.println("ID: " + task.getTaskId() + " | Name: " + task.getTask_name());
        }

        System.out.print("Enter the ID of the task to delete: ");
        int idToDelete;
        try {
            idToDelete = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric ID.");
            return;
        }

        TaskCreation taskToRemove = null;
        for (TaskCreation task : tasklist) {
            if (task.getTaskId() == idToDelete) {
                taskToRemove = task;
                break;
            }
        }

        if (taskToRemove != null) {
            System.out.print("Are you sure you want to delete the task: '" +
                    taskToRemove.getTask_name() + "'? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (confirmation.equals("yes")) {
                tasklist.remove(taskToRemove);
                System.out.println("Task deleted successfully.");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("No task found with ID: " + idToDelete);
        }
    }


    public static boolean deleteTaskById(List<TaskCreation> tasklist, int idToDelete) {
        TaskCreation taskToRemove = null;

        for (TaskCreation task : tasklist) {
            if (task.getTaskId() == idToDelete) {
                taskToRemove = task;
                break;
            }
        }

        if (taskToRemove != null) {
            tasklist.remove(taskToRemove);
            return true;
        } else {
            return false;
        }
    }
}
