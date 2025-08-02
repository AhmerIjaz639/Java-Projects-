package com.example.planner;

import NotesManager.NotesManager;
import NotesManager.Note;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SearchNoteController {

    private NotesManager notesManager;

    @FXML
    private TextField idField;

    @FXML
    private TextField titleField;

    @FXML
    private TextArea resultArea;

    public void setNotesManager(NotesManager notesManager) {
        this.notesManager = notesManager;
    }

    @FXML
    private void searchById() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            for (Note note : notesManager.getNotes()) {
                if (note.getNoteId() == id) {
                    resultArea.setText("Title: " + note.getTitle() + "\nContent:\n" + note.getContent());
                    return;
                }
            }
            resultArea.setText("Note not found.");
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid ID.");
        }
    }

    @FXML
    private void searchByTitle() {
        String title = titleField.getText().trim();
        for (Note note : notesManager.getNotes()) {
            if (note.getTitle().equalsIgnoreCase(title)) {
                resultArea.setText("ID: " + note.getNoteId() + "\nContent:\n" + note.getContent());
                return;
            }
        }
        resultArea.setText("Note not found.");
    }
}
