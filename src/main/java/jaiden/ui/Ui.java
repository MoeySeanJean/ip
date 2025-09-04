package jaiden.ui;

/**
 * Class to handle ui input and output.
 */
public class Ui {
    /**
     * Shows loading error message.
     */
    public void showLoadingError() {
        String msg = "    ____________________________________________________________\n"
                + "     The data file is corrupted (Content not in the expected format).\n"
                + "    ____________________________________________________________\n";
        System.out.print(msg);
    }
}
