package jaiden.task;

/**
 * Class for task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for task.
     *
     * @param description Description of task.
     * @param isDone Done status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Get status icon of task.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Convert task to string to be saved
     *
     * @return String representation to be saved
     */
    public String save() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Task other)) {
            return false;
        }
        return description.equals(other.description) && isDone == other.isDone;
    }
}