package jaiden.command;

import java.util.Arrays;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

public abstract class Command {
    protected String[] commands;
    protected boolean isExit;

    public Command(String[] commands) {
        this.commands = commands;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

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
