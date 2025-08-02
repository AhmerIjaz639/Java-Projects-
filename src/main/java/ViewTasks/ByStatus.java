package ViewTasks;

import TaskCreation.TaskCreation;
import static TaskCreation.TaskCreationMain.tasklist;
import java.util.ArrayList;
import java.util.List;

public class ByStatus {

    public static List<String> filterByCompletionStatus(String status) {
        List<String> results = new ArrayList<>();

        for (TaskCreation task : tasklist) {
            if (status.equalsIgnoreCase("completed") && task.isComplete()) {
                results.add(task.toString());
            } else if (status.equalsIgnoreCase("incomplete") && !task.isComplete()) {
                results.add(task.toString());
            }
        }

        if (results.isEmpty()) {
            results.add("No " + status + " tasks found.");
        }

        return results;
    }
}
