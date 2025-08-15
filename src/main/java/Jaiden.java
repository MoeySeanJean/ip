import java.util.ArrayList;
import java.util.Scanner;

public class Jaiden {
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
        try {
            while (!input.equals("bye")) {
                if (input.equals("list")) {
                    String msg = "    ____________________________________________________________\n"
                            + "     Here are the tasks in your list:\n";
                    for (int i = 0; i < tasks.size(); i++) {
                        msg += "     " + (i + 1) + "." + tasks.get(i).toString() + "\n";
                    }
                    msg += "    ____________________________________________________________\n";
                    System.out.println(msg);
                } else if (input.startsWith("mark")) {
                    if (input.split(" ").length < 2) {
                        throw new DukeException(0, "index", "mark");
                    }
                    int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(markIndex).markAsDone();
                    String msg = "    ____________________________________________________________\n"
                            + "     Nice! I've marked this task as done:\n"
                            + "       " + tasks.get(markIndex).toString() + "\n"
                            + "    ____________________________________________________________\n";
                    System.out.println(msg);
                } else if (input.startsWith("unmark")) {
                    if (input.split(" ").length < 2) {
                        throw new DukeException(0, "index", "unmark");
                    }
                    int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(markIndex).markAsNotDone();
                    String msg = "    ____________________________________________________________\n"
                            + "     OK, I've marked this task as not done yet:\n"
                            + "       " + tasks.get(markIndex).toString() + "\n"
                            + "    ____________________________________________________________\n";
                    System.out.println(msg);
                } else if (input.startsWith("todo")) {
                    if (input.length() < 6 || input.substring(5).isBlank()) {
                        throw new DukeException(0, "description", "todo");
                    }
                    String description = input.substring(5);
                    Task task = new Todo(description);
                    tasks.add(task);
                    String msg = "    ____________________________________________________________\n"
                            + "     Got it. I've added this task:\n"
                            + "       " + task.toString() + "\n"
                            + "     Now you have " + tasks.size() + " tasks in the list.\n"
                            + "    ____________________________________________________________\n";
                    System.out.println(msg);
                } else if (input.startsWith("deadline")) {
                    int byIndex = input.contains("/by") ? input.indexOf("/by") : input.length();
                    if (input.length() < 10 || input.substring(9, byIndex).isBlank()) {
                        throw new DukeException(0, "description", "deadline");
                    } else if (!input.contains("/by")) {
                        throw new DukeException(0, "by", "deadline");
                    }
                    String description = input.substring(9, input.indexOf("/by") - 1);
                    String by = input.substring(input.indexOf("/by") + 4);
                    Task task = new Deadline(description, by);
                    tasks.add(task);
                    String msg = "    ____________________________________________________________\n"
                            + "     Got it. I've added this task:\n"
                            + "       " + task.toString() + "\n"
                            + "     Now you have " + tasks.size() + " tasks in the list.\n"
                            + "    ____________________________________________________________\n";
                    System.out.println(msg);
                } else if (input.startsWith("event")) {
                    int fromIndex = input.contains("/from") ? input.indexOf("/from") : input.length();
                    int toIndex = input.contains("/to") ? input.indexOf("/to") : input.length();
                    int descriptionEndIndex = Math.min(fromIndex, toIndex);
                    if (input.length() < 7 || input.substring(6, descriptionEndIndex).isBlank()) {
                        throw new DukeException(0, "description", "event");
                    } else if (!input.contains("/from")) {
                        throw new DukeException(0, "from", "event");
                    } else if (!input.contains("/to")) {
                        throw new DukeException(0, "to", "event");
                    }
                    String description = input.substring(6, input.indexOf("/from") - 1);
                    String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                    String to = input.substring(input.indexOf("/to") + 4);
                    Task task = new Event(description, from, to);
                    tasks.add(task);
                    String msg = "    ____________________________________________________________\n"
                            + "     Got it. I've added this task:\n"
                            + "       " + task.toString() + "\n"
                            + "     Now you have " + tasks.size() + " tasks in the list.\n"
                            + "    ____________________________________________________________\n";
                    System.out.println(msg);
                } else if (input.startsWith("delete")) {
                    if (input.length() < 8 || input.substring(7).isBlank()) {
                        throw new DukeException(0, "index", "delete");
                    }
                    int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = tasks.remove(deleteIndex);
                    String msg = "    ____________________________________________________________\n"
                            + "     Noted. I've removed this task:\n"
                            + "       " + task.toString() + "\n"
                            + "     Now you have " + tasks.size() + " tasks in the list.\n"
                            + "    ____________________________________________________________\n";
                    System.out.println(msg);
                } else {
                    throw new DukeException(1);
                }
                input = scanner.nextLine();
            }
        } catch (DukeException e) {
            System.out.println(e);
        } finally {
            System.out.println(exit);
            scanner.close();
        }
    }
}
