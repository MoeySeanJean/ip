package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.Task;
import jaiden.task.TaskList;
import jaiden.task.Todo;
import jaiden.ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {
    @Test
    public void isExitTest() {
        String[] commands = {"mark", "1"};
        assertEquals(false, new MarkCommand(commands).isExit());
    }

    @Test
    public void executeTest() {
        String[] commands1 = {"mark", "1"};
        ArrayList<Task> tasks1 = new ArrayList<>();
        tasks1.add(new Todo("test"));
        TaskList test1 = new TaskList(tasks1);
        new MarkCommand(commands1).execute(test1, new Ui(), new Storage("data/test.txt"));
        ArrayList<Task> expectedTasks1 = new ArrayList<>();
        expectedTasks1.add(new Todo("test", true));
        TaskList expected1 = new TaskList(expectedTasks1);
        assertEquals(expected1, test1);

        String[] commands2 = {"unmark", "1"};
        ArrayList<Task> tasks2 = new ArrayList<>();
        tasks2.add(new Todo("test", true));
        TaskList test2 = new TaskList(tasks2);
        new MarkCommand(commands2).execute(test2, new Ui(), new Storage("data/test.txt"));
        ArrayList<Task> expectedTasks2 = new ArrayList<>();
        expectedTasks2.add(new Todo("test"));
        TaskList expected2 = new TaskList(expectedTasks2);
        assertEquals(expected2, test2);
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"mark", "1"};
        String[] commands2 = {"mark", "1"};
        String[] commands3 = {"mark", "2"};
        assertEquals(true, new MarkCommand(commands1).equals(new MarkCommand(commands2)));
        assertEquals(false, new MarkCommand(commands1).equals(new MarkCommand(commands3)));
    }
}
