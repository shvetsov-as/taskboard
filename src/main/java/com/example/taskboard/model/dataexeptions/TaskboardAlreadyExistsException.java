package com.example.taskboard.model.dataexeptions;

import java.util.UUID;

public class TaskboardAlreadyExistsException extends RuntimeException{
    public TaskboardAlreadyExistsException(UUID id){
        super("Taskboard with id [ " + id + " ] is already exists in database. Try to update previous taskboard record");
    }
}
