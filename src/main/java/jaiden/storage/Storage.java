package jaiden.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import jaiden.exception.JaidenException;
import jaiden.task.Deadline;
import jaiden.task.Event;
import jaiden.task.Task;
import jaiden.task.TaskList;
import jaiden.task.Todo;

/**
 * Class for storage.
 */
public class Storage {
    private final File data;

    /**
     * Constructor for storage.
     *
     * @param filePath File path to save data in txt format.
     */
    public Storage(String filePath) {
        this.data = new File(filePath);
    }

    /**
     * Loads data from local storage.
     *
     * @return List of tasks.
     * @throws JaidenException If invalid format from local storage.
     */
    public ArrayList<Task> load() throws JaidenException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner dataReader;
        if (!this.data.exists()) {
            new File("./data").mkdir();
        } else {
            try {
                dataReader = new Scanner(this.data);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (dataReader.hasNextLine()) {
                String line = dataReader.nextLine();
                String[] temp = line.split(" \\| ");
                if (temp.length == 0) {
                    throw new JaidenException("The data file is corrupted (Content not in the expected format).");
                } else if (temp[0].equals("T")) {
                    if (temp.length != 3 || !(temp[1].equals("0") || temp[1].equals("1")) || temp[2].isBlank()) {
                        throw new JaidenException("The data file is corrupted (Content not in the expected format).");
                    }
                    tasks.add(new Todo(temp[2], temp[1].equals("1")));
                } else if (temp[0].equals("D")) {
                    if (temp.length != 4
                            || !(temp[1].equals("0") || temp[1].equals("1"))
                                || temp[2].isBlank() || temp[3].isBlank()) {
                        throw new JaidenException("The data file is corrupted (Content not in the expected format).");
                    }
                    tasks.add(new Deadline(temp[2], temp[1].equals("1"), LocalDate.parse(temp[3])));
                } else if (temp[0].equals("E")) {
                    if (temp.length != 5
                            || !(temp[1].equals("0") || temp[1].equals("1"))
                                || temp[2].isBlank() || temp[3].isBlank() || temp[4].isBlank()) {
                        throw new JaidenException("The data file is corrupted (Content not in the expected format).");
                    }
                    tasks.add(new Event(temp[2], temp[1].equals("1"),
                            LocalDate.parse(temp[3]), LocalDate.parse(temp[4])));
                } else {
                    throw new JaidenException("The data file is corrupted (Content not in the expected format).");
                }
            }
        }
        return tasks;
    }

    /**
     * Writes to local storage.
     *
     * @param tasks Task list to be saved.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter dataWriter = new FileWriter(this.data);
            String msg = tasks.save();
            dataWriter.write(msg);
            dataWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
