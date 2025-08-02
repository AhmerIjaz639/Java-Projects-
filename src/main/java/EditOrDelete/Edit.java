package EditOrDelete;

import TaskCreation.TaskCreation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Edit {

    // ✅ Method for Console-based editing
    public static void editTask(List<TaskCreation> tasklist) {
        Scanner scanner = new Scanner(System.in);

        if (tasklist.isEmpty()) {
            System.out.println("No tasks to edit.");
            return;
        }

        System.out.println("\nAvailable Tasks:");
        for (TaskCreation task : tasklist) {
            System.out.println("ID: " + task.getTaskId() + " | Name: " + task.getTask_name());
        }

        System.out.print("\nEnter the ID of the task you want to edit: ");
        int inputId;
        try {
            inputId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric ID.");
            return;
        }

        TaskCreation selectedTask = null;
        for (TaskCreation task : tasklist) {
            if (task.getTaskId() == inputId) {
                selectedTask = task;
                break;
            }
        }

        if (selectedTask == null) {
            System.out.println("No task found with ID: " + inputId);
            return;
        }

        System.out.println("\nLeave input blank to retain existing values.");

        System.out.print("Enter new task name (current: \"" + selectedTask.getTask_name() + "\"): ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            selectedTask.setTask_name(newName.trim());
        }

        System.out.print("Enter new due date and time (dd-MM-yyyy hh:mm AM/PM) (current: " +
                new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(selectedTask.getDue_date()) + "): ");
        String dateInput = scanner.nextLine();
        if (!dateInput.trim().isEmpty()) {
            Date newDate = parseDate(dateInput.trim());
            if (newDate != null) {
                selectedTask.setDue_date(newDate);
            }
        }

        System.out.print("Enter new priority (current: \"" + selectedTask.getPriority() + "\"): ");
        String newPriority = scanner.nextLine();
        if (!newPriority.trim().isEmpty()) {
            selectedTask.setPriority(newPriority.trim());
        }

        System.out.print("Mark as complete? (true/false) (current: " + selectedTask.isComplete() + "): ");
        String completeInput = scanner.nextLine();
        if (!completeInput.trim().isEmpty()) {
            selectedTask.setComplete(Boolean.parseBoolean(completeInput.trim()));
        }

        System.out.println("\nTask updated successfully!");
    }

    // ✅ Method for JavaFX usage with limited input (no due date)
    public static boolean editTaskById(List<TaskCreation> tasklist, int inputId) {
        Scanner scanner = new Scanner(System.in);

        TaskCreation selectedTask = null;
        for (TaskCreation task : tasklist) {
            if (task.getTaskId() == inputId) {
                selectedTask = task;
                break;
            }
        }

        if (selectedTask == null) {
            return false;
        }

        System.out.println("Editing task: " + selectedTask.getTask_name());

        System.out.print("Enter new task name (leave blank to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            selectedTask.setTask_name(newName.trim());
        }

        System.out.print("Enter new priority (leave blank to keep current): ");
        String newPriority = scanner.nextLine();
        if (!newPriority.trim().isEmpty()) {
            selectedTask.setPriority(newPriority.trim());
        }

        System.out.print("Mark as complete? (true/false) or leave blank to keep current: ");
        String completeInput = scanner.nextLine();
        if (!completeInput.trim().isEmpty()) {
            selectedTask.setComplete(Boolean.parseBoolean(completeInput.trim()));
        }

        return true;
    }

    private static Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy hh:mm a").parse(dateString);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy hh:mm AM/PM.");
            return null;
        }
    }
}
