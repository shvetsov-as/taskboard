package com.example.taskboard.model.dataexeptions;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(){
        super("Could not find any data in database");
    }
}
