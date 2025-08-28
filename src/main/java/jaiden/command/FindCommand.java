package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

/**
 * Class for find command.
 */
public class FindCommand extends Command {
    /**
     * Constructor for find command.
     *
     * @param commands User input.
     */
    public FindCommand(String[] commands) {
        super(commands);
        this.isExit = false;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String msg = taskList.find(commands[1]);
        ui.show(msg);
        storage.save(taskList);
    }
}
