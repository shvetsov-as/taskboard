package com.example.taskboard.model.dataexeptions;

import java.util.UUID;

public class UserUsedByAnotherEmployeeException extends RuntimeException{
    public UserUsedByAnotherEmployeeException(UUID userId, UUID employeeId){
        super("User account with id [ " + userId + " ] is being used by employee id [ " + employeeId + " ]");
    }
}
