package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

//@@author KedrianLoh
public class InvalidFindEmailException  extends ToDoListException{
    public InvalidFindEmailException() {
        super(Errors.INVALID_FIND_EMAIL);
    }
}
