package com.example.taskboard.model.service.converter.request;

import com.example.taskboard.entity.employees.Employees;
import com.example.taskboard.entity.employees.dto.EmployeesDtoShortRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoEmployeesShortConverter {

    public Employees convertToEntity (EmployeesDtoShortRequest employeesDtoShortRequest){
        return new Employees(employeesDtoShortRequest.getEmpSurname(), employeesDtoShortRequest.getEmpName(), employeesDtoShortRequest.getEmpMidname());
    }

    public List<Employees> convertToEntity (List<EmployeesDtoShortRequest> employeesDtoShortRequest){
        return employeesDtoShortRequest.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}

