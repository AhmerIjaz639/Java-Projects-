package AI;

import java.util.Scanner;

public class ChatApp {
    private static final ChatService chatService = new ChatService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        System.out.println("🤖 Welcome to AI Chat (Ollama - LLaMA 3). Type 'exit' to quit.");

        while (true) {
            System.out.print("\nYou: ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) break;

            System.out.print("AI: ");
            // Use a lock to synchronize output for smooth streaming
            final Object printLock = new Object();

            // Use a flag to indicate when the response is complete
            final boolean[] responseComplete = {false};

            chatService.sendMessage(
                    userInput,
                    chunk -> {
                        synchronized (printLock) {
                            System.out.print(chunk);
                            System.out.flush();
                        }
                    },
                    () -> {
                        synchronized (printLock) {
                            System.out.println("\n[✓] Response Complete.");
                            responseComplete[0] = true;
                        }
                    },
                    error -> {
                        synchronized (printLock) {
                            System.out.println("\n[✗] Error: " + error);
                            responseComplete[0] = true;
                        }
                    }
            );

            // Wait for response completion before allowing next input
            // Busy-wait with small sleep to keep CPU usage low
            while (!responseComplete[0]) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        System.out.println("👋 Goodbye!");
    }

}
