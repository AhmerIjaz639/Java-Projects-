package com.example.planner;

import TaskCreation.TaskCreation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SearchController {

    @FXML private ComboBox<String> searchTypeCombo;
    @FXML private TextField searchField;
    @FXML private DatePicker datePicker;
    @FXML private TableView<TaskCreation> resultTable;
    @FXML private TableColumn<TaskCreation, Integer> idColumn;
    @FXML private TableColumn<TaskCreation, String> nameColumn;
    @FXML private TableColumn<TaskCreation, String> priorityColumn;
    @FXML private TableColumn<TaskCreation, Date> dueDateColumn;
    @FXML private TableColumn<TaskCreation, Boolean> completeColumn;

    private List<TaskCreation> taskList; // Inject/set via setter

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @FXML
    public void initialize() {
        // Initialize searchTypeCombo
        searchTypeCombo.setItems(FXCollections.observableArrayList("Task ID", "Task Name", "Priority", "Due Date"));
        searchTypeCombo.setOnAction(e -> toggleInputField());

        // Setup TableView columns
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getTaskId()).asObject());
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTask_name()));
        priorityColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPriority()));
        dueDateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDue_date()));
        completeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().isComplete()).asObject());

        // Format dueDateColumn to show formatted date string
        dueDateColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                setText(empty || date == null ? "" : sdf.format(date));
            }
        });

        // Initialize input fields visibility
        datePicker.setVisible(false);
        searchField.setVisible(true);
    }

    private void toggleInputField() {
        String type = searchTypeCombo.getValue();
        if ("Due Date".equals(type)) {
            searchField.clear();
            searchField.setVisible(false);
            datePicker.setVisible(true);
        } else {
            datePicker.setValue(null);
            datePicker.setVisible(false);
            searchField.setVisible(true);
        }
    }

    @FXML
    private void handleSearch() {
        ObservableList<TaskCreation> results = FXCollections.observableArrayList();

        String selected = searchTypeCombo.getValue();
        if (selected == null) {
            resultTable.setItems(results);  // Clear table if no search type selected
            return;
        }
        if (taskList == null) {

            System.out.println("Task list is not set!");
            return;
        }

        switch (selected) {
            case "Task ID" -> {
                try {
                    int id = Integer.parseInt(searchField.getText().trim());
                    taskList.stream()
                            .filter(t -> t.getTaskId() == id)
                            .findFirst()
                            .ifPresent(results::add);
                } catch (NumberFormatException ignored) {
                    // optionally show an error alert here
                }
            }
            case "Task Name" -> {
                String name = searchField.getText().trim().toLowerCase();
                results.addAll(taskList.stream()
                        .filter(t -> t.getTask_name().toLowerCase().contains(name))
                        .collect(Collectors.toList()));
            }
            case "Priority" -> {
                String priority = searchField.getText().trim().toLowerCase();
                results.addAll(taskList.stream()
                        .filter(t -> t.getPriority().toLowerCase().equals(priority))
                        .collect(Collectors.toList()));
            }
            case "Due Date" -> {
                LocalDate localDate = datePicker.getValue();
                if (localDate != null) {
                    String dateStr = localDate.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    results.addAll(taskList.stream()
                            .filter(t -> sdf.format(t.getDue_date()).equals(dateStr))
                            .collect(Collectors.toList()));
                }
            }
        }

        resultTable.setItems(results);
    }

    public void setTaskList(List<TaskCreation> taskList) {
        this.taskList = taskList;
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
