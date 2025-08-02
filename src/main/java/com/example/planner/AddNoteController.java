package com.example.planner;

import NotesManager.NotesManager;
import NotesManager.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddNoteController {

    private NotesManager notesManager;

    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    @FXML
    private Label statusLabel;

    public void setNotesManager(NotesManager notesManager) {
        this.notesManager = notesManager;
    }

    @FXML
    private void handleAddNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty()) {
            statusLabel.setText("Title cannot be empty!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Note newNote = new Note(title, content);
        notesManager.getNotes().add(newNote);

        statusLabel.setText("Note added with ID: " + newNote.getNoteId());
        statusLabel.setStyle("-fx-text-fill: green;");

        titleField.clear();
        contentArea.clear();
    }
}
