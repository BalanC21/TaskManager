package com.codecool.todolist.exceptions;

import lombok.Value;

public class CompletedTasksException extends RuntimeException {
    public CompletedTasksException() {
        super("Completed Tasks not found");
    }
}
