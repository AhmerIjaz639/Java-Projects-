package com.example.planner;

import MarkCompleteTask.MarkComplete;
import TaskCreation.TaskCreation;
import TaskCreation.TaskCreationMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class MarkCompleteController {

    @FXML private TableView<TaskCreation> taskTable;
    @FXML private TableColumn<TaskCreation, Integer> idColumn;
    @FXML private TableColumn<TaskCreation, String> nameColumn;
    @FXML private TableColumn<TaskCreation, String> completeColumn;
    @FXML private TextField taskIdField;
    @FXML private Label statusLabel;

    private ObservableList<TaskCreation> taskList;

    private static final String[] quotes = MarkComplete.motivationalQuotes;

    @FXML
    public void initialize() {
        taskList = FXCollections.observableArrayList(TaskCreationMain.tasklist);

        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getTaskId()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTask_name()));
        completeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().isComplete() ? "Yes" : "No"
        ));

        taskTable.setItems(taskList);
    }

    @FXML
    private void handleUpdate() {
        try {
            int taskId = Integer.parseInt(taskIdField.getText().trim());

            for (TaskCreation task : taskList) {
                if (task.getTaskId() == taskId) {
                    if (task.isComplete()) {
                        task.setComplete(false);
                        statusLabel.setText("✅ Task reopened.");
                    } else {
                        task.setComplete(true);
                        String randomQuote = quotes[new Random().nextInt(quotes.length)];
                        statusLabel.setText("🎉 Task marked complete. 💡 " + randomQuote);
                    }

                    taskTable.refresh();
                    return;
                }
            }

            statusLabel.setText(" No task found with ID: " + taskId);
        } catch (NumberFormatException e) {
            statusLabel.setText(" Please enter a valid number.");
        }
    }

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

    }

