package jaiden.command;

import java.util.Arrays;

import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Class for command.
 */
public abstract class Command {
    protected String[] commands;
    protected String string;
    protected CommandType commandType;

    /**
     * Constructor for command.
     *
     * @param commands User input.
     */
    public Command(String[] commands) {
        this.commands = commands;
    }

    /**
     * Executes command.
     *
     * @param taskList Task list.
     * @param storage Storage to save current data.
     */
    public abstract void execute(TaskList taskList, Storage storage);

    /**
     * Gets string.
     */
    public String getString() {
        return this.string;
    }

    /**
     * Gets command type.
     *
     * @return Command type.
     */
    public CommandType getCommandType() {
        return this.commandType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Command other)) {
            return false;
        }
        return Arrays.equals(this.commands, other.commands);
    }
}
