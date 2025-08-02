package NotesManager;

import java.util.List;
import java.util.Scanner;

public class SearchNote {

        private List<Note> noteList;
        private Scanner scanner;

        public SearchNote(List<Note> noteList) {
            this.noteList = noteList;
            this.scanner = new Scanner(System.in);
        }

        public void searchById() {
            System.out.print("Enter Note ID to search: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            for (Note note : noteList) {
                if (note.getNoteId() == id) {
                    System.out.println("Title: " + note.getTitle());
                    System.out.println("Content: " + note.getContent());
                    return;
                }
            }
            System.out.println("Note not found.");
        }

        public void searchByTitle() {
            System.out.print("Enter Note Title to search: ");
            String title = scanner.nextLine();
            for (Note note : noteList) {
                if (note.getTitle().equalsIgnoreCase(title)) {
                    System.out.println("ID: " + note.getNoteId());
                    System.out.println("Content: " + note.getContent());
                    return;
                }
            }
            System.out.println("Note not found.");
        }
    }

