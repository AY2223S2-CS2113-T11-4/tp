package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.task.TaskList;

/**
 * Command for terminating the program.
 */
public class ExitCommand extends Command {
    public static final String KEYWORD = "exit";

    /**
     * Displays the program's shutdown message.
     */
    public void execute(TaskList taskList, Ui ui) {
        ui.printGoodbyeMessage();
    }

    /**
     * Returns whether this command should terminate the program.
     *
     * @return <code>True</code>, since this is the exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
