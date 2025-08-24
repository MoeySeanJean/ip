public abstract class Command {
    protected String[] commands;
    protected boolean isExit;

    public Command(String[] commands) {
        this.commands = commands;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
