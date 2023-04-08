package seedu.todolist.logic.command;
import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFindTagException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.model.Config;
import seedu.todolist.model.Task;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;
import java.util.ArrayList;
import java.util.HashMap;

//@@author KedrianLoh
public class FindByTagCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_FIND_TAG};

    private String tag;

    public FindByTagCommand(HashMap<Flags, String> args) throws ToDoListException {
        if (args.containsKey(Flags.COMMAND_FIND_TAG)) {
            tag = args.get(Flags.COMMAND_FIND_TAG);
        } else {
            throw new InvalidFindTagException();
        }
    }
    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidFindTagException {
        if (taskList.getAllTags().contains(tag)) {
            ArrayList<Task> arrayList = taskList.getTaskWithTag(tag);
            ui.printTasksWithTag(arrayList, tag);
        } else {
            throw new InvalidFindTagException();
        }
    }
}
