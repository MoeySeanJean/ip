package jaiden.ui;

import jaiden.command.Command;
import jaiden.command.CommandType;
import jaiden.exception.JaidenException;
import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Main class for Jaiden.
 */
public class Jaiden {
    private final Storage storage;
    private TaskList tasks;
    private CommandType commandType;

    /**
     * Constructor for Jaiden.
     *
     * @param filePath File path to save data in txt format.
     */
    public Jaiden(String filePath) {
        Ui ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (JaidenException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(this.tasks, this.storage);
            this.commandType = c.getCommandType();
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
    public CommandType getCommandType() {
        return this.commandType;
    }
}
