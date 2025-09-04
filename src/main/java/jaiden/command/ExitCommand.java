package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;

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
        this.commandType = CommandType.EXITCOMMAND;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) {
        this.string = "Bye. Hope to see you again soon!";
        storage.save(taskList);
    }
}
