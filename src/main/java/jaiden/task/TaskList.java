package jaiden.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class for task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for non-empty task list.
     *
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Shows a list of tasks in the task list.
     *
     * @return Message to be shown.
     */
    public String list() {
        String msg = "     Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            msg += "     " + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return msg;
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of task to be marked (0-indexed).
     * @return Message to be shown.
     */
    public String mark(int index) {
        tasks.get(index).markAsDone();
        String msg = "     Nice! I've marked this task as done:\n"
                + "       " + tasks.get(index).toString() + "\n";
        return msg;
    }

    /**
     * Unmarks a task as done.
     *
     * @param index Index of task to be unmarked (0-indexed).
     * @return Message to be shown.
     */
    public String unmark(int index) {
        tasks.get(index).markAsNotDone();
        String msg = "     OK, I've marked this task as not done yet:\n"
                + "       " + tasks.get(index).toString() + "\n";
        return msg;
    }

    /**
     * Adds task to task list.
     *
     * @param task Task to be added.
     * @return Message to be shown.
     */
    public String add(Task task) {
        tasks.add(task);
        String msg = "     Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + tasks.size() + " tasks in the list.\n";
        return msg;
    }

    /**
     * Removes task from task list.
     *
     * @param index Index of task to be removed (0-indexed).
     * @return Message to be shown.
     */
    public String remove(int index) {
        Task task = tasks.remove(index);
        String msg = "     Noted. I've removed this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + tasks.size() + " tasks in the list.\n";

        return msg;
    }

    /**
     * Shows a list of tasks on a date.
     *
     * @param showDate Date.
     * @return Message to be shown.
     */
    public String show(LocalDate showDate) {
        String msg = "     Here are the tasks on "
                + showDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getClass() == Deadline.class) {
                Deadline d = (Deadline) t;
                if (d.isBy(showDate)) {
                    msg += "     " + (i + 1) + "." + t.toString() + "\n";
                }
            } else if (t.getClass() == Event.class) {
                Event e = (Event) t;
                if (e.isBetween(showDate)) {
                    msg += "     " + (i + 1) + "." + t.toString() + "\n";
                }
            }
        }
        return msg;
    }

    /**
     * Finds the tasks that contain the text.
     *
     * @param text Text to find for.
     * @return Message to be shown.
     */
    public String find(String text) {
        String msg = "     Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.hasText(text)) {
                msg += "     " + (i + 1) + "." + t.toString() + "\n";
            }
        }
        return msg;
    }

    /**
     * Converts task list to string to be saved.
     *
     * @return String representation to be saved.
     */
    public String save() {
        String msg = "";
        for (Task task : tasks) {
            msg += task.save() + "\n";
        }
        return msg;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TaskList other)) {
            return false;
        }
        return tasks.equals(other.tasks);
    }
}
