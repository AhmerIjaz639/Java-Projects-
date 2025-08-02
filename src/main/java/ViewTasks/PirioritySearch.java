package ViewTasks;

import TaskCreation.TaskCreation;
import static TaskCreation.TaskCreationMain.tasklist;
import java.util.ArrayList;
import java.util.List;

public class PirioritySearch {

    public static List<String> searchTaskByPriority(String priority) {
        List<String> results = new ArrayList<>();
        boolean found = false;

        for (TaskCreation task : tasklist) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                results.add(task.toString());
                found = true;
            }
        }

        if (!found) {
            results.add("No task found with the priority: " + priority);
        }
        return results;
    }
}
