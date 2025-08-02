package MarkCompleteTask;

import TaskCreation.TaskCreation;

import java.util.List;
import java.util.Scanner;

public class MarkComplete {

    public static final String[] motivationalQuotes = {
            "Keep going, you’re doing great!",
            "Success is the sum of small efforts repeated daily.",
            "Every accomplishment starts with the decision to try.",
            "Stay positive, work hard, make it happen.",
            "Don't watch the clock; do what it does. Keep going.",
            "Believe you can and you’re halfway there.",
            "The harder you work for something, the greater you’ll feel when you achieve it.",
            "Dream it. Wish it. Do it.",
            "Don’t stop when you’re tired. Stop when you’re done.",
            "Success doesn’t come to you — you go to it.",
            "The only limit to our realization of tomorrow is our doubts of today.",
            "Push yourself, because no one else is going to do it for you.",
            "Great things never come from comfort zones.",
            "Don’t wait for opportunity. Create it.",
            "Sometimes we’re tested not to show our weaknesses, but to discover our strengths.",
            "Hard work beats talent when talent doesn’t work hard.",
            "Your limitation—it's only your imagination.",
            "Success is not final, failure is not fatal: It is the courage to continue that counts.",
            "You don’t have to be perfect to be amazing.",
            "If it doesn’t challenge you, it won’t change you.",
            "Work hard in silence, let success make the noise.",
            "Don’t wish for it, work for it.",
            "You are capable of amazing things.",
            "Sometimes the smallest step in the right direction ends up being the biggest step of your life.",
            "Don’t be afraid to give up the good to go for the great.",
            "The future depends on what you do today.",
            "What you get by achieving your goals is not as important as what you become by achieving your goals.",
            "Success is the result of preparation, hard work, and learning from failure.",
            "Little by little, a little becomes a lot.",
            "The difference between ordinary and extraordinary is that little extra.",
            "Don’t limit your challenges. Challenge your limits.",
            "You don’t have to see the whole staircase, just take the first step.",
            "Do something today that your future self will thank you for.",
            "Start where you are. Use what you have. Do what you can.",
            "The best time to plant a tree was 20 years ago. The second best time is now.",
            "Difficult roads often lead to beautiful destinations.",
            "Believe in yourself and all that you are.",
            "Your only limit is you.",
            "Success is walking from failure to failure with no loss of enthusiasm.",
            "Motivation is what gets you started. Habit is what keeps you going.",
            "Don’t count the days, make the days count.",
            "Act as if what you do makes a difference. It does.",
            "Never stop doing your best just because someone doesn’t give you credit.",
            "There is no substitute for hard work.",
            "You are stronger than you think.",
            "Don’t watch the clock; do what it does. Keep going.",
            "Don’t be pushed around by the fears in your mind. Be led by the dreams in your heart.",
            "Success isn’t overnight. It’s when every day you get a little better than the day before.",
            "The secret of getting ahead is getting started.",
            "Don’t let yesterday take up too much of today.",
            "Failure is the condiment that gives success its flavor.",
            "Strive for progress, not perfection.",
            "Every day may not be good, but there is something good in every day.",
            "The best way to get started is to quit talking and begin doing.",
            "You miss 100% of the shots you don’t take.",
            "Don’t let what you cannot do interfere with what you can do.",
            "To be successful, you must accept all challenges that come your way.",
            "Work until your idols become your rivals.",
            "Your dreams don’t work unless you do.",
            "Don’t stop until you’re proud.",
            "Don’t quit. Suffer now and live the rest of your life as a champion.",
            "Success is liking yourself, liking what you do, and liking how you do it.",
            "The only way to do great work is to love what you do.",
            "Success usually comes to those who are too busy to be looking for it.",
            "It always seems impossible until it’s done.",
            "The harder the battle, the sweeter the victory.",
            "If you want it, work for it.",
            "Don’t wait for the perfect moment, take the moment and make it perfect.",
            "Success is not in what you have, but who you are.",
            "You don’t find willpower, you create it.",
            "The pain you feel today will be the strength you feel tomorrow.",
            "Great things take time. Be patient and keep pushing.",
            "Sometimes later becomes never. Do it now.",
            "Your attitude determines your direction.",
            "Keep your face always toward the sunshine—and shadows will fall behind you.",
            "The difference between a successful person and others is not a lack of strength, not a lack of knowledge, but rather a lack in will.",
            "Don’t be afraid to fail. Be afraid not to try.",
            "You were born to be real, not perfect.",
            "Start where you are. Use what you have. Do what you can.",
            "Don’t stop believing.",
            "What lies behind us and what lies before us are tiny matters compared to what lies within us.",
            "Success is no accident. It is hard work, perseverance, learning, studying, sacrifice and most of all, love of what you are doing.",
            "Work hard, stay positive, and get up early. It’s the best part of the day.",
            "The best revenge is massive success.",
            "Difficulties in life are intended to make us better, not bitter.",
            "Keep your eyes on the stars and your feet on the ground.",
            "The best way out is always through.",
            "Don’t let fear decide your future.",
            "If opportunity doesn’t knock, build a door.",
            "The difference between try and triumph is a little “umph.”",
            "Keep moving forward.",
            "Don’t be discouraged. It’s often the last key in the bunch that opens the lock.",
            "Your life does not get better by chance, it gets better by change.",
            "You are braver than you believe, stronger than you seem, and smarter than you think.",
            "Success is the progressive realization of a worthy goal or ideal.",
            "Every strike brings me closer to the next home run.",
            "Don’t give up. Great things take time.",
            "You only fail when you stop trying.",
            "Believe in your infinite potential. Your only limitations are those you set upon yourself.",
            "Don’t be afraid to start over. It’s a new chance to rebuild what you want."
    };


    public static void markCompleteOrReopen(List<TaskCreation> tasklist) {
        Scanner scanner = new Scanner(System.in);

        if (tasklist.isEmpty()) {
            System.out.println("No tasks available to update.");
            return;
        }

        System.out.println("\nTasks:");
        for (TaskCreation task : tasklist) {
            System.out.printf("ID: %d | Name: %s | Completed: %s\n",
                    task.getTaskId(), task.getTask_name(), task.isComplete() ? "Yes" : "No");
        }

        System.out.print("Enter Task ID to update completion status: ");
        int id = Integer.parseInt(scanner.nextLine());

        TaskCreation selectedTask = null;
        for (TaskCreation task : tasklist) {
            if (task.getTaskId() == id) {
                selectedTask = task;
                break;
            }
        }

        if (selectedTask == null) {
            System.out.println("No task found with ID: " + id);
            return;
        }

        if (selectedTask.isComplete()) {
            System.out.print("Task is already complete. Do you want to reopen it? (yes/no): ");
            String reopen = scanner.nextLine().trim().toLowerCase();
            if (reopen.equals("yes") || reopen.equals("y")) {
                selectedTask.setComplete(false);
                System.out.println("Task reopened successfully.");
            } else {
                System.out.println("Task status unchanged.");
            }
        } else {
            selectedTask.setComplete(true);
            System.out.println("Task marked as complete.");
            int index = (int) (Math.random() * motivationalQuotes.length);
            System.out.println("💡 Motivation: " + motivationalQuotes[index]);
        }
    }
}
