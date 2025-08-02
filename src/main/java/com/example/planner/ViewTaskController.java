package com.example.planner;

import ViewTasks.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewTaskController {

    @FXML private ComboBox<String> filterComboBox;
    @FXML private TextField inputField;
    @FXML private DatePicker startDatePicker, endDatePicker;
    @FXML private ListView<String> resultList;

    @FXML private HBox datePickersBox;

    @FXML
    public void initialize() {
        filterComboBox.setItems(FXCollections.observableArrayList(
                "Search by Name", "Filter by Priority", "Filter by Task Type",
                "Due Today", "Due This Week", "Custom Date Range", "Completion Status"
        ));

        inputField.setVisible(false);
        datePickersBox.setVisible(false);

        filterComboBox.setOnAction(e -> {
            String selected = filterComboBox.getValue();

            inputField.setVisible(false);
            datePickersBox.setVisible(false);

            switch (selected) {
                case "Search by Name", "Filter by Priority", "Filter by Task Type", "Completion Status" -> {
                    inputField.setVisible(true);
                    inputField.setPromptText(getPromptText(selected));
                }
                case "Custom Date Range" -> {
                    datePickersBox.setVisible(true);
                }
            }
        });
    }


    private String getPromptText(String filterType) {
        return switch (filterType) {
            case "Search by Name" -> "Enter task name";
            case "Filter by Priority" -> "Enter priority (High/Medium/Low)";
            case "Filter by Task Type" -> "Enter task type (Exam/Assignment/General)";
            case "Completion Status" -> "Enter status (Completed/Incomplete)";
            default -> "";
        };
    }

    @FXML
    private void onApplyFilter() {
        try {
            String selected = filterComboBox.getValue();
            resultList.getItems().clear();

            if (selected == null || selected.isBlank()) {
                showAlert("Please select a filter type.");
                return;
            }

            List<String> results;

            switch (selected) {
                case "Search by Name" -> {
                    String name = inputField.getText();
                    if (name == null || name.isBlank()) {
                        showAlert("Please enter a task name.");
                        return;
                    }
                    results = SearchbyName.searchTaskByName(name);
                    showResults(results);
                }

                case "Filter by Priority" -> {
                    String priority = inputField.getText();
                    if (priority == null || priority.isBlank()) {
                        showAlert("Please enter a priority (e.g., High, Medium, Low).");
                        return;
                    }
                    results = PirioritySearch.searchTaskByPriority(priority);
                    showResults(results);
                }

                case "Filter by Task Type" -> {
                    String type = inputField.getText();
                    if (type == null || type.isBlank()) {
                        showAlert("Please enter a task type (e.g., Exam, Assignment, General).");
                        return;
                    }
                    results = TaskType.searchByType(type);
                    showResults(results);
                }

                case "Due Today" -> {
                    results = ByDates.filterTasksByDueDate("today", null, null);
                    showResults(results);
                }

                case "Due This Week" -> {
                    results = ByDates.filterTasksByDueDate("week", null, null);
                    showResults(results);
                }

                case "Custom Date Range" -> {
                    if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
                        showAlert("Please select both start and end dates.");
                        return;
                    }
                    String start = startDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    String end = endDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    results = ByDates.filterTasksByDueDate("custom", start, end);
                    showResults(results);
                }

                case "Completion Status" -> {
                    String status = inputField.getText();
                    if (status == null || status.isBlank()) {
                        showAlert("Please enter status (Completed/Incomplete).");
                        return;
                    }
                    results = ByStatus.filterByCompletionStatus(status);
                    showResults(results);
                }

                default -> {
                    showAlert("Unknown filter option selected.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error applying filter: " + e.getMessage());
            alert.show();
        }
    }

    private void showResults(List<String> results) {
        if (results == null || results.isEmpty()) {
            resultList.getItems().add("No tasks found.");
        } else {
            resultList.getItems().addAll(results);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.show();
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




