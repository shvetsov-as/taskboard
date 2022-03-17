package com.example.taskboard.model.service.converter.request;

import com.example.taskboard.entity.employees.Employees;
import com.example.taskboard.entity.employees.dto.EmployeesDtoRequest;
import com.example.taskboard.entity.users.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoEmployeesConverter {

    private final DtoUsersConverter dtoUsersConverter;

    public DtoEmployeesConverter(DtoUsersConverter dtoUsersConverter) {
        this.dtoUsersConverter = dtoUsersConverter;
    }

    public Employees convertToEntity (EmployeesDtoRequest employeesDtoRequest){
        Users user = dtoUsersConverter.convertToEntityCreate(employeesDtoRequest.getUser());
        return new Employees(employeesDtoRequest.getEmpSurname(), employeesDtoRequest.getEmpName(), employeesDtoRequest.getEmpMidname(), user);
    }

    public List<Employees> convertToEntity (List<EmployeesDtoRequest> employeesDtoRequest){
        return employeesDtoRequest.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}

