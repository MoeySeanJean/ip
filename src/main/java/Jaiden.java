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
        System.out.println(greet);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String msg = "    ____________________________________________________________\n"
                    + "     " + input + "\n"
                    + "    ____________________________________________________________\n";
            System.out.println(msg);
            input = scanner.nextLine();
        }
        System.out.println(exit);
        scanner.close();
    }
}
