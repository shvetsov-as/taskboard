package com.example.taskboard.model.service.converter.response;

import com.example.taskboard.entity.employees.dto.EmployeesDtoResponse;
import com.example.taskboard.entity.employees.Employees;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesConverter {

    private final UsersConverter usersConverter;

    public EmployeesConverter(UsersConverter usersConverter) {
        this.usersConverter = usersConverter;
    }

    public EmployeesDtoResponse convertToDto(Employees employee) {
        return new EmployeesDtoResponse(employee.getEmpId(), employee.getEmpSurname(),
                employee.getEmpName(), employee.getEmpMidname(), usersConverter.convertToDto(employee.getUser()));
    }

    public List<EmployeesDtoResponse> convertToDto(List<Employees> employees) {
        return employees.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public EmployeesDtoResponse convertToDtoWithoutUser(Employees employee) {

        return new EmployeesDtoResponse(employee.getEmpId(),
                employee.getEmpSurname(), employee.getEmpName(), employee.getEmpMidname());
    }

    public List<EmployeesDtoResponse> convertToDtoWithoutUser(List<Employees> employees) {
        return employees.stream().map(this::convertToDtoWithoutUser).collect(Collectors.toList());

    }
}
