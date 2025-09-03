package jaiden.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jaiden.command.AddCommand;
import jaiden.command.DeleteCommand;
import jaiden.command.ExitCommand;
import jaiden.command.ListCommand;
import jaiden.command.MarkCommand;
import jaiden.command.UnknownCommand;

public class ParserTest {
    @Test
    public void parseTest() {
        assertEquals(new ListCommand(new String[]{"list"}), new Parser().parse("list"));
        assertEquals(new ListCommand(new String[]{"show", "2025-08-22"}), new Parser().parse("show 2025-08-22"));
        assertEquals(new MarkCommand(new String[]{"mark", "1"}), new Parser().parse("mark 1"));
        assertEquals(new MarkCommand(new String[]{"unmark", "1"}), new Parser().parse("unmark 1"));
        assertEquals(new AddCommand(new String[]{"todo", "test"}), new Parser().parse("todo test"));
        assertEquals(new AddCommand(new String[]{"deadline", "test", "/by", "2025-08-22"}),
                new Parser().parse("deadline test /by 2025-08-22"));
        assertEquals(new AddCommand(new String[]{"event", "test", "/from", "2025-08-22", "/to", "2025-08-22"}),
                new Parser().parse("event test /from 2025-08-22 /to 2025-08-22"));
        assertEquals(new DeleteCommand(new String[]{"delete", "1"}), new Parser().parse("delete 1"));
        assertEquals(new ExitCommand(new String[]{"bye"}), new Parser().parse("bye"));
        assertEquals(new UnknownCommand(new String[]{"unknown"}), new Parser().parse("unknown"));
    }
}
