package MoodSelection;

import java.util.Scanner;

public class MoodSelector {

    public static String selectMood() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Select Your Current Mood:");
        System.out.println("1.  Happy");
        System.out.println("2.  Sad");
        System.out.println("3.  Angry");
        System.out.println("4.  Tired");
        System.out.println("5.  Motivated");
        System.out.println("6.  Neutral");

        System.out.print("Enter your choice (1-6): ");
        int choice = scanner.nextInt();

        String mood = "";

        switch (choice) {
            case 1 -> mood = "Happy";
            case 2 -> mood = "Sad";
            case 3 -> mood = "Angry";
            case 4 -> mood = "Tired";
            case 5 -> mood = "Motivated";
            case 6 -> mood = "Neutral";
            default -> {
                System.out.println(" Invalid choice. Please select between 1 to 6.");
                return " ";
            }
        }

        System.out.println(" You selected mood: " + mood);
        return mood;
    }
}