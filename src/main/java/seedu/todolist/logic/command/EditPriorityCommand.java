//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.InvalidSelectException;
import seedu.todolist.model.Priority;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.Task;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;

/**
 * Command class that will edit/delete the priority level of Task objects of the given TaskList object.
 */
public class EditPriorityCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_PRIORITY, Flags.EDIT, Flags.EDIT_DELETE,
        Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.FILTER_ALL,
        Flags.DESCRIPTION, Flags.EMAIL, Flags.REPEAT, Flags.TAG, Flags.PRIORITY};

    private Priority priority = Priority.NONE;
    private HashSet<Integer> idHashSet;
    private Predicate<Task> predicate;

    /**
     * Constructs a EditPriorityCommand object by parsing the provided arguments.
     * Can select tasks to edit by providing a list of ids, or one or more filters.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws ToDoListException If ids/filters are invalid, or if both ids and filters are provided,
     *                           or if the given priority level is invalid.
     */
    public EditPriorityCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_PRIORITY));
        predicate = ParserUtil.parseFilter(args);
        if (idHashSet.isEmpty() == (predicate == null)) {
            throw new InvalidSelectException();
        }
        if (args.containsKey(Flags.EDIT) == args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        } else if (args.containsKey(Flags.EDIT)) {
            priority = ParserUtil.parsePriority(args.get(Flags.EDIT));
        }
        assert args.size() > 1: "Fewer arguments than expected!";
    }

    /**
     * Edits/deletes the priority level of tasks specified in the constructor.
     *
     * @param taskList The task list to edit tasks from.
     * @param ui The Ui object used to display the result of editing priority levels.
     * @throws InvalidIdException If the given task list does not contain tasks with the specified ids.
     */
    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws ToDoListException {
        String taskString = predicate == null
                ? taskList.setPriority(idHashSet, priority)
                : taskList.setPriority(predicate, priority);

        if (taskString.isEmpty()) {
            ui.printFilteredNoTasksFoundMessage();
        } else if (priority == Priority.NONE) {
            ui.printEditDeleteTaskMessage("priority level", taskString);
        } else {
            ui.printEditTaskMessage("priority level", priority.toString(), taskString);
        }
    }
}
