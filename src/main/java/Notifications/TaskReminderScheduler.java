package Notifications;

import TaskCreation.TaskCreation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import static TaskCreation.TaskCreationMain.tasklist;

public class TaskReminderScheduler {

    private static final Set<String> notifiedTasks = new HashSet<>();
    private static boolean started = false;

    public static void startReminderCheck() {
        if (started) return;
        started = true;

        Timer timer = new Timer();

        TimerTask reminderTask = new TimerTask() {
            @Override
            public void run() {
                Date now = new Date();

                for (TaskCreation task : tasklist) {
                    if (task.isComplete()) continue;

                    Date dueDate = task.getDue_date();
                    long diff = dueDate.getTime() - now.getTime();
                    long minutesLeft = diff / (1000 * 60);

                    int[] reminderTimes = {30, 15, 5, 1};

                    for (int reminderTime : reminderTimes) {
                        if (minutesLeft == reminderTime) {
                            String taskKey = task.getTask_name() + "_" + reminderTime + "_" + dueDate.getTime();

                            if (!notifiedTasks.contains(taskKey)) {
                                showWindowsNotification("Task Reminder",
                                        "\"" + task.getTask_name() + "\" is due in " + reminderTime + " minute(s)!");
                                notifiedTasks.add(taskKey);
                            }
                        }
                    }
                }
            }
        };


        timer.scheduleAtFixedRate(reminderTask, 0, 10 * 1000);
    }

    private static TrayIcon trayIcon;

    private static void setupTrayIcon() throws AWTException {
        if (trayIcon != null) return; // already setup

        SystemTray tray = SystemTray.getSystemTray();
        Image image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        trayIcon = new TrayIcon(image, "Task Reminder");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Upcoming Task Notification");
        tray.add(trayIcon);
    }

    private static void showWindowsNotification(String title, String message) {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray not supported on this system.");
            return;
        }

        try {
            setupTrayIcon();
            trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);


            Toolkit.getDefaultToolkit().beep();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
