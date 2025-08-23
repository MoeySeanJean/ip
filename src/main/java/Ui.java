public class Ui {
    public void greet() {
        String msg = "    ____________________________________________________________\n"
                + "     Hello! I'm Jaiden\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(msg);
    }

    public void exit() {
        String msg = "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        System.out.println(msg);
    }

    public void print(String msg) {
        System.out.println(msg);
    }

    public void showLoadingError() {
        String msg = "    ____________________________________________________________\n"
                + "     The data file is corrupted (Content not in the expected format).\n"
                + "    ____________________________________________________________\n";
        System.out.println(msg);
    }
}
