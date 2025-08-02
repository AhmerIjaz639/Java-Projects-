package PomodoroTimer;

import java.util.Scanner;

public class TMain {
    public static void Timer() {
        Scanner scanner = new Scanner(System.in);
        PomodoroTimer timer = new PomodoroTimer();

        System.out.print("Enter custom time in minutes (press Enter for default 25): ");
        String input = scanner.nextLine();

        if (!input.isEmpty()) {
            try {
                int minutes = Integer.parseInt(input);
                timer.setCustomTime(minutes);
                System.out.println("Timer set to " + minutes + " minutes.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Using default 25 minutes.");
            }
        } else {
            System.out.println("Using default 25 minutes.");
        }

        timer.start();
        System.out.println("Timer started! Press Enter to stop...");
        scanner.nextLine();

        timer.stop();
        System.out.println("Timer stopped.");
        System.out.println("Remaining Time: " + timer.getTime());
    }
}
