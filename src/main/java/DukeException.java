public class DukeException extends Exception {
    protected String field;
    protected String task;

    public DukeException() {}

    public DukeException(String field, String task) {
        this.field = field;
        this.task = task;
    }
}
