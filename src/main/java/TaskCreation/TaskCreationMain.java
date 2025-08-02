package TaskCreation;

import MarkCompleteTask.MarkComplete;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TaskCreationMain {

    public static List<TaskCreation> tasklist = new ArrayList<>();


    public static List<TaskCreation> getTaskList() {
        return tasklist;
    }

    public static void displayMenu() {
        System.out.println("Choose a task type to create:");
        System.out.println("1. Exam Task");
        System.out.println("2. Assignment Task");
        System.out.println("3. General Task");
        System.out.println("4. View All Tasks");
        System.out.println("5. Mark Task Complete or Reopen");
    }


    public static void handleUserInput(int choice, List<TaskCreation> tasklist) {
        switch (choice) {
            case 1 -> createExamTask(tasklist);
            case 2 -> createAssignmentTask(tasklist);
            case 3 -> createGeneralTask(tasklist);
            case 4 -> MarkComplete.markCompleteOrReopen(tasklist);
            default -> System.out.println("Invalid choice, please try again.");
        }
    }



    public static void createExamTask(List<TaskCreation> taskList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();

        System.out.print("Enter due date and time (dd-MM-yyyy hh:mm AM/PM): ");

        String dueDateString = scanner.nextLine();
        Date dueDate = parseDate(dueDateString);

        System.out.print("Enter priority: ");
        String priority = scanner.nextLine();

        System.out.print("Is the task complete (true/false): ");
        boolean isComplete = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter subject name: ");
        String subjectName = scanner.nextLine();

        System.out.print("Enter exam date (dd-MM-yyyy): ");
        String examDateString = scanner.nextLine();
        Date examDate = parseDate(examDateString);

        Exam_task examTask = new Exam_task(taskName, dueDate, priority, isComplete, subjectName, examDate);
        tasklist.add(examTask);
        System.out.println("Exam Task Created Successfully!");
    }

    public static void createAssignmentTask(List<TaskCreation> taskList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();

        System.out.print("Enter due date and time (dd-MM-yyyy hh:mm AM/PM): ");

        String dueDateString = scanner.nextLine();
        Date dueDate = parseDate(dueDateString);

        System.out.print("Enter priority: ");
        String priority = scanner.nextLine();

        System.out.print("Is the task complete (true/false): ");
        boolean isComplete = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();

        System.out.print("Enter submission date (dd-MM-yyyy): ");
        String submissionDateString = scanner.nextLine();
        Date submissionDate = parseDate(submissionDateString);

        Assignment_task assignmentTask = new Assignment_task(taskName, dueDate, priority, isComplete, courseName, submissionDate);
        tasklist.add(assignmentTask);
        System.out.println("Assignment Task Created Successfully!");
    }

    public static void createGeneralTask(List<TaskCreation> taskList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();

        System.out.print("Enter due date and time (dd-MM-yyyy hh:mm AM/PM): ");

        String dueDateString = scanner.nextLine();
        Date dueDate = parseDate(dueDateString);

        System.out.print("Enter priority: ");
        String priority = scanner.nextLine();

        System.out.print("Is the task complete (true/false): ");
        boolean isComplete = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter task description: ");
        String taskDescription = scanner.nextLine();

        General_task generalTask = new General_task(taskName, dueDate, priority, isComplete, taskDescription);
        tasklist.add(generalTask);
        System.out.println("General Task Created Successfully!");
    }

    public static Date parseDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm a");

            return formatter.parse(dateString);
        } catch (Exception e) {
            System.out.println("Invalid date format. Using today's date instead.");
            return new Date();
        }
    }


    public static List<TaskCreation> getTasks() {
        return tasklist;
    }
}

