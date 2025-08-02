package NotesManager;
import java.util.*;

public class NotesManager {

        private List<Note> notes;
        private AddNote addNote;
        private SearchNote searchNote;
        private DisplayNote displayNote;
        private DeleteNote deleteNote;
        private Scanner scanner;

        public NotesManager() {
            notes = new ArrayList<>();
            addNote = new AddNote(notes);
            searchNote = new SearchNote(notes);
            displayNote = new DisplayNote(notes);
            deleteNote = new DeleteNote(notes);
            scanner = new Scanner(System.in);
        }

        private void runNotesManager() {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nNotes Manager:");
                System.out.println("1. Add Note");
                System.out.println("2. Search Note by ID");
                System.out.println("3. Search Note by Title");
                System.out.println("4. Display All Notes");
                System.out.println("5. Delete Note by ID");
                System.out.println("6. Delete Note by Title");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addNote.add();
                        break;
                    case 2:
                        searchNote.searchById();
                        break;
                    case 3:
                        searchNote.searchByTitle();
                        break;
                    case 4:
                        displayNote.displayAll();
                        break;
                    case 5:
                        deleteNote.deleteById();
                        break;
                    case 6:
                        deleteNote.deleteByTitle();
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }

            }
        }

        public static void showNotesMenu() {
            NotesManager manager = new NotesManager();
            manager.runNotesManager();
        }


    public List<Note> getNotes() {
        return notes;
    }

}

