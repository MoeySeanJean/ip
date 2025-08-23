import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Jaiden {
    public enum Command {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        SHOW,
        BYE,
        UNKNOWN
    }

    public static Command toCommand(String command) {
        if (command.equals("list")) {
            return Command.LIST;
        } else if (command.startsWith("mark")) {
            return Command.MARK;
        } else if (command.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (command.startsWith("todo")) {
            return Command.TODO;
        } else if (command.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (command.startsWith("event")) {
            return Command.EVENT;
        } else if (command.startsWith("delete")) {
            return Command.DELETE;
        } else if (command.startsWith("show")) {
            return Command.SHOW;
        } else if (command.equals("bye")) {
            return Command.BYE;
        } else {
            return Command.UNKNOWN;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Jaiden\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        String exit = "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        ArrayList<Task> tasks = new ArrayList<Task>();
        System.out.println(greet);
        String input = scanner.nextLine();
        Command command = toCommand(input);
        File data = new File("./data/jaiden.txt");
        FileWriter dataWriter = null;
        try {
            if (!data.exists()) {
                new File("./data").mkdir();
            } else {
                Scanner dataReader = new Scanner(data);
                while (dataReader.hasNextLine()) {
                    String line = dataReader.nextLine();
                    String[] temp = line.split(" \\| ");
                    if (temp.length == 0) {
                        throw new DukeException(2);
                    } else if (temp[0].equals("T")) {
                        if (temp.length != 3 || !(temp[1].equals("0") || temp[1].equals("1")) || temp[2].isBlank()) {
                            throw new DukeException(2);
                        }
                        tasks.add(new Todo(temp[2], temp[1].equals("1")));
                    } else if (temp[0].equals("D")) {
                        if (temp.length != 4 || !(temp[1].equals("0") || temp[1].equals("1")) || temp[2].isBlank() || temp[3].isBlank()) {
                            throw new DukeException(2);
                        }
                        tasks.add(new Deadline(temp[2], temp[1].equals("1"), LocalDate.parse(temp[3])));
                    } else if (temp[0].equals("E")) {
                        if (temp.length != 5 || !(temp[1].equals("0") || temp[1].equals("1"))  || temp[2].isBlank() || temp[3].isBlank() || temp[4].isBlank()) {
                            throw new DukeException(2);
                        }
                        tasks.add(new Event(temp[2], temp[1].equals("1"), LocalDate.parse(temp[3]), LocalDate.parse(temp[4])));
                    } else {
                        throw new DukeException(2);
                    }
                }
            }
            dataWriter = new FileWriter(data);
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
                        System.out.println(msg);
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
                        System.out.println(msg);
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
                        System.out.println(msg);
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
                        System.out.println(msg);
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
                        System.out.println(msg);
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
                        System.out.println(msg);
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
                        System.out.println(msg);
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
                        System.out.println(msg);
                        break;
                    case UNKNOWN:
                        throw new DukeException(1);
                }
                input = scanner.nextLine();
                command = toCommand(input);
            }
        } catch (DukeException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            String msg = "";
            for (Task task : tasks) {
                msg += task.save() + "\n";
            }
            try {
                dataWriter.write(msg);
                dataWriter.close();
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                System.out.println(exit);
                scanner.close();
            }
        }
    }
}
