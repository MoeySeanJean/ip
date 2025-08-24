public class DukeException extends Exception {
    private String message;

    public DukeException() {}

    public DukeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
