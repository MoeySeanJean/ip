package jaiden.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for deadline task.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for deadline task.
     *
     * @param description Description of task.
     * @param by Due date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for deadline task.
     *
     * @param description Description of task.
     * @param isDone Done status.
     * @param by Due date.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Check if the date is before the task due date.
     *
     * @param d Date.
     * @return True if the date is before the task due date.
     */
    public boolean isBy(LocalDate d) {
        return d.isBefore(by) || d.isEqual(by);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String save() {
        return "D | " + super.save() + " | " + by;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
