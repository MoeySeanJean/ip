package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Class for unknown command.
 */
public class UnknownCommand extends Command {
    /**
     * Constructor for unknown command.
     *
     * @param inputs User input.
     */
    public UnknownCommand(String[] inputs) {
        super(inputs);
        this.commandType = CommandType.ERRORCOMMAND;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) {
        this.string = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        storage.save(taskList);
    }
}
