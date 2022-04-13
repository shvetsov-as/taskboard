package com.example.taskboard.entity.employees.mapper;

import com.example.taskboard.entity.employees.Employees;
import com.example.taskboard.entity.employees.dto.EmployeesDtoRequest;
import com.example.taskboard.entity.employees.dto.EmployeesDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EmployeesMapper {

    //EmployeesMapper INSTANCE = Mappers.getMapper(EmployeesMapper.class);

    Employees employeesDtoRequestToEmployees(EmployeesDtoRequest employeesDtoRequest);

    EmployeesDtoResponse employeesToEmployeesDtoResponse(Employees employee);

    List<EmployeesDtoResponse> employeesListToEmployeesDtoResponseList(List<Employees> employees);
}