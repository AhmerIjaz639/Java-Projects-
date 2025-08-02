package com.example.planner;

import TaskCreation.TaskCreationMain;
import TaskCreation.Exam_task;
import TaskCreation.Assignment_task;
import TaskCreation.General_task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TaskCreationController {

    @FXML private TextField taskNameField;
    @FXML private DatePicker dueDatePicker;
    @FXML private TextField dueTimeField;
    @FXML private TextField priorityField;

    // Exam Fields
    @FXML private VBox examFields;
    @FXML private TextField subjectField;
    @FXML private DatePicker examDatePicker;
    @FXML private TextField examTimeField;

    // Assignment Fields
    @FXML private VBox assignmentFields;
    @FXML private TextField courseField;
    @FXML private DatePicker submissionDatePicker;
    @FXML private TextField submissionTimeField;

    // General Task Fields
    @FXML private VBox generalFields;
    @FXML private TextArea descriptionField;

    // Common Controls
    @FXML private RadioButton examRadio;
    @FXML private RadioButton assignmentRadio;
    @FXML private RadioButton generalRadio;
    @FXML private ToggleGroup taskTypeGroup;
    @FXML private CheckBox isCompleteCheck;
    @FXML private Button saveTaskButton;

    @FXML
    public void initialize() {
        examFields.setVisible(false);
        examFields.setManaged(false);
        assignmentFields.setVisible(false);
        assignmentFields.setManaged(false);
        generalFields.setVisible(false);
        generalFields.setManaged(false);
    }

    @FXML
    public void onTaskTypeSelected(MouseEvent event) {
        boolean isExam = examRadio.isSelected();
        boolean isAssignment = assignmentRadio.isSelected();
        boolean isGeneral = generalRadio.isSelected();

        examFields.setVisible(isExam);
        examFields.setManaged(isExam);

        assignmentFields.setVisible(isAssignment);
        assignmentFields.setManaged(isAssignment);

        generalFields.setVisible(isGeneral);
        generalFields.setManaged(isGeneral);
    }

    private LocalTime parseTime(String timeStr) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            return LocalTime.parse(timeStr.toUpperCase(), formatter);
        } catch (Exception e) {
            throw new Exception("Invalid time format. Please use hh:mm AM/PM.");
        }
    }
    @FXML

    private void onViewTasks() {
        PlannerMain.loadScene("/com/example/planner/ViewTak.fxml", "View Tasks");
    }


    @FXML
    public void onSaveTask() {
        try {
            String taskName = taskNameField.getText();
            String priority = priorityField.getText();
            boolean isComplete = isCompleteCheck.isSelected();

            LocalDate dueDate = dueDatePicker.getValue();
            LocalTime dueTime = parseTime(dueTimeField.getText());
            Timestamp dueTimestamp = Timestamp.valueOf(dueDate.atTime(dueTime));

            if (examRadio.isSelected()) {
                String subject = subjectField.getText();
                LocalDate examDate = examDatePicker.getValue();
                LocalTime examTime = parseTime(examTimeField.getText());
                Timestamp examTimestamp = Timestamp.valueOf(examDate.atTime(examTime));

                Exam_task examTask = new Exam_task(taskName, dueTimestamp, priority, isComplete, subject, examTimestamp);
                TaskCreationMain.tasklist.add(examTask);

            } else if (assignmentRadio.isSelected()) {
                String course = courseField.getText();
                LocalDate submissionDate = submissionDatePicker.getValue();
                LocalTime submissionTime = parseTime(submissionTimeField.getText());
                Timestamp submissionTimestamp = Timestamp.valueOf(submissionDate.atTime(submissionTime));

                Assignment_task assignmentTask = new Assignment_task(taskName, dueTimestamp, priority, isComplete, course, submissionTimestamp);
                TaskCreationMain.tasklist.add(assignmentTask);

            } else if (generalRadio.isSelected()) {
                String description = descriptionField.getText();
                General_task generalTask = new General_task(taskName, dueTimestamp, priority, isComplete, description);
                TaskCreationMain.tasklist.add(generalTask);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Task created successfully!");
            alert.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error creating task: " + e.getMessage());
            alert.show();
        }
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
