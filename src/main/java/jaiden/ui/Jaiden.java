package jaiden.ui;

import jaiden.command.Command;
import jaiden.exception.JaidenException;
import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Main class for Jaiden.
 */
public class Jaiden {
    private final Storage storage;
    private TaskList tasks;
    private String commandType;

    /**
     * Constructor for Jaiden.
     *
     * @param filePath File path to save data in txt format.
     */
    public Jaiden(String filePath) {
        Ui ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JaidenException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, storage);
            commandType = c.getClass().getSimpleName();
            return c.getString();
        } catch (JaidenException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Gets command type.
     *
     * @return Command type.
     */
    public String getCommandType() {
        return commandType;
    }
}
