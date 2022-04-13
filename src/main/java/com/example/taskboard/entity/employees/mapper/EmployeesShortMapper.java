package com.example.taskboard.entity.employees.mapper;

import com.example.taskboard.entity.employees.Employees;
import com.example.taskboard.entity.employees.dto.EmployeesDtoShortRequest;
import com.example.taskboard.entity.employees.dto.EmployeesDtoShortResponse;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EmployeesShortMapper {

    Employees employeesDtoShortRequestToEmployees(EmployeesDtoShortRequest employeesDtoShortRequest);

    EmployeesDtoShortResponse employeesToEmployeesDtoShortResponse(Employees employee);

    List<EmployeesDtoShortResponse> employeesListToEmployeesDtoShortResponseList(List<Employees> employees);
}