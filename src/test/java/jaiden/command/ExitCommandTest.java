package jaiden.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jaiden.storage.Storage;
import jaiden.task.TaskList;

public class ExitCommandTest {
    @Test
    public void executeTest() {
        String[] commands = {"bye"};
        TaskList test = new TaskList();
        new ExitCommand(commands).execute(test, new Storage("data/test.txt"));
        assertEquals(new TaskList(), test);
    }

    @Test
    public void getStringTest() {
        String[] commands = {"bye"};
        TaskList test = new TaskList();
        Command command = new ExitCommand(commands);
        command.execute(test, new Storage("data/test.txt"));
        assertEquals("Bye. Hope to see you again soon!", command.getString());
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"bye"};
        String[] commands2 = {"bye"};
        assertEquals(new ExitCommand(commands1), new ExitCommand(commands2));
    }
}
