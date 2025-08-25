package jaiden.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void saveTest() {
        assertEquals("D | 0 | test | 2025-08-22", new Deadline("test", LocalDate.parse("2025-08-22")).save());
    }

    @Test
    public void isBetweenTest() {
        assertEquals(true, new Deadline("test", LocalDate.parse("2025-08-22")).isBy(LocalDate.parse("2025-08-22")));
    }

    @Test
    public void toStringTest() {
        assertEquals("[D][ ] test (by: Aug 22 2025)", new Deadline("test", LocalDate.parse("2025-08-22")).toString());
    }
}
