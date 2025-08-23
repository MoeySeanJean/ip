import java.time.LocalDate;
import java.util.ArrayList;

public class Jaiden {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Jaiden(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        ArrayList<String> input = new ArrayList<>();;
        Command command;
        try {
            input = parser.read();
            command = parser.parseCommand(input.get(0));
        } catch (DukeException e) {
            ui.showEmptyError(e.field, e.task);
            command = Command.DEFAULT;
        }
        while (command != Command.BYE) {
            String msg;
            Task task;
            String description;
            int index;
            try {
                switch (command) {
                    case LIST:
                        msg = tasks.list();
                        ui.print(msg);
                        break;
                    case MARK:
                        index = parser.parseIndex(input.get(1));
                        msg = tasks.mark(index);
                        ui.print(msg);
                        break;
                    case UNMARK:
                        index = parser.parseIndex(input.get(1));
                        msg = tasks.unmark(index);
                        ui.print(msg);
                        break;
                    case TODO:
                        task = parser.parseTodo(input.get(1));
                        msg = tasks.add(task);
                        ui.print(msg);
                        break;
                    case DEADLINE:
                        task = parser.parseDeadline(input.get(1), input.get(2));
                        msg = tasks.add(task);
                        ui.print(msg);
                        break;
                    case EVENT:
                        task = parser.parseEvent(input.get(1), input.get(2), input.get(3));
                        msg = tasks.add(task);
                        ui.print(msg);
                        break;
                    case DELETE:
                        index = parser.parseIndex(input.get(1));
                        msg = tasks.remove(index);
                        ui.print(msg);
                        break;
                    case SHOW:
                        LocalDate showDate = parser.parseDate(input.get(1));
                        msg = tasks.show(showDate);
                        ui.print(msg);
                        break;
                    case UNKNOWN:
                        throw new DukeException();
                    default:
                        break;
                }
            } catch (DukeException e) {
                ui.showCommandError();
            }
            try {
                input = parser.read();
                command = parser.parseCommand(input.get(0));
            } catch (DukeException e) {
                ui.showEmptyError(e.field, e.task);
                command = Command.DEFAULT;
            }
        }
        storage.save(tasks);
        ui.exit();
    }

    public static void main(String[] args) {
        new Jaiden("data/tasks.txt").run();
    }
}
