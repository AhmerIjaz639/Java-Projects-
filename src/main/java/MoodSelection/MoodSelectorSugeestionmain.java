package MoodSelection;

import java.util.Scanner;

public class MoodSelectorSugeestionmain {


        public static void MoodSelector() {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n     Mood-Based Task Suggestion Menu     ");
                System.out.println("1. Select Mood and View Suggestions");
                System.out.println("2. Back to Main Menu");
                System.out.print("Enter your choice (1-2): ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        String selectedMood = MoodSelector.selectMood();
                        if (!selectedMood.isEmpty()) {
                            TaskSuggestionMood.suggestTasksByMood(selectedMood);
                        }
                        break;

                    case "2":
                        return;

                    default:
                        System.out.println(" Invalid choice. Please try again.");
                        break;
                }

            }
        }
    }





