package jaiden.storage;

import jaiden.exception.DukeException;
import jaiden.task.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    void load_validFormat_success() throws Exception {
        new File("./data").mkdir();
        FileWriter testWriter = new FileWriter("data/test.txt");
        testWriter.write("T | 0 | test\nD | 0 | test | 2025-08-22\nE | 0 | test | 2025-08-22 | 2025-08-22\nT | 1 | test\nD | 1 | test | 2025-08-22\nE | 1 | test | 2025-08-22 | 2025-08-22");
        testWriter.close();
        ArrayList<Task> test = new ArrayList<>();
        test.add(new Todo("test"));
        test.add(new Deadline("test", LocalDate.parse("2025-08-22")));
        test.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        test.add(new Todo("test", true));
        test.add(new Deadline("test", true, LocalDate.parse("2025-08-22")));
        test.add(new Event("test", true, LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        assertEquals(test, new Storage("data/test.txt").load());
    }

    @Test
    void load_invalidFormat_exceptionThrown () {
        try {
            new File("./data").mkdir();
            FileWriter testWriter = new FileWriter("data/test.txt");
            testWriter.write(" | 0 | test");
            testWriter.close();
            new Storage("data/test.txt").load();
            fail();
        } catch (Exception e) {
            assertEquals(new DukeException(), e);
        }
    }

    @Test
    void saveTest() throws Exception {
        Storage storage = new Storage("data/test.txt");
        ArrayList<Task> test = new ArrayList<>();
        test.add(new Todo("test"));
        test.add(new Deadline("test", LocalDate.parse("2025-08-22")));
        test.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        test.add(new Todo("test", true));
        test.add(new Deadline("test", true, LocalDate.parse("2025-08-22")));
        test.add(new Event("test", true, LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        storage.save(new TaskList(test));
        assertEquals("T | 0 | test\nD | 0 | test | 2025-08-22\nE | 0 | test | 2025-08-22 | 2025-08-22\nT | 1 | test\nD | 1 | test | 2025-08-22\nE | 1 | test | 2025-08-22 | 2025-08-22", new Scanner(new File("data/test.txt")).useDelimiter("\\Z").next());
    }
}
