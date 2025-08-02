package EditOrDelete;
import TaskCreation.Assignment_task;
import TaskCreation.Exam_task;
import TaskCreation.General_task;
import TaskCreation.TaskCreation;

import java.util.List;
import java.util.Scanner;

public class EditOrDeleteMain{

    public static void showEditOrDeleteMenu(List<TaskCreation> tasklist, Scanner scanner){
        System.out.println("1. Edit Task (by ID)");
        System.out.println("2. Delete Task (by ID)");
        System.out.print("Select an option (2 or 3): ");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                Edit.editTask(tasklist);
                break;
            case 2:
                Delete.deleteTask(tasklist);
                break;
            default:
                System.out.println("Invalid option selected.");
        }
    }


    public static void editExistingTask(List<TaskCreation> tasks) {
        Edit.editTask(tasks);
    }

    public static void deleteExistingTask(List<TaskCreation> tasks) {
        Delete.deleteTask(tasks);
    }
}
