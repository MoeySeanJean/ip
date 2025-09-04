package jaiden.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.Test;

import jaiden.command.CommandType;

public class JaidenTest {
    @Test
    public void getResponseTest() throws Exception {
        new File("./data").mkdir();
        FileWriter testWriter = new FileWriter("data/test.txt");
        testWriter.write("");
        testWriter.close();
        Jaiden jaiden = new Jaiden("data/test.txt");
        assertEquals("Got it. I've added this task:\n[T][ ] read\nNow you have 1 tasks in the list.",
                jaiden.getResponse("todo read"));
        assertEquals("Got it. I've added this task:\n[D][ ] read (by: Aug 22 2025)\n"
                + "Now you have 2 tasks in the list.", jaiden.getResponse("deadline read /by 2025-08-22"));
        assertEquals("Got it. I've added this task:\n[E][ ] read (from: Aug 22 2025 to: Aug 22 2025)\n"
                + "Now you have 3 tasks in the list.",
                jaiden.getResponse("event read /from 2025-08-22 /to 2025-08-22"));
        assertEquals("Here are the tasks in your list:\n1.[T][ ] read\n2.[D][ ] read (by: Aug 22 2025)\n"
                + "3.[E][ ] read (from: Aug 22 2025 to: Aug 22 2025)\n", jaiden.getResponse("list"));
        assertEquals("Here are the tasks on Aug 22 2025 in your list:\n1.[D][ ] read (by: Aug 22 2025)\n"
                + "2.[E][ ] read (from: Aug 22 2025 to: Aug 22 2025)\n", jaiden.getResponse("show 2025-08-22"));
        assertEquals("Here are the matching tasks in your list:\n1.[T][ ] read\n2.[D][ ] read (by: Aug 22 2025)\n"
                + "3.[E][ ] read (from: Aug 22 2025 to: Aug 22 2025)\n", jaiden.getResponse("find read"));
        assertEquals("Nice! I've marked this task as done:\n[T][X] read", jaiden.getResponse("mark 1"));
        assertEquals("OK, I've marked this task as not done yet:\n[T][ ] read",
                jaiden.getResponse("unmark 1"));
        assertEquals("Noted. I've removed this task:\n[T][ ] read\nNow you have 2 tasks in the list.",
                jaiden.getResponse("delete 1"));
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                jaiden.getResponse("test"));
        assertEquals("Bye. Hope to see you again soon!", jaiden.getResponse("bye"));
        assertEquals("Error: OOPS!!! The description of a todo cannot be empty.",
                jaiden.getResponse("todo"));
        assertEquals("Error: OOPS!!! The description of a deadline cannot be empty.",
                jaiden.getResponse("deadline"));
        assertEquals("Error: OOPS!!! The by of a deadline cannot be empty.",
                jaiden.getResponse("deadline test"));
        assertEquals("Error: OOPS!!! The description of a event cannot be empty.",
                jaiden.getResponse("event"));
        assertEquals("Error: OOPS!!! The from of a event cannot be empty.",
                jaiden.getResponse("event test"));
        assertEquals("Error: OOPS!!! The to of a event cannot be empty.",
                jaiden.getResponse("event test /from 2025-08-22"));
        assertEquals("Error: OOPS!!! The index of a mark cannot be empty.", jaiden.getResponse("mark"));
        assertEquals("Error: OOPS!!! The index of a unmark cannot be empty.",
                jaiden.getResponse("unmark"));
        assertEquals("Error: OOPS!!! The index of a delete cannot be empty.",
                jaiden.getResponse("delete"));
        assertEquals("Error: OOPS!!! The date of a show cannot be empty.", jaiden.getResponse("show"));
        assertEquals("Error: OOPS!!! The text of a find cannot be empty.", jaiden.getResponse("find"));
    }

    @Test
    public void getCommandTypeTest() {
        Jaiden jaiden = new Jaiden("data/test.txt");
        jaiden.getResponse("todo read");
        assertEquals(CommandType.ADDCOMMAND, jaiden.getCommandType());
        jaiden.getResponse("deadline read /by 2025-08-22");
        assertEquals(CommandType.ADDCOMMAND, jaiden.getCommandType());
        jaiden.getResponse("event read /from 2025-08-22 /to 2025-08-22");
        assertEquals(CommandType.ADDCOMMAND, jaiden.getCommandType());
        jaiden.getResponse("list");
        assertEquals(CommandType.LISTCOMMAND, jaiden.getCommandType());
        jaiden.getResponse("show 2025-08-22");
        assertEquals(CommandType.LISTCOMMAND, jaiden.getCommandType());
        jaiden.getResponse("find read");
        assertEquals(CommandType.LISTCOMMAND, jaiden.getCommandType());
        jaiden.getResponse("mark 1");
        assertEquals(CommandType.CHANGEMARKCOMMAND, jaiden.getCommandType());
        jaiden.getResponse("unmark 1");
        assertEquals(CommandType.CHANGEMARKCOMMAND, jaiden.getCommandType());
        jaiden.getResponse("delete 1");
        assertEquals(CommandType.DELETECOMMAND, jaiden.getCommandType());
        jaiden.getResponse("test");
        assertEquals(CommandType.ERRORCOMMAND, jaiden.getCommandType());
        jaiden.getResponse("mark");
        assertEquals(CommandType.ERRORCOMMAND, jaiden.getCommandType());
        jaiden.getResponse("bye");
        assertEquals(CommandType.EXITCOMMAND, jaiden.getCommandType());
    }
}
