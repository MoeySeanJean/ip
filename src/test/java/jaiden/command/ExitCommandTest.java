package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
    @Test
    public void isExitTest() {
        String[] commands = {"bye"};
        assertEquals(true, new ExitCommand(commands).isExit());
    }

    @Test
    public void executeTest() {
        String[] commands = {"bye"};
        TaskList test = new TaskList();
        new ExitCommand(commands).execute(test, new Ui(), new Storage("data/test.txt"));
        assertEquals(new TaskList(), test);
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"bye"};
        String[] commands2 = {"bye"};
        assertEquals(true, new ExitCommand(commands1).equals(new ExitCommand(commands2)));
    }
}
