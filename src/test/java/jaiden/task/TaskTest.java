package jaiden.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void getStatusIconTest() {
        assertEquals(" ", new Task("test").getStatusIcon());
        assertEquals("X", new Task("test", true).getStatusIcon());
    }

    @Test
    public void markAsDoneTest() {
        Task test = new Task("test");
        test.markAsDone();
        assertEquals("X", test.getStatusIcon());
    }

    @Test
    public void markAsNotDoneTest() {
        Task test = new Task("test");
        test.markAsNotDone();
        assertEquals(" ", test.getStatusIcon());
    }

    @Test
    public void saveTest() {
        assertEquals("0 | test", new Task("test").save());
        assertEquals("1 | test", new Task("test", true).save());
    }

    @Test
    public void toStringTest() {
        assertEquals("[ ] test", new Task("test").toString());
        assertEquals("[X] test", new Task("test", true).toString());
    }

    @Test
    public void equalsTest() {
        assertEquals(true, new Task("test").equals(new Task("test")));
        assertEquals(true, new Task("test", true).equals(new Task("test", true)));
        assertEquals(false, new Task("test1").equals(new Task("test2")));
        assertEquals(false, new Task("test", true).equals(new Task("test", false)));
    }
}
