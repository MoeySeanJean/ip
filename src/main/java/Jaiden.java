import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Jaiden {
    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;

    public Jaiden(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
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
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Command command = Command.toCommand(input);
        try {
            while (command != Command.BYE) {
                String msg;
                Task task;
                String description;
                int markIndex;
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
                        if (input.split(" ").length < 2) {
                            throw new DukeException(0, "index", "mark");
                        }
                        markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                        tasks.get(markIndex).markAsDone();
                        msg = "    ____________________________________________________________\n"
                                + "     Nice! I've marked this task as done:\n"
                                + "       " + tasks.get(markIndex).toString() + "\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case UNMARK:
                        if (input.split(" ").length < 2) {
                            throw new DukeException(0, "index", "unmark");
                        }
                        markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                        tasks.get(markIndex).markAsNotDone();
                        msg = "    ____________________________________________________________\n"
                                + "     OK, I've marked this task as not done yet:\n"
                                + "       " + tasks.get(markIndex).toString() + "\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case TODO:
                        if (input.length() < 6 || input.substring(5).isBlank()) {
                            throw new DukeException(0, "description", "todo");
                        }
                        description = input.substring(5);
                        task = new Todo(description);
                        tasks.add(task);
                        msg = "    ____________________________________________________________\n"
                                + "     Got it. I've added this task:\n"
                                + "       " + task.toString() + "\n"
                                + "     Now you have " + tasks.size() + " tasks in the list.\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case DEADLINE:
                        int byIndex = input.contains("/by") ? input.indexOf("/by") : input.length();
                        if (input.length() < 10 || input.substring(9, byIndex).isBlank()) {
                            throw new DukeException(0, "description", "deadline");
                        } else if (!input.contains("/by") || input.indexOf("/by") + 4 >= input.length()) {
                            throw new DukeException(0, "by", "deadline");
                        }
                        description = input.substring(9, input.indexOf("/by") - 1);
                        String by = input.substring(input.indexOf("/by") + 4);
                        if (by.isBlank()) {
                            throw new DukeException(0, "by", "deadline");
                        }
                        LocalDate byDate = LocalDate.parse(by);
                        task = new Deadline(description, byDate);
                        tasks.add(task);
                        msg = "    ____________________________________________________________\n"
                                + "     Got it. I've added this task:\n"
                                + "       " + task.toString() + "\n"
                                + "     Now you have " + tasks.size() + " tasks in the list.\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case EVENT:
                        int fromIndex = input.contains("/from") ? input.indexOf("/from") : input.length();
                        int toIndex = input.contains("/to") ? input.indexOf("/to") : input.length();
                        int descriptionEndIndex = Math.min(fromIndex, toIndex);
                        if (input.length() < 7 || input.substring(6, descriptionEndIndex).isBlank()) {
                            throw new DukeException(0, "description", "event");
                        } else if (!input.contains("/from") || input.indexOf("/from") + 6 >= toIndex) {
                            throw new DukeException(0, "from", "event");
                        } else if (!input.contains("/to") || input.indexOf("/to") + 4 >= input.length()) {
                            throw new DukeException(0, "to", "event");
                        }
                        description = input.substring(6, input.indexOf("/from") - 1);
                        String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                        if (from.isBlank()) {
                            throw new DukeException(0, "from", "event");
                        }
                        LocalDate fromDate = LocalDate.parse(from);
                        String to = input.substring(input.indexOf("/to") + 4);
                        if (to.isBlank()) {
                            throw new DukeException(0, "to", "event");
                        }
                        LocalDate toDate = LocalDate.parse(to);
                        task = new Event(description, fromDate, toDate);
                        tasks.add(task);
                        msg = "    ____________________________________________________________\n"
                                + "     Got it. I've added this task:\n"
                                + "       " + task.toString() + "\n"
                                + "     Now you have " + tasks.size() + " tasks in the list.\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case DELETE:
                        if (input.length() < 8 || input.substring(7).isBlank()) {
                            throw new DukeException(0, "index", "delete");
                        }
                        int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                        task = tasks.remove(deleteIndex);
                        msg = "    ____________________________________________________________\n"
                                + "     Noted. I've removed this task:\n"
                                + "       " + task.toString() + "\n"
                                + "     Now you have " + tasks.size() + " tasks in the list.\n"
                                + "    ____________________________________________________________\n";
                        ui.print(msg);
                        break;
                    case SHOW:
                        if (input.length() < 6 || input.substring(5).isBlank()) {
                            throw new DukeException(0, "date", "show");
                        }
                        LocalDate showDate = LocalDate.parse(input.substring(5));
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
                input = scanner.nextLine();
                command = Command.toCommand(input);
            }
        } catch (DukeException e) {
            ui.print(e.toString());
        } finally {
            storage.save(tasks);
            scanner.close();
            ui.exit();
        }
    }

    public static void main(String[] args) {
        new Jaiden("data/tasks.txt").run();
    }
}
