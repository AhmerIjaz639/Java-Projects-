package NotesManager;
public class Note {


    private static int counter = 21001;

    private int noteId;
    private String title;
    private String content;

    public Note(String title, String content) {
        this.noteId = counter++;
        this.title = title;
        this.content = content;
    }

    public int getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}