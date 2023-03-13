package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.ToDoListException;
import seedu.duke.ui.Ui;
import seedu.duke.task.TaskList;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

public class EditDeadlineCommandTest {
    private static final String DATE_EXAMPLE = "06-04-2000";
    private static final String TIME_EXAMPLE = "23:59";

    private CommandParser parser = new CommandParser();
    private TaskList testList;
    private Ui ui = new Ui();

    private HashMap<String, String> generateInputArguments(String index, String date, String time) {
        HashMap<String, String> args = new HashMap<>();
        args.put(AddTaskCommand.KEYWORD, index);
        args.put(ArgumentList.DEADLINE_FLAG, date + " " + time);
        return args;
    }

    @BeforeEach
    void setUp() {
        final String SAMPLE_TASK = "add something -d 02-02-2023 18:00";
        testList = new TaskList();
        try {
            parser.parseCommand(SAMPLE_TASK).execute(testList, ui);
        } catch (ToDoListException e) {
            fail("Failed to initialize test task list");
        }
    }
    
    @Test
    public void editDeadline_invalidIndex_throwsException() {
        final String[] invalidIndexes = {"", "]", "wq", "7.5", "-3"};
        for (String index : invalidIndexes) {
            HashMap<String, String> args = generateInputArguments(index, DATE_EXAMPLE, TIME_EXAMPLE);
            try {
                Command testEdit = new EditDeadlineCommand(args);
                testEdit.execute(testList, ui);
            } catch (ToDoListException e) {
                continue;
            }
            fail("An edit command was successfully constructed with invalid index: " + index);
        }
    }

    @Test
    public void editDeadline_invalidDate_throwsException() {
        final String[] invalidDates = {"31/06/2032", "493430", "2013-12-22", "1023-43-22"};
        for (String date : invalidDates) {
            HashMap<String, String> args = generateInputArguments("0", date, TIME_EXAMPLE);
            try {
                Command testEdit = new EditDeadlineCommand(args);
                testEdit.execute(testList, ui);
            } catch (ToDoListException e) {
                continue;
            }
            fail("An edit command was successfully constructed with invalid date: " + date);
        }
    }

    @Test
    public void editDeadline_invalidTime_throwsException() {
        final String[] invalidTimes = {"1000", "493430", "9AM"};
        for (String time : invalidTimes) {
            HashMap<String, String> args = generateInputArguments("0", DATE_EXAMPLE, time);
            try {
                Command testEdit = new EditDeadlineCommand(args);
                testEdit.execute(testList, ui);
            } catch (ToDoListException e) {
                continue;
            }
            fail("An edit command was successfully constructed with invalid time: " + time);
        }
    }
}
