package com.example.taskboard.model.dataexeptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String empSurname, String empName, String empMidname){
        super("Could not find any employee [ " + empSurname + " " + empName + " " + empMidname + " ]");
    }
}
