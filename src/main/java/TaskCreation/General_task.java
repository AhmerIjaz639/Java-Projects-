package TaskCreation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class General_task extends TaskCreation {

    private String task_description;

    public General_task(String task_name, Date due_date, String priority, boolean isComplete, String task_description) {
        super(task_name, due_date, priority, isComplete);
        this.task_description = task_description;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }


    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        return super.toString() + ", Task Description: " + task_description + ", Due Date: " + formatter.format(getDue_date());
    }
}
