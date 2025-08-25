package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

import java.util.Arrays;

/**
 * Class for command.
 */
public abstract class Command {
    protected String[] commands;
    protected boolean isExit;

    /**
     * Constructor for command.
     *
     * @param commands User input.
     */
    public Command(String[] commands) {
        this.commands = commands;
    }

    /**
     * Check if command is exit command.
     *
     * @return Exit status.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Execute command.
     *
     * @param taskList Task list.
     * @param ui Ui to show command outputs.
     * @param storage Storage to save current data.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Command other)) {
            return false;
        }
        return Arrays.equals(commands, other.commands) && isExit == other.isExit;
    }
}
