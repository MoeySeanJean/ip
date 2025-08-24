package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String[] commands) {
        super(commands);
        this.isExit = true;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show("     Bye. Hope to see you again soon!\n");
        storage.save(taskList);
    }
}
