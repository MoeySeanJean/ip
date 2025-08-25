package jaiden.ui;

import jaiden.command.*;

/**
 * Class to parse user input into executable command.
 */
public class Parser {
    /**
     * Parse user input into executable command.
     *
     * @param command User input.
     * @return Corresponding command.
     */
    public Command parse(String command) {
        String[] commands = command.split(" ");
        switch (commands[0]) {
            case "list":
            case "show":
                return new ListCommand(commands);
            case "mark":
            case "unmark":
                return new MarkCommand(commands);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(commands);
            case "delete":
                return new DeleteCommand(commands);
            case "bye":
                return new ExitCommand(commands);
            default:
                return new UnknownCommand(commands);
        }
    }
}
