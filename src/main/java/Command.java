public enum Command {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    SHOW,
    BYE,
    UNKNOWN;

    public static Command toCommand(String command) {
        if (command.equals("list")) {
            return Command.LIST;
        } else if (command.startsWith("mark")) {
            return Command.MARK;
        } else if (command.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (command.startsWith("todo")) {
            return Command.TODO;
        } else if (command.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (command.startsWith("event")) {
            return Command.EVENT;
        } else if (command.startsWith("delete")) {
            return Command.DELETE;
        } else if (command.startsWith("show")) {
            return Command.SHOW;
        } else if (command.equals("bye")) {
            return Command.BYE;
        } else {
            return Command.UNKNOWN;
        }
    }

}
