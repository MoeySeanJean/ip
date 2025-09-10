package jaiden.ui;

import java.util.ArrayList;
import java.util.List;

import jaiden.command.AddCommand;
import jaiden.command.ChangeMarkCommand;
import jaiden.command.Command;
import jaiden.command.DeleteCommand;
import jaiden.command.ExitCommand;
import jaiden.command.ListCommand;
import jaiden.command.UnknownCommand;
import jaiden.exception.JaidenException;

/**
 * Class to parse user input into executable command.
 */
public class Parser {
    /**
     * Parses user input into executable command.
     *
     * @param inputs User input.
     * @return Corresponding command.
     */
    public static Command parse(String... inputs) throws JaidenException {
        assert inputs.length > 0;
        String commandType = inputs[0];
        assert commandType != null;
        List<String> parsedInputs = new ArrayList<>();
        parsedInputs.add(commandType);
        StringBuilder arg = new StringBuilder();
        for (int i = 1; i < inputs.length; i++) {
            if (!inputs[i].startsWith("/")) {
                arg.append(inputs[i]).append(" ");
            } else {
                parsedInputs.add(arg.toString().trim());
                parsedInputs.add(inputs[i]);
                arg.setLength(0);
            }
        }
        if (!arg.isEmpty()) {
            parsedInputs.add(arg.toString().trim());
        }
        switch(commandType) {
        case "mark":
            if (parsedInputs.size() < 2) {
                throw new JaidenException("OOPS!!! The index of a mark cannot be empty.");
            }
            break;
        case "unmark":
            if (parsedInputs.size() < 2) {
                throw new JaidenException("OOPS!!! The index of a unmark cannot be empty.");
            }
            break;
        case "todo":
            if (parsedInputs.size() < 2 || parsedInputs.get(1).isBlank()) {
                throw new JaidenException("OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            if (parsedInputs.size() < 2 || parsedInputs.get(1).isBlank()) {
                throw new JaidenException("OOPS!!! The description of a deadline cannot be empty.");
            } else if (parsedInputs.size() < 4 || !parsedInputs.get(2).equals("/by") || parsedInputs.get(3).isBlank()) {
                throw new JaidenException("OOPS!!! The by of a deadline cannot be empty.");
            }
            break;
        case "event":
            if (parsedInputs.size() < 2 || parsedInputs.get(1).isBlank()) {
                throw new JaidenException("OOPS!!! The description of a event cannot be empty.");
            } else if (parsedInputs.size() < 4 || !parsedInputs.get(2).equals("/from")
                    || parsedInputs.get(3).isBlank()) {
                throw new JaidenException("OOPS!!! The from of a event cannot be empty.");
            } else if (parsedInputs.size() < 6 || !parsedInputs.get(4).equals("/to") || parsedInputs.get(5).isBlank()) {
                throw new JaidenException("OOPS!!! The to of a event cannot be empty.");
            }
            break;
        case "delete":
            if (parsedInputs.size() < 2 || parsedInputs.get(1).isBlank()) {
                throw new JaidenException("OOPS!!! The index of a delete cannot be empty.");
            }
            break;
        case "show":
            if (parsedInputs.size() < 2 || parsedInputs.get(1).isBlank()) {
                throw new JaidenException("OOPS!!! The date of a show cannot be empty.");
            }
            break;
        case "find":
            if (parsedInputs.size() < 2 || parsedInputs.get(1).isBlank()) {
                throw new JaidenException("OOPS!!! The text of a find cannot be empty.");
            }
            break;
        default:
            break;
        }
        return switch (commandType) {
        case "list", "show", "find" -> new ListCommand(parsedInputs.toArray(new String[0]));
        case "mark", "unmark" -> new ChangeMarkCommand(parsedInputs.toArray(new String[0]));
        case "todo", "deadline", "event" -> new AddCommand(parsedInputs.toArray(new String[0]));
        case "delete" -> new DeleteCommand(parsedInputs.toArray(new String[0]));
        case "bye" -> new ExitCommand(parsedInputs.toArray(new String[0]));
        default -> new UnknownCommand(parsedInputs.toArray(new String[0]));
        };
    }
}
