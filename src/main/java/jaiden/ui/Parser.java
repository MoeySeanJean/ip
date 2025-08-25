package jaiden.ui;

import jaiden.command.AddCommand;
import jaiden.command.Command;
import jaiden.command.DeleteCommand;
import jaiden.command.ExitCommand;
import jaiden.command.ListCommand;
import jaiden.command.MarkCommand;
import jaiden.command.UnknownCommand;

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
        case "bye":
            return new ExitCommand(commands);
        default:
            return new UnknownCommand(commands);
        }
    }
}
