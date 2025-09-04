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
     * @param commands User input.
     */
    public AddCommand(String[] commands) {
        super(commands);
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) {
        String description = commands[1];
        Task task;
        if (commands[0].equals("todo")) {
            task = new Todo(description);
        } else if (commands[0].equals("deadline")) {
            LocalDate by = LocalDate.parse(commands[3]);
            task = new Deadline(description, by);
        } else {
            LocalDate from = LocalDate.parse(commands[3]);
            LocalDate to = LocalDate.parse(commands[5]);
            task = new Event(description, from, to);
        }
        this.string = taskList.add(task);
        storage.save(taskList);
    }
}
