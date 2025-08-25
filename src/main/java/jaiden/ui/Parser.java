package jaiden.ui;

import jaiden.command.*;

public class Parser {
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
            case "find":
                return new FindCommand(commands);
            case "bye":
                return new ExitCommand(commands);
            default:
                return new UnknownCommand(commands);
        }
    }
}
