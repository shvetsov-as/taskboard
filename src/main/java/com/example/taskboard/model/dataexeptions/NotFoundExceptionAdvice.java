package com.example.taskboard.model.dataexeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String elementNotFoundHandler(ElementNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String dataNotFoundHandler(DataNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String exceptionHandler(Exception ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PasswordsNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String passwordsNotMatchExceptionHandler(PasswordsNotMatchException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PasswordNotMatchRequirementsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String passwordNotMatchRequirementsHandler(PasswordNotMatchRequirementsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String UserNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserNotCreatedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String UserNotCreatedHandler(UserNotCreatedException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EmployeeNotFoundHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmployeeNotCreatedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String EmployeeNotCreatedHandler(EmployeeNotCreatedException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TaskboardAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String TaskboardAlreadyExistsHandler(TaskboardAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserUsedByAnotherEmployeeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String UserUsedByAnotherEmployeeHandler(UserUsedByAnotherEmployeeException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TaskNotCreatedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String TaskNotCreatedHandler(TaskNotCreatedException ex) {
        return ex.getMessage();
    }
}
