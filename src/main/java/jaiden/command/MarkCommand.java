package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String[] commands) {
        super(commands);
        this.isExit = false;
    }

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
