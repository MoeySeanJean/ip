package jaiden.ui;

import jaiden.command.Command;
import jaiden.exception.DukeException;
import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Main class for Jaiden
 */
public class Jaiden {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Jaiden.
     *
     * @param filePath File path to save data in txt format.
     */
    private Jaiden(String filePath) {
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

    /**
     * Main logic flow.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Jaiden("data/tasks.txt").run();
    }
}
