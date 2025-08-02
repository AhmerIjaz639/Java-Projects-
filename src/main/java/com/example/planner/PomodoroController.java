package com.example.planner;

import PomodoroTimer.PomodoroTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class PomodoroController {
    @FXML private Label timeLabel;
    @FXML private TextField customTimeField;

    private PomodoroTimer timer = new PomodoroTimer();
    private Timeline timeline;

    @FXML
    public void initialize() {
        updateLabel();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateLabel()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    @FXML
    private void handleStart() {
        if (!timer.isRunning()) {
            if (!customTimeField.getText().isEmpty()) {
                try {
                    int mins = Integer.parseInt(customTimeField.getText());
                    timer.setCustomTime(mins);
                } catch (NumberFormatException ignored) {}
            }
            timer.start();
            timeline.play();
        }
    }

    @FXML
    private void handleStop() {
        timer.stop();
        updateLabel();
        timeline.stop();
    }

    @FXML
    private void handleReset() {
        timer.reset();
        updateLabel();
        timeline.stop();
    }

    @FXML
    private Button backButton;
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


    private void updateLabel() {
        timeLabel.setText(timer.getTime());
    }
}
