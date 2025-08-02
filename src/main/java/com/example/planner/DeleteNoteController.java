package com.example.planner;

import NotesManager.NotesManager;
import NotesManager.Note;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DeleteNoteController {

    private NotesManager notesManager;

    @FXML
    private TextField idField;

    @FXML
    private TextField titleField;

    @FXML
    private Label statusLabel;

    public void setNotesManager(NotesManager notesManager) {
        this.notesManager = notesManager;
    }

    @FXML
    private void deleteById() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            boolean removed = notesManager.getNotes().removeIf(note -> note.getNoteId() == id);
            statusLabel.setText(removed ? "Note deleted." : "Note not found.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid ID format.");
        }
    }

    @FXML
    private void deleteByTitle() {
        String title = titleField.getText().trim();
        boolean removed = notesManager.getNotes().removeIf(note -> note.getTitle().equalsIgnoreCase(title));
        statusLabel.setText(removed ? "Note deleted." : "Note not found.");
    }
}
