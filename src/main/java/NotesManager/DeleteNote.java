package NotesManager;

import java.util.*;

public class DeleteNote {

    private List<Note> noteList;
    private Scanner scanner;

    public DeleteNote(List<Note> noteList) {
        this.noteList = noteList;
        this.scanner = new Scanner(System.in);
    }

    public void deleteById() {
        System.out.print("Enter Note ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Iterator<Note> iterator = noteList.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note.getNoteId() == id) {
                iterator.remove();
                System.out.println("Note deleted.");
                return;
            }
        }
        System.out.println("Note not found.");
    }

    public void deleteByTitle() {
        System.out.print("Enter Note Title to delete: ");
        String title = scanner.nextLine();
        Iterator<Note> iterator = noteList.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note.getTitle().equalsIgnoreCase(title)) {
                iterator.remove();
                System.out.println("Note deleted.");
                return;
            }
        }
        System.out.println("Note not found.");
    }

}
