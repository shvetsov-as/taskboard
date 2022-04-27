package com.example.taskboard.model.dataexeptions;

import java.util.UUID;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(UUID id){
        super("Could not find any data by id [ " + id + " ]");
    }
}
