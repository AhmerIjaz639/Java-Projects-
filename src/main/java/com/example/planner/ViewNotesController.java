package com.example.planner;

import NotesManager.NotesManager;
import NotesManager.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ViewNotesController {

    private NotesManager notesManager;

    @FXML
    private ListView<String> notesListView;  // List of note titles

    @FXML
    private TextArea notesArea;  // Displays selected note content

    private ObservableList<String> noteTitles = FXCollections.observableArrayList();

    public void setNotesManager(NotesManager notesManager) {
        this.notesManager = notesManager;
        loadNotes();
    }

    private void loadNotes() {
        noteTitles.clear();
        for (Note note : notesManager.getNotes()) {
            noteTitles.add(note.getTitle());
        }
        notesListView.setItems(noteTitles);

        // Optional: Select the first note by default if available
        if (!noteTitles.isEmpty()) {
            notesListView.getSelectionModel().selectFirst();
            showNoteContent(noteTitles.get(0));
        }

        // Add listener for selection changes in the ListView
        notesListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showNoteContent(newSelection);
            }
        });
    }

    // Helper method to find and show content of the selected note by title
    private void showNoteContent(String title) {
        for (Note note : notesManager.getNotes()) {
            if (note.getTitle().equals(title)) {
                String content = "ID: " + note.getNoteId() + "\n" +
                        "Title: " + note.getTitle() + "\n\n" +
                        note.getContent();
                notesArea.setText(content);
                break;
            }
        }
    }
}
