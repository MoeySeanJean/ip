public class DeleteCommand extends Command {
    public DeleteCommand(String[] commands) {
        super(commands);
        this.isExit = false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(commands[1]) - 1;
        String msg = taskList.remove(index);
        ui.show(msg);
        storage.save(taskList);
    }
}
