package seedu.todolist.logic.command;
import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFindEmailException;
import seedu.todolist.model.Config;
import seedu.todolist.model.Task;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;
import java.util.ArrayList;
import java.util.HashMap;

//@@author KedrianLoh
public class FindByEmailCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_FIND_EMAIL};

    private String email;

    public FindByEmailCommand(HashMap<Flags, String> args) throws InvalidFindEmailException {
        if (args.containsKey(Flags.COMMAND_FIND_EMAIL)) {
            email = args.get(Flags.COMMAND_FIND_EMAIL);
        } else {
            throw new InvalidFindEmailException();
        }
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidFindEmailException {
        if (taskList.getAllEmailsInTaskList().contains(email)) {
            ArrayList<Task> arrayList = taskList.getTaskWithEmail(email);
            ui.printTasksWithEmail(arrayList, email);
        } else {
            throw new InvalidFindEmailException();
        }
    }
}
