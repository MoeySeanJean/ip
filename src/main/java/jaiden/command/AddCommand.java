package jaiden.command;

import java.time.LocalDate;

import jaiden.storage.Storage;
import jaiden.task.Deadline;
import jaiden.task.Event;
import jaiden.task.Task;
import jaiden.task.TaskList;
import jaiden.task.Todo;

/**
 * Class for add command.
 */
public class AddCommand extends Command {
    /**
     * Constructor for add command.
     *
     * @param inputs User input.
     */
    public AddCommand(String[] inputs) {
        super(inputs);
        this.commandType = CommandType.ADDCOMMAND;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) {
        String description = inputs[1];
        Task task;
        if (inputs[0].equals("todo")) {
            task = new Todo(description);
        } else if (inputs[0].equals("deadline")) {
            LocalDate by = LocalDate.parse(inputs[3]);
            task = new Deadline(description, by);
        } else {
            LocalDate from = LocalDate.parse(inputs[3]);
            LocalDate to = LocalDate.parse(inputs[5]);
            task = new Event(description, from, to);
        }
        this.string = taskList.add(task);
        storage.save(taskList);
    }
}
