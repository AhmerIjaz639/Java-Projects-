package MoodSelection;

import TaskCreation.TaskCreation;
import java.util.Date;
import static TaskCreation.TaskCreationMain.tasklist;

public class TaskSuggestionMood {
    public static void suggestTasksByMood(String mood) {
        Date now = new Date();
        boolean found = false;

        System.out.println("\n Suggested Tasks for Mood: " + mood);

        for (TaskCreation task : tasklist) {
            String priority = task.getPriority();
            Date dueDate = task.getDue_date();

            switch (mood.toLowerCase()) {
                case "motivated" -> {
                    if (priority.equalsIgnoreCase("High") && dueDate.before(now)) {
                        System.out.println(task);
                        found = true;
                    }
                }
                case "tired", "sad" -> {
                    long diff = dueDate.getTime() - now.getTime();
                    long hoursLeft = diff / (1000 * 60 * 60);
                    if ((priority.equalsIgnoreCase("Low") || priority.equalsIgnoreCase("Medium")) && hoursLeft > 24) {
                        System.out.println(task);
                        found = true;
                    }
                }
                case "happy", "neutral" -> {
                    if (priority.equalsIgnoreCase("Medium")) {
                        System.out.println(task);
                        found = true;
                    }
                }
                case "angry" -> {
                    long diff = dueDate.getTime() - now.getTime();
                    long hoursLeft = diff / (1000 * 60 * 60);
                    if (priority.equalsIgnoreCase("Low") && hoursLeft <= 48) {
                        System.out.println(task);
                        found = true;
                    }
                }
            }
        }


        if (!found) {
            System.out.println(" No tasks matched exactly. Showing fallback suggestions:");

            for (TaskCreation task : tasklist) {
                String priority = task.getPriority();
                Date dueDate = task.getDue_date();

                switch (mood.toLowerCase()) {
                    case "motivated" -> {
                        if (priority.equalsIgnoreCase("Medium") && dueDate.after(now)) {
                            System.out.println(task);
                            found = true;
                        }
                    }
                    case "sad", "tired" -> {
                        if (priority.equalsIgnoreCase("Medium")) {
                            System.out.println(task);
                            found = true;
                        }
                    }
                    case "happy", "neutral" -> {
                        if (dueDate.after(now)) {
                            System.out.println(task);
                            found = true;
                        }
                    }
                    case "angry" -> {
                        long diff = dueDate.getTime() - now.getTime();
                        long hoursLeft = diff / (1000 * 60 * 60);
                        if (hoursLeft <= 72) {
                            System.out.println(task);
                            found = true;
                        }
                    }
                }
            }

            if (!found) {
                System.out.println(" Still no fallback tasks available for this mood.");
            }
        }
    }
}
