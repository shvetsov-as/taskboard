package com.example.taskboard.model.dataexeptions;

public class EmployeeNotCreatedException extends RuntimeException{
    public EmployeeNotCreatedException(String empSurname, String empName, String empMidname){
        super("Could not create employee [ " + empSurname + " " + empName + " " + empMidname + " ]");
    }
}
