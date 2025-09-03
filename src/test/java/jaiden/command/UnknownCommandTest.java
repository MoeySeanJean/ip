package jaiden.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;

public class UnknownCommandTest {
    @Test
    public void isExitTest() {
        String[] commands = {"test"};
        assertEquals(false, new UnknownCommand(commands).isExit());
    }

    @Test
    public void executeTest() {
        String[] commands = {"bye"};
        TaskList test = new TaskList();
        new UnknownCommand(commands).execute(test, new Ui(), new Storage("data/test.txt"));
        assertEquals(new TaskList(), test);
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"test"};
        String[] commands2 = {"test"};
        assertEquals(true, new UnknownCommand(commands1).equals(new UnknownCommand(commands2)));
    }
}
