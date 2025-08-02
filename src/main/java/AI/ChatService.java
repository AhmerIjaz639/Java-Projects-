package AI;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class ChatService {
    private Thread currentThread;

    public void sendMessage(String userMessage, Consumer<String> onChunkReceived, Runnable onComplete, Consumer<String> onError) {
        currentThread = new Thread(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL("http://localhost:11434/api/generate");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                String escapedPrompt = userMessage.replace("\"", "\\\"");
                String json = """
                {
                    "model": "llama3",
                    "prompt": "%s",
                    "stream": true
                }
                """.formatted(escapedPrompt);

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(json.getBytes(StandardCharsets.UTF_8));
                    os.flush();
                }

                int status = conn.getResponseCode();
                if (status == 200) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (Thread.currentThread().isInterrupted()) {
                                onError.accept("✋ Response stopped by user.");
                                return;
                            }

                            line = line.trim();
                            if (!line.isEmpty() && line.contains("\"response\":\"")) {
                                String chunk = extractResponseFromLine(line);
                                if (!chunk.isEmpty()) {
                                    onChunkReceived.accept(chunk);
                                }
                            }
                        }
                    }
                    onComplete.run();
                } else {
                    onError.accept("HTTP error: " + status);
                }

            } catch (IOException e) {
                if (!Thread.currentThread().isInterrupted()) {
                    onError.accept("Exception: " + e.getMessage());
                }
            } finally {
                if (conn != null) conn.disconnect();
            }
        }, "ChatService-Thread");

        currentThread.start();
    }

    public void stopCurrentResponse() {
        if (currentThread != null && currentThread.isAlive()) {
            currentThread.interrupt();
        }
    }

    private String extractResponseFromLine(String line) {
        try {
            int start = line.indexOf("\"response\":\"");
            if (start == -1) return "";

            start += "\"response\":\"".length();
            int end = start;
            boolean escape = false;

            while (end < line.length()) {
                char c = line.charAt(end);
                if (c == '"' && !escape) break;
                escape = (c == '\\' && !escape);
                if (c != '\\') escape = false;
                end++;
            }

            if (end <= start) return "";

            String raw = line.substring(start, end);
            return raw.replace("\\n", "\n")
                    .replace("\\\"", "\"")
                    .replace("\\\\", "\\");
        } catch (Exception e) {
            return "";
        }
    }
}
