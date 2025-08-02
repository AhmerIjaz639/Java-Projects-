package ViewTasks;

import TaskCreation.TaskCreation;
import static TaskCreation.TaskCreationMain.tasklist;

import java.util.ArrayList;
import java.util.List;

public class SearchbyName {

    public static List<String> searchTaskByName(String name) {
        List<String> results = new ArrayList<>();
        boolean found = false;

        for (TaskCreation task : tasklist) {
            if (task.getTask_name().equalsIgnoreCase(name)) {
                results.add(task.toString());
                found = true;
            }
        }

        if (!found) {
            results.add("No task found with the name: " + name);
        }
        return results;
    }
}
