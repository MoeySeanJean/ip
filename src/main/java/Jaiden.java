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
        Task[] list = new Task[100];
        int index = 0;
        System.out.println(greet);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                String msg = "    ____________________________________________________________\n"
                        + "     Here are the tasks in your list:\n";
                for (int i = 0; i < index; i++) {
                    msg += "     " + (i + 1) + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription() + "\n";
                }
                msg += "    ____________________________________________________________\n";
                System.out.println(msg);
            } else if (input.startsWith("mark")) {
                int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                list[markIndex].markAsDone();
                String msg = "    ____________________________________________________________\n"
                        + "     Nice! I've marked this task as done:\n"
                        + "       [" + list[markIndex].getStatusIcon() + "] " + list[markIndex].getDescription() + "\n"
                        + "    ____________________________________________________________\n";
                System.out.println(msg);
            } else if (input.startsWith("unmark")) {
                int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                list[markIndex].markAsNotDone();
                String msg = "    ____________________________________________________________\n"
                        + "     OK, I've marked this task as not done yet:\n"
                        + "       [" + list[markIndex].getStatusIcon() + "] " + list[markIndex].getDescription() + "\n"
                        + "    ____________________________________________________________\n";
                System.out.println(msg);
            } else {
                    list[index++] = new Task(input);
                    String msg = "    ____________________________________________________________\n"
                            + "     added: " + input + "\n"
                            + "    ____________________________________________________________\n";
                    System.out.println(msg);
            }
            input = scanner.nextLine();
        }
        System.out.println(exit);
        scanner.close();
    }
}
