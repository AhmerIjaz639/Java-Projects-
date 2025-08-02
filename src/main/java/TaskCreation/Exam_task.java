package TaskCreation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Exam_task extends TaskCreation{

    private String subject_name;
    private Date exam_date;

    public Exam_task(String task_name, Date due_date, String priority, boolean isComplete , String subject_name, Date exam_date) {
        super(task_name, due_date, priority, isComplete);
        this.subject_name=subject_name;
        this.exam_date=exam_date;

    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public Date getExam_date() {
        return exam_date;
    }

    public void setExam_date(Date exam_date) {
        this.exam_date = exam_date;
    }



    public void setDue_date(Date exam_date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(exam_date);
        cal.add(Calendar.DATE, -1); // Subtract 1 day
        this.setDue_date(cal.getTime());
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        return super.toString() + ", Subject: " + subject_name + ", Exam Date: " + formatter.format(exam_date);
    }


}
