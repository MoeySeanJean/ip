import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String list() {
        String msg = "     Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            msg += "     " + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return msg;
    }

    public String mark(int index) {
        tasks.get(index).markAsDone();
        String msg = "     Nice! I've marked this task as done:\n"
                + "       " + tasks.get(index).toString() + "\n";
        return msg;
    }

    public String unmark(int index) {
        tasks.get(index).markAsNotDone();
        String msg = "     OK, I've marked this task as not done yet:\n"
                + "       " + tasks.get(index).toString() + "\n";
        return msg;
    }

    public String add(Task task) {
        tasks.add(task);
        String msg = "     Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + tasks.size() + " tasks in the list.\n";
        return msg;
    }

    public String remove(int index) {
        Task task = tasks.remove(index);
        String msg = "     Noted. I've removed this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + tasks.size() + " tasks in the list.\n";

        return msg;
    }

    public String show(LocalDate showDate) {
        String msg = "     Here are the tasks on " + showDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getClass() == Deadline.class) {
                Deadline d = (Deadline) t;
                if (d.isBy(showDate)) {
                    msg += "     " + (i + 1) + "." + tasks.get(i).toString() + "\n";
                }
            } else if (t.getClass() == Event.class) {
                Event e = (Event) t;
                if (e.isBetween(showDate)) {
                    msg += "     " + (i + 1) + "." + tasks.get(i).toString() + "\n";
                }
            }
        }
        return msg;
    }

    public String save() {
        String msg = "";
        for (Task task : tasks) {
            msg += task.save() + "\n";
        }
        return msg;
    }
}
