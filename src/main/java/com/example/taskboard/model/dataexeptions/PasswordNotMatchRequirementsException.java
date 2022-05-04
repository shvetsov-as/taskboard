package com.example.taskboard.model.dataexeptions;

public class PasswordNotMatchRequirementsException extends RuntimeException{
    public PasswordNotMatchRequirementsException() {
        super("Password must contain at least 8 characters: 1 digit, 1 letter in upper case and 1 letter in lowercase. Example: Ab345678");
    }
}
