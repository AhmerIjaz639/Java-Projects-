package ViewTasks;
import TaskCreation.TaskCreation;

import java.util.List;

import static TaskCreation.TaskCreationMain.tasklist;

public class ViewAllTasks {

    public static void viewAllTasks(List<TaskCreation> taskList) {
        if (tasklist.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (TaskCreation task : tasklist) {
                System.out.println(task);
            }
        }
    }

}
