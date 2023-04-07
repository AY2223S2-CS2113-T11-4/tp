package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidFindDescriptionException extends ToDoListException{
    public InvalidFindDescriptionException() {
        super(Errors.INVALID_FIND_DESCRIPTION); }
}
