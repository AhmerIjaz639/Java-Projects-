package ViewTasks;

import TaskCreation.Assignment_task;
import TaskCreation.Exam_task;
import TaskCreation.General_task;
import TaskCreation.TaskCreation;

import static TaskCreation.TaskCreationMain.tasklist;
import java.util.ArrayList;
import java.util.List;

public class TaskType {

    public static List<String> searchByType(String type) {
        List<String> results = new ArrayList<>();
        boolean found = false;

        for (TaskCreation task : tasklist) {
            if (type.equalsIgnoreCase("exam") && task instanceof Exam_task) {
                results.add(task.toString());
                found = true;
            } else if (type.equalsIgnoreCase("assignment") && task instanceof Assignment_task) {
                results.add(task.toString());
                found = true;
            } else if (type.equalsIgnoreCase("general") && task instanceof General_task) {
                results.add(task.toString());
                found = true;
            }
        }

        if (!found) {
            results.add("No tasks found of type: " + type);
        }
        return results;
    }
}
