package seedu.todolist.logic.command;
import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFindDescriptionException;
import seedu.todolist.model.Config;
import seedu.todolist.model.Task;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;
import java.util.ArrayList;
import java.util.HashMap;

//@@author KedrianLoh
public class FindByDescriptionCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_FIND_DESCRIPTION};

    private String description;

    public FindByDescriptionCommand(HashMap<Flags, String> args) throws InvalidFindDescriptionException {
        if (args.containsKey(Flags.COMMAND_FIND_DESCRIPTION)) {
            description = args.get(Flags.COMMAND_FIND_DESCRIPTION);
        } else {
            throw new InvalidFindDescriptionException();
        }
    }
    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidFindDescriptionException {
        if (taskList.getAllDescriptionInTaskList().contains(description)) {
            ArrayList<Task> arrayList = taskList.getTaskWithDescription(description);
            ui.printTasksWithDescription(arrayList, description);
        } else {
            throw new InvalidFindDescriptionException();
        }
    }
}
