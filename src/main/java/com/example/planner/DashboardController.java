package com.example.planner;

import Notifications.TaskReminderScheduler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import SummaryOFTask.SummaryOfWork;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static TaskCreation.TaskCreationMain.tasklist;

public class DashboardController {

    @FXML private Label clockLabel;
    @FXML private StackPane contentArea;
    @FXML private Label totalTasksLabel, completedTasksLabel, pendingTasksLabel, highPriorityLabel;
    @FXML private Label welcomeText;
    @FXML
    private ImageView welcomeImageView;


    public void initialize() {
        startClock();
        loadTaskSummary();
        loadImage();
        TaskReminderScheduler.startReminderCheck();
        setWelcomeMessage("Welcome to the Dashboard!");
    }


    private void loadImage() {
        try {
            Image image = new Image(getClass().getResource("/com/example/planner/dash.png").toExternalForm());
            welcomeImageView.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Image not found!");
        }
    }
    @FXML
    private void loadTaskSummary() {
        int total = SummaryOfWork.getTotalTasks();
        int completed = SummaryOfWork.getCompletedTasks();
        int pending = SummaryOfWork.getPendingTasks();
        int highPriority = SummaryOfWork.getHighPriorityTasks();

        updateSummary(total, completed, pending, highPriority);
    }

    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            clockLabel.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a ")));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    public void handleLogout() {
        System.out.println("Logging out...");
    }




    public void showTaskCreation() {
        PlannerMain. loadScene("/com/example/planner/TaskCreation.fxml", "Task Creation Panel");
    }

    public  void onViewTasks() {
        PlannerMain.loadScene("/com/example/planner/ViewTak.fxml", "View Tasks");
    }

    public void showPomodoro() {
        PlannerMain.loadScene("/com/example/planner/timer.fxml", "Timer");
    }

    public void showAIHelper() {
        PlannerMain.loadScene("/com/example/planner/chat.fxml", "AI helper");
    }

    public void showNotes() {

        PlannerMain.loadScene("/com/example/planner/NotesMenu.fxml", "Notes");


    }

    public void showMood() {
        PlannerMain.loadScene("/com/example/planner/Mood.fxml", "Mood ");
    }



    private void updateSummary(int total, int completed, int pending, int highPriority) {
        totalTasksLabel.setText("Total: " + total);
        completedTasksLabel.setText("Completed: " + completed);
        pendingTasksLabel.setText("Pending: " + pending);
        highPriorityLabel.setText("High Priority: " + highPriority);
    }
    public void setWelcomeMessage(String message) {

        welcomeText.setText(message);
    }

    @FXML
    private void onMarkComplete() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/planner/markcomplete.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();

            Scene scene = new Scene(root, 900, 650);  // Set size here
            stage.setScene(scene);

            stage.setTitle("Mark Task Complete");
            stage.setResizable(false);  // Disable resizing

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onEditorDelete() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/planner/EditorDelete.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();

            Scene scene = new Scene(root, 900, 650);
            stage.setScene(scene);

            stage.setTitle("Mark Task Complete");
            stage.setResizable(false);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSearchTask() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/planner/Search.fxml"));
            Parent root = loader.load();

            SearchController controller = loader.getController();
            controller.setTaskList(tasklist);

            Stage stage = new Stage();
            Scene scene = new Scene(root, 900, 650);
            stage.setScene(scene);
            stage.setTitle("Mark Task Complete");
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
