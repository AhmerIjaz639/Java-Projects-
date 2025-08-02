package com.example.planner;

import TaskCreation.TaskCreation;
import TaskCreation.TaskCreationMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MoodSuggestionController {

    @FXML
    private ComboBox<String> moodComboBox;

    @FXML
    private TextArea suggestionArea;
    @FXML
    private Button suggestButton;



    @FXML
    public void initialize() {
        moodComboBox.getItems().addAll("Happy", "Sad", "Angry", "Tired", "Motivated", "Neutral");
        suggestButton.setOnMouseEntered(e -> {
            suggestButton.setStyle("-fx-background-color: #1E90FF; -fx-text-fill: white; " +
                    "-fx-font-weight: bold; -fx-background-radius: 10; " +
                    "-fx-padding: 10 20; -fx-font-size: 14px; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0.3, 0, 2); " +
                    "-fx-cursor: hand;");
        });

        suggestButton.setOnMouseExited(e -> {
            suggestButton.setStyle("-fx-background-color: #00BFFF; -fx-text-fill: white; " +
                    "-fx-font-weight: bold; -fx-background-radius: 10; " +
                    "-fx-padding: 10 20; -fx-font-size: 14px; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0.3, 0, 2); " +
                    "-fx-cursor: hand;");
        });
    }

    @FXML
    private void handleMoodSuggestion() {
        String mood = moodComboBox.getValue();
        if (mood == null || mood.isBlank()) {
            suggestionArea.setText("Please select a mood first.");
            return;
        }

        StringBuilder output = new StringBuilder();
        Date now = new Date();
        boolean found = false;

        List<TaskCreation> tasklist = TaskCreationMain.tasklist;

        output.append("Suggested Tasks for Mood: ").append(mood).append("\n\n");

        for (TaskCreation task : tasklist) {
            String priority = task.getPriority();
            Date dueDate = task.getDue_date();
            long hoursLeft = (dueDate.getTime() - now.getTime()) / (1000 * 60 * 60);

            switch (mood.toLowerCase()) {
                case "motivated" -> {
                    if (priority.equalsIgnoreCase("High") && dueDate.before(now)) {
                        output.append(task).append("\n");
                        found = true;
                    }
                }
                case "sad", "tired" -> {
                    if ((priority.equalsIgnoreCase("Low") || priority.equalsIgnoreCase("Medium")) && hoursLeft > 24) {
                        output.append(task).append("\n");
                        found = true;
                    }
                }
                case "happy", "neutral" -> {
                    if (priority.equalsIgnoreCase("Medium")) {
                        output.append(task).append("\n");
                        found = true;
                    }
                }
                case "angry" -> {
                    if (priority.equalsIgnoreCase("Low") && hoursLeft <= 48) {
                        output.append(task).append("\n");
                        found = true;
                    }
                }
            }
        }

        if (!found) {
            output.append("No exact match. Showing fallback suggestions:\n\n");

            for (TaskCreation task : tasklist) {
                String priority = task.getPriority();
                Date dueDate = task.getDue_date();
                long hoursLeft = (dueDate.getTime() - now.getTime()) / (1000 * 60 * 60);

                switch (mood.toLowerCase()) {
                    case "motivated" -> {
                        if (priority.equalsIgnoreCase("Medium") && dueDate.after(now)) {
                            output.append(task).append("\n");
                            found = true;
                        }
                    }
                    case "sad", "tired" -> {
                        if (priority.equalsIgnoreCase("Medium")) {
                            output.append(task).append("\n");
                            found = true;
                        }
                    }
                    case "happy", "neutral" -> {
                        if (dueDate.after(now)) {
                            output.append(task).append("\n");
                            found = true;
                        }
                    }
                    case "angry" -> {
                        if (hoursLeft <= 72) {
                            output.append(task).append("\n");
                            found = true;
                        }
                    }
                }
            }

            if (!found) {
                output.append("Still no fallback tasks available for this mood.");
            }
        }

        suggestionArea.setText(output.toString());
    }



    private Button backButton;
    @FXML
    public void handleBackButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
