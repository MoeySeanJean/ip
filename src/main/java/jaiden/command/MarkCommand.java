package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

/**
 * Class for mark and unmark commands.
 */
public class MarkCommand extends Command {
    /**
     * Constructor for mark and unmark commands.
     *
     * @param commands User input.
     */
    public MarkCommand(String[] commands) {
        super(commands);
        this.isExit = false;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(commands[1]) - 1;
        String msg;
        if (commands[0].equals("mark")) {
            msg = taskList.mark(index);
        } else {
            msg = taskList.unmark(index);
        }
        ui.show(msg);
        storage.save(taskList);
    }
}
