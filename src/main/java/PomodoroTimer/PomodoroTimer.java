package PomodoroTimer;


import java.time.Duration;
import java.time.LocalTime;

public class PomodoroTimer {
    private int totalSeconds;
    private int remainingSeconds;
    private boolean running = false;
    private LocalTime startTime;

    public PomodoroTimer() {
        this.totalSeconds = 25 * 60; // default 25 minutes
        this.remainingSeconds = totalSeconds;
    }

    // Set custom time in minutes
    public void setCustomTime(int minutes) {
        this.totalSeconds = minutes * 60;
        this.remainingSeconds = totalSeconds;
        this.running = false;
    }

    // Start the timer
    public void start() {
        if (!running) {
            this.startTime = LocalTime.now();
            this.running = true;
        }
    }

    // Stop/pause the timer
    public void stop() {
        if (running) {
            int elapsed = (int) Duration.between(startTime, LocalTime.now()).getSeconds();
            remainingSeconds -= elapsed;
            if (remainingSeconds < 0) remainingSeconds = 0;
            running = false;
        }
    }

    // Reset the timer
    public void reset() {
        this.remainingSeconds = totalSeconds;
        this.running = false;
    }

    // Get remaining time in HH:MM:SS
    public String getTime() {
        int current = running ? remainingSeconds - (int) Duration.between(startTime, LocalTime.now()).getSeconds() : remainingSeconds;
        if (current < 0) current = 0;

        int minutes = current / 60;
        int seconds = current % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public boolean isRunning() {
        return running;
    }


}
