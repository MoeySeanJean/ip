package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

/**
 * Class for exit command.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for exit command.
     *
     * @param commands User input.
     */
    public ExitCommand(String[] commands) {
        super(commands);
        this.isExit = true;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show("     Bye. Hope to see you again soon!\n");
        storage.save(taskList);
    }
}
