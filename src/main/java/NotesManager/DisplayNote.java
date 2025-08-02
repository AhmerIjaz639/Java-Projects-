package NotesManager;

import java.util.List;

public class DisplayNote {
    private List<Note> noteList;

    public DisplayNote(List<Note> noteList) {
        this.noteList = noteList;
    }

    public void displayAll() {
        for (Note note : noteList) {
            System.out.println("ID: " + note.getNoteId());
            System.out.println("Title: " + note.getTitle());
            System.out.println("Content: " + note.getContent());
            System.out.println("---------------------------------------------------------------------");
            System.out.println("\n");
            System.out.println("---------------------------------------------------------------------");
        }
    }
}
