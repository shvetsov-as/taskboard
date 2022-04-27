package com.example.taskboard.model.service.dataservice.employees;

import com.example.taskboard.entity.employees.Employees;
import com.example.taskboard.entity.employees.dto.EmployeesDtoRequest;
import com.example.taskboard.entity.employees.dto.EmployeesDtoResponse;
import com.example.taskboard.entity.employees.dto.EmployeesDtoShortRequest;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IEmployeesDataService {
    List<EmployeesDtoResponse> findAll();
    DtoPage<EmployeesDtoResponse> findAllPageable(Pageable pageable);
    EmployeesDtoResponse findById(UUID id);
    Employees findByIdNoConvert(UUID id);
    EmployeesDtoResponse findEmployeesByFullName(String empSurname, String empName, String empMidname);
    Boolean deleteById(UUID id);
    EmployeesDtoResponse create(EmployeesDtoRequest employeesDtoRequest);
    EmployeesDtoResponse createEmpIfUserExist(UUID userId, EmployeesDtoShortRequest employeesDtoShortRequest);
    Boolean updateUser(UUID empid, UUID userId);
    Boolean update(UUID id, EmployeesDtoRequest employeesDtoRequest);
}

