package TaskCreation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Assignment_task extends TaskCreation {

    private String course_name;
    private Date submission_date;

    public Assignment_task(String task_name, Date due_date, String priority, boolean isComplete, String course_name, Date submission_date) {
        super(task_name, due_date, priority, isComplete);
        this.course_name = course_name;
        this.submission_date = submission_date;
        setDue_date(submission_date);
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Date getSubmission_date() {
        return submission_date;
    }

    public void setSubmission_date(Date submission_date) {
        this.submission_date = submission_date;
        setDue_date(submission_date);
    }

    @Override
    public void setDue_date(Date submission_date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(submission_date);
        cal.add(Calendar.DATE, -1);
        super.setDue_date(cal.getTime());
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");

        return super.toString() + ", Course: " + course_name + ", Submission Date: " + formatter.format(submission_date);
    }
}
