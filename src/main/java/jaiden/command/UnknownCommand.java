package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

/**
 * Class for unknown command.
 */
public class UnknownCommand extends Command {
    /**
     * Constructor for unknown command.
     *
     * @param commands User input.
     */
    public UnknownCommand(String[] commands) {
        super(commands);
        this.isExit = false;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show("     OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        storage.save(taskList);
    }
}
