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
        Task[] tasks = new Task[100];
        int index = 0;
        System.out.println(greet);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                String msg = "    ____________________________________________________________\n"
                        + "     Here are the tasks in your list:\n";
                for (int i = 0; i < index; i++) {
                    msg += "     " + (i + 1) + "." + tasks[i].toString() + "\n";
                }
                msg += "    ____________________________________________________________\n";
                System.out.println(msg);
            } else if (input.startsWith("mark")) {
                int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[markIndex].markAsDone();
                String msg = "    ____________________________________________________________\n"
                        + "     Nice! I've marked this task as done:\n"
                        + "       " + tasks[markIndex].toString() + "\n"
                        + "    ____________________________________________________________\n";
                System.out.println(msg);
            } else if (input.startsWith("unmark")) {
                int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[markIndex].markAsNotDone();
                String msg = "    ____________________________________________________________\n"
                        + "     OK, I've marked this task as not done yet:\n"
                        + "       " + tasks[markIndex].toString() + "\n"
                        + "    ____________________________________________________________\n";
                System.out.println(msg);
            } else if (input.startsWith("todo")) {
                String description = input.substring(5);
                Task task = new Todo(description);
                tasks[index++] = task;
                String msg = "    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "       " + task.toString() + "\n"
                        + "     Now you have " + index + " tasks in the list.\n"
                        + "    ____________________________________________________________\n";
                System.out.println(msg);
            } else if (input.startsWith("deadline")) {
                String description = input.substring(9, input.indexOf("/by") - 1);
                String by = input.substring(input.indexOf("/by") + 4);
                Task task = new Deadline(description, by);
                tasks[index++] = task;
                String msg = "    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "       " + task.toString() + "\n"
                        + "     Now you have " + index + " tasks in the list.\n"
                        + "    ____________________________________________________________\n";
                System.out.println(msg);
            } else if (input.startsWith("event")) {
                String description = input.substring(6, input.indexOf("/from") - 1);
                String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                String to =  input.substring(input.indexOf("/to") + 4);
                Task task = new Event(description, from, to);
                tasks[index++] = task;
                String msg = "    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "       " + task.toString() + "\n"
                        + "     Now you have " + index + " tasks in the list.\n"
                        + "    ____________________________________________________________\n";
                System.out.println(msg);
            }
            input = scanner.nextLine();
        }
        System.out.println(exit);
        scanner.close();
    }
}
