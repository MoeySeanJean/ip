package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

import java.time.LocalDate;

/**
 * Class for list and show commands.
 */
public class ListCommand extends Command {
    /**
     * Constructor for list and show commands.
     *
     * @param commands User input.
     */
    public ListCommand(String[] commands) {
        super(commands);
        this.isExit = false;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String msg;
        if (commands[0].equals("list")) {
            msg = taskList.list();
        } else {
            LocalDate showDate = LocalDate.parse(commands[1]);
            msg = taskList.show(showDate);
        }
        ui.show(msg);
        storage.save(taskList);
    }
}
