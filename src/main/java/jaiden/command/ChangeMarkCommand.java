package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Class for mark and unmark commands.
 */
public class ChangeMarkCommand extends Command {
    /**
     * Constructor for mark and unmark commands.
     *
     * @param commands User input.
     */
    public ChangeMarkCommand(String[] commands) {
        super(commands);
        this.commandType = CommandType.CHANGEMARKCOMMAND;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) {
        int index = Integer.parseInt(commands[1]) - 1;
        if (commands[0].equals("mark")) {
            this.string = taskList.mark(index);
        } else {
            this.string = taskList.unmark(index);
        }
        storage.save(taskList);
    }
}
