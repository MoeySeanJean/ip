package jaiden.command;

import jaiden.storage.Storage;
import jaiden.task.Task;
import jaiden.task.TaskList;
import jaiden.task.Todo;
import jaiden.ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void isExitTest() {
        String[] commands = {"delete", "1"};
        assertEquals(false, new DeleteCommand(commands).isExit());
    }

    @Test
    public void executeTest() {
        String[] commands = {"delete", "1"};
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test"));
        TaskList test= new TaskList(tasks);
        new DeleteCommand(commands).execute(test, new Ui(), new Storage("data/test.txt"));
        assertEquals(new TaskList(), test);
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"delete", "1"};
        String[] commands2 = {"delete", "1"};
        String[] commands3 = {"delete", "2"};
        assertEquals(true, new DeleteCommand(commands1).equals(new DeleteCommand(commands2)));
        assertEquals(false, new DeleteCommand(commands1).equals(new DeleteCommand(commands3)));
    }
}
