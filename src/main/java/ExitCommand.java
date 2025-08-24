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
