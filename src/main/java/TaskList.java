import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String list() {
        String msg = "    ____________________________________________________________\n"
                + "     Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            msg += "     " + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        msg += "    ____________________________________________________________\n";
        return msg;
    }

    public String mark(int index) {
        tasks.get(index).markAsDone();
        String msg = "    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done:\n"
                + "       " + tasks.get(index).toString() + "\n"
                + "    ____________________________________________________________\n";
        return msg;
    }

    public String unmark(int index) {
        tasks.get(index).markAsNotDone();
        String msg = "    ____________________________________________________________\n"
                + "     OK, I've marked this task as not done yet:\n"
                + "       " + tasks.get(index).toString() + "\n"
                + "    ____________________________________________________________\n";
        return msg;
    }

    public String add(Task task) {
        tasks.add(task);
        String msg = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + tasks.size() + " tasks in the list.\n"
                + "    ____________________________________________________________\n";
        return msg;
    }

    public String remove(int index) {
        Task task = tasks.remove(index);
        String msg = "    ____________________________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + tasks.size() + " tasks in the list.\n"
                + "    ____________________________________________________________\n";

        return msg;
    }

    public String show(LocalDate showDate) {
        String msg = "    ____________________________________________________________\n"
                + "     Here are the tasks on " + showDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " in your list:\n";
        for (Task t : tasks) {
            if (t.getClass() == Deadline.class) {
                Deadline d = (Deadline) t;
                if (d.by.isAfter(showDate) || d.by.isEqual(showDate)) {
                    msg += "       " + t.toString() + "\n";
                }
            } else if (t.getClass() == Event.class) {
                Event e = (Event) t;
                if ((e.from.isBefore(showDate) || e.from.isEqual(showDate)) && (e.to.isAfter(showDate) || e.to.isEqual(showDate))) {
                    msg += "       " + t.toString() + "\n";
                }
            }
        }
        msg += "    ____________________________________________________________\n";
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
