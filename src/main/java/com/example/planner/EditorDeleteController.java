package com.example.planner;

import TaskCreation.TaskCreation;
import TaskCreation.TaskCreationMain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class EditorDeleteController {

    @FXML private TableView<TaskCreation> taskTable;
    @FXML private TableColumn<TaskCreation, Integer> idColumn;
    @FXML private TableColumn<TaskCreation, String> nameColumn;
    @FXML private TableColumn<TaskCreation, String> priorityColumn;
    @FXML private TableColumn<TaskCreation, String> dueDateColumn;
    @FXML private TableColumn<TaskCreation, Boolean> completeColumn;

    @FXML private TextField nameField;
    @FXML private TextField priorityField;
    @FXML private DatePicker dueDatePicker;
    @FXML private CheckBox completeCheckBox;

    private ObservableList<TaskCreation> taskObservableList;

    public void initialize() {
        // Set up table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("task_name"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        dueDateColumn.setCellValueFactory(cellData -> {
            Date date = cellData.getValue().getDue_date();
            String formatted = new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(date);
            return new javafx.beans.property.SimpleStringProperty(formatted);
        });
        completeColumn.setCellValueFactory(new PropertyValueFactory<>("complete"));

        // Load tasks
        taskObservableList = FXCollections.observableArrayList(TaskCreationMain.getTaskList());
        taskTable.setItems(taskObservableList);

        // Populate fields on selection
        taskTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> populateFields(newVal));
    }

    private void populateFields(TaskCreation task) {
        if (task != null) {
            nameField.setText(task.getTask_name());
            priorityField.setText(task.getPriority());
            dueDatePicker.setValue(new java.sql.Date(task.getDue_date().getTime()).toLocalDate());
            completeCheckBox.setSelected(task.isComplete());
        }
    }

    @FXML
    private void handleEdit() {
        TaskCreation selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean changed = false;

            String newName = nameField.getText();
            String newPriority = priorityField.getText();
            LocalDate newDueDate = dueDatePicker.getValue();
            boolean newComplete = completeCheckBox.isSelected();

            if (newName != null && !newName.equals(selected.getTask_name()) && !newName.isBlank()) {
                selected.setTask_name(newName);
                changed = true;
            }

            if (newPriority != null && !newPriority.equals(selected.getPriority()) && !newPriority.isBlank()) {
                selected.setPriority(newPriority);
                changed = true;
            }

            if (newDueDate != null) {
                Date convertedDate = java.sql.Date.valueOf(newDueDate);
                if (!convertedDate.equals(selected.getDue_date())) {
                    selected.setDue_date(convertedDate);
                    changed = true;
                }
            }

            if (newComplete != selected.isComplete()) {
                selected.setComplete(newComplete);
                changed = true;
            }

            if (changed) {
                taskTable.refresh();
                clearFields();
                showAlert("Task Edited", "Task updated successfully.");
            } else {
                showAlert("No Changes", "No modifications were made.");
            }
        }
    }


    @FXML
    private void handleDelete() {
        TaskCreation selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setHeaderText("Confirm Deletion");
            confirm.setContentText("Are you sure you want to delete task: " + selected.getTask_name() + "?");
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    taskObservableList.remove(selected);
                    TaskCreationMain.getTaskList().remove(selected); // backend removal
                    taskTable.refresh();
                    clearFields();
                }
            });
        }
    }

    private void clearFields() {
        nameField.clear();
        priorityField.clear();
        dueDatePicker.setValue(null);
        completeCheckBox.setSelected(false);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private Button backButton;

    @FXML
    private void handleBackButton() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/com/example/planner/DashBoard.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = (javafx.stage.Stage) backButton.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.setTitle("TO Dash Board");

        } catch (java.io.IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load Task Creation screen.");
            alert.show();
        }
    }
}
