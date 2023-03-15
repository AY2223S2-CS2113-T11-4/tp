package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;
import seedu.duke.exception.ToDoListException;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static boolean isInUse = true;

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        CommandParser parser = new CommandParser();
        try {
            taskList = Storage.loadData("./data.txt", ui);
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundMessage();
        } catch (ConversionErrorException e) {
            ui.printLoadingErrorMessage();
        }

        ui.printWelcomeMessage();
        try (Scanner in = new Scanner(System.in)) {
            while (isInUse) {
                try {
                    String userInput = in.nextLine();
                    Command parsedCommand = parser.parseCommand(userInput);
                    parsedCommand.execute(taskList, ui);
                    Storage.saveData("./data.txt", taskList, ui);
                    isInUse = !parsedCommand.isExit();
                } catch (IOException e) {
                    ui.printSavingErrorMessage();
                } catch (ToDoListException e) {
                    ui.printError(e);
                }
            }
        }
    }
}

