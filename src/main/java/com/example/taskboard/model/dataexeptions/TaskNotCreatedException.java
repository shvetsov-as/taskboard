package com.example.taskboard.model.dataexeptions;

public class TaskNotCreatedException extends RuntimeException{
    public TaskNotCreatedException(String taskname){
        super("Could not create new task [ " + taskname + " ]");
    }
}
