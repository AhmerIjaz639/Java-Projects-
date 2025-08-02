package com.example.planner;


import NotesManager.NotesManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class NotesMenuController {

    private NotesManager notesManager = new NotesManager();

    @FXML
    private void openAddNote() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddNote.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Add Note");
            stage.show();


            AddNoteController controller = loader.getController();
            controller.setNotesManager(notesManager);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openViewNotes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewNote.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("View Notes");
            stage.show();

            ViewNotesController controller = loader.getController();
            controller.setNotesManager(notesManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openDeleteNote() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteNote.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Delete Note");
            stage.show();

            DeleteNoteController controller = loader.getController();
            controller.setNotesManager(notesManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openSearchNote() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchNote.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Search Note");
            stage.show();

            SearchNoteController controller = loader.getController();
            controller.setNotesManager(notesManager);
        } catch (Exception e) {
            e.printStackTrace();
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
