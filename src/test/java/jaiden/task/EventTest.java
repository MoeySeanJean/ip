package jaiden.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void saveTest() {
        assertEquals("E | 0 | test | 2025-08-22 | 2025-08-22", new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")).save());
    }

    @Test
    public void isBetweenTest() {
        assertEquals(true, new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")).isBetween(LocalDate.parse("2025-08-22")));
    }

    @Test
    public void toStringTest() {
        assertEquals("[E][ ] test (from: Aug 22 2025 to: Aug 22 2025)", new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")).toString());
    }
}
