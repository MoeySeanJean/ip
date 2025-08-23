import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public ArrayList<String> read() throws DukeException {
        ArrayList<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String inputCommand = input.split(" ")[0];
        list.add(inputCommand);
        Command command = Command.toCommand(inputCommand);
        String description;
        switch(command) {
            case LIST:
                break;
            case MARK:
                if (input.split(" ").length < 2) {
                    throw new DukeException(0, "index", "mark");
                }
                list.add(input.split(" ")[1]);
                break;
            case UNMARK:
                if (input.split(" ").length < 2) {
                    throw new DukeException(0, "index", "unmark");
                }
                list.add(input.split(" ")[1]);
                break;
            case TODO:
                if (input.length() < 6 || input.substring(5).isBlank()) {
                    throw new DukeException(0, "description", "todo");
                }
                list.add(input.substring(5));
                break;
            case DEADLINE:
                int byIndex = input.contains("/by") ? input.indexOf("/by") : input.length();
                if (input.length() < 10 || input.substring(9, byIndex).isBlank()) {
                    throw new DukeException(0, "description", "deadline");
                } else if (!input.contains("/by") || input.indexOf("/by") + 4 >= input.length()) {
                    throw new DukeException(0, "by", "deadline");
                }
                description = input.substring(9, input.indexOf("/by") - 1);
                String by = input.substring(input.indexOf("/by") + 4);
                if (by.isBlank()) {
                    throw new DukeException(0, "by", "deadline");
                }
                list.add(description);
                list.add(by);
                break;
            case EVENT:
                int fromIndex = input.contains("/from") ? input.indexOf("/from") : input.length();
                int toIndex = input.contains("/to") ? input.indexOf("/to") : input.length();
                int descriptionEndIndex = Math.min(fromIndex, toIndex);
                if (input.length() < 7 || input.substring(6, descriptionEndIndex).isBlank()) {
                    throw new DukeException(0, "description", "event");
                } else if (!input.contains("/from") || input.indexOf("/from") + 6 >= toIndex) {
                    throw new DukeException(0, "from", "event");
                } else if (!input.contains("/to") || input.indexOf("/to") + 4 >= input.length()) {
                    throw new DukeException(0, "to", "event");
                }
                description = input.substring(6, input.indexOf("/from") - 1);
                String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                if (from.isBlank()) {
                    throw new DukeException(0, "from", "event");
                }
                String to = input.substring(input.indexOf("/to") + 4);
                if (to.isBlank()) {
                    throw new DukeException(0, "to", "event");
                }
                list.add(description);
                list.add(from);
                list.add(to);
                break;
            case DELETE:
                if (input.length() < 8 || input.substring(7).isBlank()) {
                    throw new DukeException(0, "index", "delete");
                }
                list.add(input.split(" ")[1]);
                break;
            case SHOW:
                if (input.length() < 6 || input.substring(5).isBlank()) {
                    throw new DukeException(0, "date", "show");
                }
                list.add(input.substring(5));
                break;
        }
        return list;
    }

    public Command parseCommand(String command) {
        return Command.toCommand(command);
    }

    public int parseIndex(String index) {
        return Integer.parseInt(index) - 1;
    }

    public Todo parseTodo(String description) {
        return new Todo(description);
    }

    public Deadline parseDeadline(String description, String by) {
        return new Deadline(description, LocalDate.parse(by));
    }

    public Event parseEvent(String description, String from, String to) {
        return new Event(description, LocalDate.parse(from), LocalDate.parse(to));
    }

    public LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }
}
