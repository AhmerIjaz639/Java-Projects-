package ViewTasks;

import TaskCreation.TaskCreation;

import java.text.SimpleDateFormat;
import java.util.*;
import static TaskCreation.TaskCreationMain.tasklist;

public class ByDates {

    public static List<String> filterTasksByDueDate(String option, String startDateStr, String endDateStr) {
        List<String> results = new ArrayList<>();
        Date today = new Date();
        Calendar cal = Calendar.getInstance();

        for (TaskCreation task : tasklist) {
            Date dueDate = task.getDue_date();

            if (option.equalsIgnoreCase("today")) {
                if (isSameDay(dueDate, today)) {
                    results.add(task.toString());
                }
            } else if (option.equalsIgnoreCase("week")) {
                cal.setTime(today);
                cal.add(Calendar.DAY_OF_YEAR, 7);
                Date endOfWeek = cal.getTime();

                if (dueDate.after(today) && dueDate.before(endOfWeek)) {
                    results.add(task.toString());
                }
            } else if (option.equalsIgnoreCase("custom")) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Date start = sdf.parse(startDateStr);
                    Date end = sdf.parse(endDateStr);

                    cal.setTime(end);
                    cal.set(Calendar.HOUR_OF_DAY, 23);
                    cal.set(Calendar.MINUTE, 59);
                    cal.set(Calendar.SECOND, 59);
                    end = cal.getTime();

                    if (!dueDate.before(start) && !dueDate.after(end)) {
                        results.add(task.toString());
                    }
                } catch (Exception e) {
                    // Optional: you might want to throw or handle this error differently
                    System.out.println("Invalid date format. Use dd-MM-yyyy.");
                    return Collections.emptyList();
                }
            }
        }

        if (results.isEmpty()) {
            results.add("No tasks found for selected due date option.");
        }

        return results;
    }

    private static boolean isSameDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }
}
