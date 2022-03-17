package com.example.taskboard.model.dataexeptions;

public class PasswordNotMatchRequirementsException extends RuntimeException{
    public PasswordNotMatchRequirementsException() {
        super("Password not match to Requirements in UsersDtoRequest");
    }
}
