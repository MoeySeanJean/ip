package jaiden.task;

/**
 * Class for todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for todo task.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for todo task.
     *
     * @param description Description of task.
     * @param isDone Done status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String save() {
        return "T | " + super.save();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
