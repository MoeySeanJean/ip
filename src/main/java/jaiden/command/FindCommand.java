package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String[] commands) {
        super(commands);
        this.isExit = false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String msg = taskList.find(commands[1]);
        ui.show(msg);
        storage.save(taskList);
    }
}
