package com.example.taskboard.model.dataexeptions;

public class PasswordsNotMatchException extends RuntimeException{
    public PasswordsNotMatchException() {
        super("Passwords not match in UsersDtoRequest");
    }
}
