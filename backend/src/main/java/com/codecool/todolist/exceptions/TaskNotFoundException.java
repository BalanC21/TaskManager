package com.codecool.todolist.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Task with Id: " + id + " not found");
    }
}
