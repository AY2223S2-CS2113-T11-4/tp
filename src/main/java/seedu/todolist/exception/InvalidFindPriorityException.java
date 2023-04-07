package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidFindPriorityException extends ToDoListException{
    public InvalidFindPriorityException() {
        super(Errors.INVALID_FIND_PRIORITY); }
}
