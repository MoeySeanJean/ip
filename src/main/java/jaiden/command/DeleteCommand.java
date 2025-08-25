package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

/**
 * Class for delete command.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor for delete command.
     *
     * @param commands User input.
     */
    public DeleteCommand(String[] commands) {
        super(commands);
        this.isExit = false;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(commands[1]) - 1;
        String msg = taskList.remove(index);
        ui.show(msg);
        storage.save(taskList);
    }
}
