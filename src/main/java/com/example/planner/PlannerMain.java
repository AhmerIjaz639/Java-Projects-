package com.example.planner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.IOException;

public class PlannerMain extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;


        loadScene("/com/example/planner/Login.fxml", "Smart Planner");


        Notifications.TaskReminderScheduler.startReminderCheck();
    }

    public static void loadScene(String fxmlPath, String title) {
        try {

            if (fxmlPath == null || fxmlPath.isEmpty()) {
                throw new IllegalArgumentException("FXML path cannot be null or empty");
            }


            FXMLLoader loader = new FXMLLoader(PlannerMain.class.getResource(fxmlPath));
            if (loader.getLocation() == null) {
                throw new IOException("FXML file not found: " + fxmlPath);
            }
            primaryStage.setTitle("Study Planner");
            primaryStage.setWidth(900);
            primaryStage.setHeight(650);
            primaryStage.setResizable(false);



            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 500);
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();

            System.out.println("Successfully loaded scene: " + fxmlPath);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Loading Scene");
            alert.setHeaderText("Failed to load " + title);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}