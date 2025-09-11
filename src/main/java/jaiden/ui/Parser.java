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
        List<String> parsedInputs = parseInputs(inputs);

        String commandType = inputs[0];
        assert commandType != null;
        checkInputFormat(commandType, parsedInputs);

        return switch (commandType) {
        case "list", "view", "find" -> new ListCommand(parsedInputs.toArray(new String[0]));
        case "mark", "unmark" -> new ChangeMarkCommand(parsedInputs.toArray(new String[0]));
        case "todo", "deadline", "event" -> new AddCommand(parsedInputs.toArray(new String[0]));
        case "delete" -> new DeleteCommand(parsedInputs.toArray(new String[0]));
        case "bye" -> new ExitCommand(parsedInputs.toArray(new String[0]));
        default -> new UnknownCommand(parsedInputs.toArray(new String[0]));
        };
    }

    private static List<String> parseInputs(String[] inputs) {
        List<String> parsedInputs = new ArrayList<>();
        parsedInputs.add(inputs[0]);
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
        return parsedInputs;
    }

    private static void checkInputFormat(String commandType, List<String> parsedInputs) throws JaidenException {
        switch(commandType) {
        case "mark":
            if (hasNoArgs(parsedInputs) || isBlankArg(parsedInputs, 1)) {
                throw new JaidenException("OOPS!!! The index of a mark cannot be empty.");
            }
            break;
        case "unmark":
            if (hasNoArgs(parsedInputs) || isBlankArg(parsedInputs, 1)) {
                throw new JaidenException("OOPS!!! The index of a unmark cannot be empty.");
            }
            break;
        case "todo":
            if (hasNoArgs(parsedInputs) || isBlankArg(parsedInputs, 1)) {
                throw new JaidenException("OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            if (hasNoArgs(parsedInputs) || isBlankArg(parsedInputs, 1)) {
                throw new JaidenException("OOPS!!! The description of a deadline cannot be empty.");
            } else if (hasNoDeadline(parsedInputs) || isBlankArg(parsedInputs, 3)) {
                throw new JaidenException("OOPS!!! The by of a deadline cannot be empty.");
            }
            break;
        case "event":
            if (hasNoArgs(parsedInputs) || isBlankArg(parsedInputs, 1)) {
                throw new JaidenException("OOPS!!! The description of a event cannot be empty.");
            } else if (hasNoFrom(parsedInputs) || isBlankArg(parsedInputs, 3)) {
                throw new JaidenException("OOPS!!! The from of a event cannot be empty.");
            } else if (hasNoTo(parsedInputs) || isBlankArg(parsedInputs, 5)) {
                throw new JaidenException("OOPS!!! The to of a event cannot be empty.");
            }
            break;
        case "delete":
            if (hasNoArgs(parsedInputs) || isBlankArg(parsedInputs, 1)) {
                throw new JaidenException("OOPS!!! The index of a delete cannot be empty.");
            }
            break;
        case "view":
            if (hasNoArgs(parsedInputs) || isBlankArg(parsedInputs, 1)) {
                throw new JaidenException("OOPS!!! The date of a view cannot be empty.");
            }
            break;
        case "find":
            if (hasNoArgs(parsedInputs) || isBlankArg(parsedInputs, 1)) {
                throw new JaidenException("OOPS!!! The text of a find cannot be empty.");
            }
            break;
        default:
            break;
        }
    }

    private static boolean hasNoArgs(List<String> parsedInputs) {
        return parsedInputs.size() < 2;
    }

    private static boolean isBlankArg(List<String> parsedInputs, int index) {
        return parsedInputs.get(index).isBlank();
    }

    private static boolean hasNoDeadline(List<String> parsedInputs) {
        return parsedInputs.size() < 4 || !parsedInputs.get(2).equals("/by");
    }

    private static boolean hasNoFrom(List<String> parsedInputs) {
        return parsedInputs.size() < 4 || !parsedInputs.get(2).equals("/from");
    }

    private static boolean hasNoTo(List<String> parsedInputs) {
        return parsedInputs.size() < 6 || !parsedInputs.get(4).equals("/to");
    }
}
