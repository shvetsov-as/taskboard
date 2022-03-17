package com.example.taskboard.model.dataexeptions;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(Long id){
        super("Could not find any data by id [ " + id + " ]");
    }
}
