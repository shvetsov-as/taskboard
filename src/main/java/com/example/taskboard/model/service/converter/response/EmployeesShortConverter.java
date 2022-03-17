package com.example.taskboard.model.service.converter.response;

import com.example.taskboard.entity.employees.dto.EmployeesDtoShortResponse;
import com.example.taskboard.entity.employees.Employees;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesShortConverter {

    public EmployeesDtoShortResponse convertToDto(Employees employee) {
        return new EmployeesDtoShortResponse(employee.getEmpId(),
                employee.getEmpSurname(), employee.getEmpName(), employee.getEmpMidname());
    }

    public List<EmployeesDtoShortResponse> convertToDto(List<Employees> employees) {
        return employees.stream().map(this::convertToDto).collect(Collectors.toList());

    }

}
