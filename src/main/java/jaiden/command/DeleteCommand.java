package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;

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
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) {
        int index = Integer.parseInt(commands[1]) - 1;
        this.string = taskList.remove(index);
        storage.save(taskList);
    }
}
