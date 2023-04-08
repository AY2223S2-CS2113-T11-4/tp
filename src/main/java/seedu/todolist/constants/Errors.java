package seedu.todolist.constants;

import seedu.todolist.storage.Storage;

/**
 * Enum that holds all the error messages to be displayed.
 */
public final class Errors {
    public static final String MISSING_ARGUMENT = "Missing argument after: ";

    public static final String INVALID_COMMAND = "You need to provide a valid command.";
    public static final String INVALID_FLAGS = "Unknown or duplicate flags are not allowed: ";
    public static final String INVALID_ID = "Invalid task id: ";
    public static final String INVALID_DATE = "Invalid deadline: ";
    public static final String OLD_DATE = "Deadline must be after the current date & time: ";
    public static final String INVALID_EMAIL = "Invalid email address: ";
    public static final String INVALID_PRIORITY = "Invalid priority: ";
    public static final String INVALID_DURATION = "Invalid repeat duration: ";
    public static final String INVALID_FIND_TAG = "Unable to find task with desired tag. Please try again.";
    //@@author KedrianLoh
    public static final String INVALID_FIND_DESCRIPTION = "Unable to find task with desired description. Please try " +
            "again.";
    public static final String INVALID_FIND_EMAIL = "Unable to find task with desired email. Please try again.";
    public static final String INVALID_FIND_PRIORITY = "Unable to find task wth desired priority. Please try again.";
    //@@author clement559
    public static final String INVALID_EDIT = "Task edit commands must have either "
            + Flags.EDIT.getName() + " or " + Flags.EDIT_DELETE.getName() + ", not both or neither.";
    public static final String INVALID_TAG_EDIT = "Tag edit commands must have exactly one of "
            + Flags.EDIT.getName() + ", " + Flags.EDIT_ADD.getName() + ", or " + Flags.EDIT_DELETE.getName();
    public static final String INVALID_SELECT = "Commands taking IDs/filters must have either "
            + "IDs or filters, not both or neither.";
    public static final String INVALID_FREQUENCY = "Invalid frequency: ";
    public static final String INVALID_BOOLEAN = "Invalid boolean value: ";
    public static final String INVALID_SORT = "Invalid sort option: ";

    //@@author clement559
    public static final String FAILED_SAVE = "Error when saving to "
            + Storage.DEFAULT_DATA_PATH + " and " + Storage.DEFAULT_CONFIG_PATH + System.lineSeparator()
            + "Check that these are not directories and that you have write permissions for them.";
    public static final String FAILED_LOAD_DATA = "Error when loading your data from "
            + Storage.DEFAULT_DATA_PATH + System.lineSeparator()
            + "A new task list will be created for you.";
    public static final String FAILED_LOAD_CONFIG = "Error when loading your configs from "
            + Storage.DEFAULT_CONFIG_PATH + System.lineSeparator()
            + "Your settings will be reset to the default.";
    private Errors() {
    }
}
