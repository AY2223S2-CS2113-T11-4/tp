package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

//@@author KedrianLoh
public class InvalidFindTagException extends ToDoListException {
    public InvalidFindTagException() {
        super(Errors.INVALID_FIND_TAG); }
}
