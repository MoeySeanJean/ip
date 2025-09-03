package jaiden.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import jaiden.storage.Storage;
import jaiden.task.Task;
import jaiden.task.TaskList;
import jaiden.task.Todo;
import jaiden.ui.Ui;

public class AddCommandTest {
    @Test
    public void isExitTest() {
        String[] commands = {"todo", "test"};
        assertEquals(false, new AddCommand(commands).isExit());
    }

    @Test
    public void executeTest() {
        String[] commands1 = {"todo", "test"};
        TaskList test1 = new TaskList();
        new AddCommand(commands1).execute(test1, new Ui(), new Storage("data/test.txt"));
        ArrayList<Task> tasks1 = new ArrayList<>();
        tasks1.add(new Todo("test"));
        TaskList expected1 = new TaskList(tasks1);
        assertEquals(expected1, test1);

        String[] commands2 = {"deadline", "test", "/by", "2025-08-22"};
        TaskList test2 = new TaskList();
        new AddCommand(commands2).execute(test2, new Ui(), new Storage("data/test.txt"));
        ArrayList<Task> tasks2 = new ArrayList<>();
        tasks2.add(new Todo("test"));
        TaskList expected2 = new TaskList(tasks2);
        assertEquals(expected2, test2);

        String[] commands3 = {"event", "test", "/from", "2025-08-22", "/to", "2025-08-22"};
        TaskList test3 = new TaskList();
        new AddCommand(commands3).execute(test3, new Ui(), new Storage("data/test.txt"));
        ArrayList<Task> tasks3 = new ArrayList<>();
        tasks3.add(new Todo("test"));
        TaskList expected3 = new TaskList(tasks3);
        assertEquals(expected3, test3);
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"todo", "test1"};
        String[] commands2 = {"todo", "test1"};
        String[] commands3 = {"todo", "test2"};
        assertEquals(true, new AddCommand(commands1).equals(new AddCommand(commands2)));
        assertEquals(false, new AddCommand(commands1).equals(new AddCommand(commands3)));
    }
}
