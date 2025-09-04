package jaiden.command;

import java.time.LocalDate;

import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Class for list, show and find commands.
 */
public class ListCommand extends Command {
    /**
     * Constructor for list, show and find commands.
     *
     * @param inputs User input.
     */
    public ListCommand(String[] inputs) {
        super(inputs);
        this.commandType = CommandType.LISTCOMMAND;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) {
        if (inputs[0].equals("list")) {
            this.string = taskList.list();
        } else if (inputs[0].equals("show")) {
            LocalDate showDate = LocalDate.parse(inputs[1]);
            this.string = taskList.show(showDate);
        } else {
            this.string = taskList.find(inputs[1]);
        }
        storage.save(taskList);
    }
}
