package SummaryOFTask;

import TaskCreation.TaskCreation;
import static TaskCreation.TaskCreationMain.tasklist;

public class SummaryOfWork {
    private static int total = 0;
    private static int completed = 0;
    private static int pending = 0;
    private static int highPriority = 0;

    private static void calculateSummary() {
        total = tasklist.size();
        completed = 0;
        pending = 0;
        highPriority = 0;

        for (TaskCreation task : tasklist) {
            if (task.isComplete()) completed++;
            else pending++;

            if (task.getPriority().equalsIgnoreCase("High")) highPriority++;
        }
    }

    public static int getTotalTasks() {
        calculateSummary();
        return total;
    }

    public static int getCompletedTasks() {
        return completed;
    }

    public static int getPendingTasks() {
        return pending;
    }

    public static int getHighPriorityTasks() {
        return highPriority;
    }

    public static void showSummary() {
        calculateSummary();
        double consistency = (total > 0) ? ((double) completed / total) * 100 : 0;

        System.out.println(" Summary:");
        System.out.println("Total Tasks: " + total +
                " | Completed: " + completed +
                " | Pending: " + pending +
                " | High Priority: " + highPriority);
        System.out.printf("Consistency: %.2f%%\n", consistency);
    }
}
