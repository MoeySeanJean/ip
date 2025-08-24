package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

import java.time.LocalDate;

public class ListCommand extends Command {
    public ListCommand(String[] commands) {
        super(commands);
        this.isExit = false;
    }

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
