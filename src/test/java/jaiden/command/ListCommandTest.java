package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.TaskList;
import jaiden.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void isExitTest() {
        String[] commands = {"list"};
        assertEquals(false, new ListCommand(commands).isExit());
    }

    @Test
    public void executeTest() {
        String[] commands1 = {"list"};
        TaskList test = new TaskList();
        new ListCommand(commands1).execute(test, new Ui(), new Storage("data/test.txt"));
        assertEquals(new TaskList(), test);
        String[] commands2 = {"show", "2025-08-22"};
        new ListCommand(commands2).execute(test, new Ui(), new Storage("data/test.txt"));
        assertEquals(new TaskList(), test);
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"list"};
        String[] commands2 = {"list"};
        String[] commands3 = {"show", "2025-08-22"};
        assertEquals(true, new ListCommand(commands1).equals(new ListCommand(commands2)));
        assertEquals(false, new ListCommand(commands1).equals(new ListCommand(commands3)));
    }
}
