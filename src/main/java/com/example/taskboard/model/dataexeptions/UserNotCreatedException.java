package com.example.taskboard.model.dataexeptions;

public class UserNotCreatedException extends RuntimeException{
    public UserNotCreatedException(String login){
        super("Could not create user with login [ " + login + " ]");
    }
}
