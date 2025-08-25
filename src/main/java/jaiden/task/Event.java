package jaiden.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for event task
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for event task.
     *
     * @param description Description of task.
     * @param from Start date.
     * @param to End date.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for event task.
     *
     * @param description Description of task.
     * @param isDone Done status.
     * @param from Start date.
     * @param to End date.
     */
    public Event(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Check if the date is between the task start date and end date.
     *
     * @param d Date.
     * @return True if the date is between the task start date and end date.
     */
    public boolean isBetween(LocalDate d) {
        return (from.isBefore(d) || from.isEqual(d)) && (to.isAfter(d) || to.isEqual(d));
    }

    /**
     * @inheritDoc
     */
    @Override
    public String save() {
        return "E | " + super.save() + " | " + from + " | " + to;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
