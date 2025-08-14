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
        String[] list = new String[100];
        int index = 0;
        System.out.println(greet);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                String msg = "    ____________________________________________________________\n";
                for (int i = 0; i < index; i++) {
                    msg += "     " + (i + 1) + ". " + list[i] + "\n";
                }
                msg += "    ____________________________________________________________\n";
                System.out.println(msg);
            } else {
                list[index++] = input;
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
