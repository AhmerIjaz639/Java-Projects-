package NotesManager;

import java.util.List;
import java.util.Scanner;

public class AddNote {

        private List<Note> noteList;
        private Scanner scanner;

        public AddNote(List<Note> noteList) {
            this.noteList = noteList;
            this.scanner = new Scanner(System.in);
        }

    public void add() {
        boolean continueAdding = true;
        while (continueAdding) {
            String title = "";
            while (title.trim().isEmpty()) {
                System.out.print("Enter Note Title: ");
                title = scanner.nextLine();
                if (title.trim().isEmpty()) {
                    System.out.println("Title cannot be empty!");
                }
            }

            System.out.println("Enter Note Content (type 'END' on a new line to finish):");
            StringBuilder contentBuilder = new StringBuilder();
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("END")) {
                    break;
                }
                contentBuilder.append(line).append("\n");
            }

            String content = contentBuilder.toString().trim();

            Note newNote = new Note(title, content);
            noteList.add(newNote);
            System.out.println("" + "Note added with ID: " + newNote.getNoteId());

            System.out.print("Add another note? (yes/no): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                continueAdding = false;
            }
        }
    }

}


