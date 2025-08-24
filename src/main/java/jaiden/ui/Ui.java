package jaiden.ui;

import jaiden.exception.DukeException;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String msg = "    ____________________________________________________________\n"
                + "     Hello! I'm Jaiden\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.print(msg);
    }

    public String readCommand() throws DukeException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String inputCommand = input.split(" ")[0];
        switch(inputCommand) {
            case "mark":
                if (input.split(" ").length < 2) {
                    throw new DukeException("OOPS!!! The index of a mark cannot be empty.");
                }
                break;
            case "unmark":
                if (input.split(" ").length < 2) {
                    throw new DukeException("OOPS!!! The index of a unmark cannot be empty.");
                }
                break;
            case "todo":
                if (input.length() < 6 || input.substring(5).isBlank()) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                int byIndex = input.contains("/by") ? input.indexOf("/by") : input.length();
                if (input.length() < 10 || input.substring(9, byIndex).isBlank()) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                } else if (!input.contains("/by") || input.indexOf("/by") + 4 >= input.length()) {
                    throw new DukeException("OOPS!!! The by of a deadline cannot be empty.");
                }
                String by = input.substring(input.indexOf("/by") + 4);
                if (by.isBlank()) {
                    throw new DukeException("OOPS!!! The by of a deadline cannot be empty.");
                }
                break;
            case "event":
                int fromIndex = input.contains("/from") ? input.indexOf("/from") : input.length();
                int toIndex = input.contains("/to") ? input.indexOf("/to") : input.length();
                int descriptionEndIndex = Math.min(fromIndex, toIndex);
                if (input.length() < 7 || input.substring(6, descriptionEndIndex).isBlank()) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                } else if (!input.contains("/from") || input.indexOf("/from") + 6 >= toIndex) {
                    throw new DukeException("OOPS!!! The from of a deadline cannot be empty.");
                } else if (!input.contains("/to") || input.indexOf("/to") + 4 >= input.length()) {
                    throw new DukeException("OOPS!!! The to of a deadline cannot be empty.");
                }
                String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                if (from.isBlank()) {
                    throw new DukeException("OOPS!!! The from of a deadline cannot be empty.");
                }
                String to = input.substring(input.indexOf("/to") + 4);
                if (to.isBlank()) {
                    throw new DukeException("OOPS!!! The to of a deadline cannot be empty.");
                }
                break;
            case "delete":
                if (input.length() < 8 || input.substring(7).isBlank()) {
                    throw new DukeException("OOPS!!! The index of a delete cannot be empty.");
                }
                break;
            case "show":
                if (input.length() < 6 || input.substring(5).isBlank()) {
                    throw new DukeException("OOPS!!! The date of a show cannot be empty.");
                }
                break;
            default:
                break;
        }
        return input;
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void show(String msg) {
        System.out.print(msg);
    }

    public void showLoadingError() {
        String msg = "    ____________________________________________________________\n"
                + "     The data file is corrupted (Content not in the expected format).\n"
                + "    ____________________________________________________________\n";
        System.out.print(msg);
    }

    public void showError(String msg) {
        showLine();
        System.out.println("     " + msg);
    }
}
