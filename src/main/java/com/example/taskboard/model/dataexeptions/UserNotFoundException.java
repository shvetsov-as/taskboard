package com.example.taskboard.model.dataexeptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String login){
        super("Could not find any user with login [ " + login + " ]");
    }
}
