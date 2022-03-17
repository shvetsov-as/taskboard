package com.example.taskboard.model.dataexeptions;

public class TaskboardAlreadyExistsException extends RuntimeException{
    public TaskboardAlreadyExistsException(Long id){
        super("Taskboard with id [ " + id + " ] is already exists in database. Try to update previous taskboard record");
    }
}
