package jaiden.ui;

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
     * @param input User input.
     * @return Corresponding command.
     */
    public static Command parse(String input) throws JaidenException {
        String[] commands = input.split(" ");
        String commandType = commands[0];
        switch(commandType) {
        case "mark":
            if (input.split(" ").length < 2) {
                throw new JaidenException("OOPS!!! The index of a mark cannot be empty.");
            }
            break;
        case "unmark":
            if (input.split(" ").length < 2) {
                throw new JaidenException("OOPS!!! The index of a unmark cannot be empty.");
            }
            break;
        case "todo":
            if (input.length() < 6 || input.substring(5).isBlank()) {
                throw new JaidenException("OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            int byIndex = input.contains("/by") ? input.indexOf("/by") : input.length();
            if (input.length() < 10 || input.substring(9, byIndex).isBlank()) {
                throw new JaidenException("OOPS!!! The description of a deadline cannot be empty.");
            } else if (!input.contains("/by") || input.indexOf("/by") + 4 >= input.length()) {
                throw new JaidenException("OOPS!!! The by of a deadline cannot be empty.");
            }
            String by = input.substring(input.indexOf("/by") + 4);
            if (by.isBlank()) {
                throw new JaidenException("OOPS!!! The by of a deadline cannot be empty.");
            }
            break;
        case "event":
            int fromIndex = input.contains("/from") ? input.indexOf("/from") : input.length();
            int toIndex = input.contains("/to") ? input.indexOf("/to") : input.length();
            int descriptionEndIndex = Math.min(fromIndex, toIndex);
            if (input.length() < 7 || input.substring(6, descriptionEndIndex).isBlank()) {
                throw new JaidenException("OOPS!!! The description of a event cannot be empty.");
            } else if (!input.contains("/from") || input.indexOf("/from") + 6 >= toIndex) {
                throw new JaidenException("OOPS!!! The from of a event cannot be empty.");
            } else if (!input.contains("/to") || input.indexOf("/to") + 4 >= input.length()) {
                throw new JaidenException("OOPS!!! The to of a event cannot be empty.");
            }
            String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
            if (from.isBlank()) {
                throw new JaidenException("OOPS!!! The from of a event cannot be empty.");
            }
            String to = input.substring(input.indexOf("/to") + 4);
            if (to.isBlank()) {
                throw new JaidenException("OOPS!!! The to of a event cannot be empty.");
            }
            break;
        case "delete":
            if (input.length() < 8 || input.substring(7).isBlank()) {
                throw new JaidenException("OOPS!!! The index of a delete cannot be empty.");
            }
            break;
        case "show":
            if (input.length() < 6 || input.substring(5).isBlank()) {
                throw new JaidenException("OOPS!!! The date of a show cannot be empty.");
            }
            break;
        case "find":
            if (input.length() < 6 || input.substring(5).isBlank()) {
                throw new JaidenException("OOPS!!! The text of a find cannot be empty.");
            }
            break;
        default:
            break;
        }
        return switch (commandType) {
        case "list", "show", "find" -> new ListCommand(commands);
        case "mark", "unmark" -> new ChangeMarkCommand(commands);
        case "todo", "deadline", "event" -> new AddCommand(commands);
        case "delete" -> new DeleteCommand(commands);
        case "bye" -> new ExitCommand(commands);
        default -> new UnknownCommand(commands);
        };
    }
}
