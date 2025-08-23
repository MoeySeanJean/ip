import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Jaiden {
    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;
    private Parser parser;

    public Jaiden(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new ArrayList<Task>();
        }
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }

    public void run() {
        ui.greet();
        try {
            ArrayList<String> input = parser.read();
            Command command = parser.parseCommand(input.get(0));
            while (command != Command.BYE) {
                String msg;
                Task task;
                String description;
                int index;
                switch (command) {
                    case LIST:
                        msg = "    ____________________________________________________________\n"
                                + "     Here are the tasks in your list:\n";
                        for (int i = 0; i < tasks.size(); i++) {
                            msg += "     " + (i + 1) + "." + tasks.get(i).toString() + "\n";
                        }
                        msg += "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case MARK:
                        index = parser.parseIndex(input.get(1));
                        tasks.get(index).markAsDone();
                        msg = "    ____________________________________________________________\n"
                                + "     Nice! I've marked this task as done:\n"
                                + "       " + tasks.get(index).toString() + "\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case UNMARK:
                        index = parser.parseIndex(input.get(1));
                        tasks.get(index).markAsNotDone();
                        msg = "    ____________________________________________________________\n"
                                + "     OK, I've marked this task as not done yet:\n"
                                + "       " + tasks.get(index).toString() + "\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case TODO:
                        task = parser.parseTodo(input.get(1));
                        tasks.add(task);
                        msg = "    ____________________________________________________________\n"
                                + "     Got it. I've added this task:\n"
                                + "       " + task.toString() + "\n"
                                + "     Now you have " + tasks.size() + " tasks in the list.\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case DEADLINE:
                        task = parser.parseDeadline(input.get(1), input.get(2));
                        tasks.add(task);
                        msg = "    ____________________________________________________________\n"
                                + "     Got it. I've added this task:\n"
                                + "       " + task.toString() + "\n"
                                + "     Now you have " + tasks.size() + " tasks in the list.\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case EVENT:
                        task = parser.parseEvent(input.get(1), input.get(2), input.get(3));
                        tasks.add(task);
                        msg = "    ____________________________________________________________\n"
                                + "     Got it. I've added this task:\n"
                                + "       " + task.toString() + "\n"
                                + "     Now you have " + tasks.size() + " tasks in the list.\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case DELETE:
                        index = parser.parseIndex(input.get(1));
                        task = tasks.remove(index);
                        msg = "    ____________________________________________________________\n"
                                + "     Noted. I've removed this task:\n"
                                + "       " + task.toString() + "\n"
                                + "     Now you have " + tasks.size() + " tasks in the list.\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case SHOW:
                        LocalDate showDate = parser.parseDate(input.get(1));
                        msg = "    ____________________________________________________________\n"
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
                        ui.print(msg);
                        break;
                    case UNKNOWN:
                        throw new DukeException(1);
                }
                input = parser.read();
                command = parser.parseCommand(input.get(0));
            }
        } catch (DukeException e) {
            ui.print(e.toString());
        } finally {
            storage.save(tasks);
            ui.exit();
        }
    }

    public static void main(String[] args) {
        new Jaiden("data/tasks.txt").run();
    }
}
