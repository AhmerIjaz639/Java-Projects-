package TaskCreation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskCreation {

    private static int idCounter = 1;
    private int taskId;

    private String task_name;
    private Date due_date;
    private String priority;
    private boolean isComplete;

    public TaskCreation(String task_name, Date due_date, String priority, boolean isComplete) {
        this.taskId = idCounter++;
        this.task_name = task_name;
        this.due_date = due_date;
        this.priority = priority;
        this.isComplete = isComplete;
    }

    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int id) {
        this.taskId = id;
    }


    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");


    @Override
    public String toString() {
        return "Task ID: " + taskId +
                " | Task: " + task_name +
                " | Due: " + formatter.format(due_date) +
                " | Priority: " + priority +
                " | Completed: " + isComplete;
    }


}
