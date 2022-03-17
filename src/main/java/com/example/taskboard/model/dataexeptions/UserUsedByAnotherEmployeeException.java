package com.example.taskboard.model.dataexeptions;

public class UserUsedByAnotherEmployeeException extends RuntimeException{
    public UserUsedByAnotherEmployeeException(Long userId, Long employeeId){
        super("User account with id [ " + userId + " ] is being used by employee id [ " + employeeId + " ]");
    }
}
