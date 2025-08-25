package jaiden.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void saveTest() {
        assertEquals("T | 0 | test", new Todo("test").save());
    }

    @Test
    public void toStringTest() {
        assertEquals("[T][ ] test", new Todo("test").toString());
    }
}
