package jaiden.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {
    @Test
    public void getMessageTest() {
        assertEquals(null, new DukeException().getMessage());
        assertEquals("test", new DukeException("test").getMessage());
    }

    @Test
    public void equalsTest() {
        assertEquals(true, new DukeException().equals(new DukeException()));
        assertEquals(true, new DukeException("test").equals(new DukeException("test")));
        assertEquals(false, new DukeException().equals(new DukeException("test")));
    }
}
