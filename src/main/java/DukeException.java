public class DukeException extends Exception {
    protected int exception;
    protected String field;
    protected String task;

    public DukeException(int exception) {
        this.exception = exception;
    }

    public DukeException(int exception, String field, String task) {
        this.exception = exception;
        this.field = field;
        this.task = task;
    }

    @Override
    public String toString() {
        if (exception == 0) {
            String msg = "    ____________________________________________________________\n"
                    + "     OOPS!!! The " + field + " of a " + task + " cannot be empty.\n"
                    + "    ____________________________________________________________\n";
            return msg;
        } else {
            String msg = "    ____________________________________________________________\n"
                    + "     OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "    ____________________________________________________________\n";
            return msg;
        }
    }
}
