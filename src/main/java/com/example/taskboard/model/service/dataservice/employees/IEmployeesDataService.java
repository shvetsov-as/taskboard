package com.example.taskboard.model.service.dataservice.employees;

import com.example.taskboard.entity.employees.Employees;
import com.example.taskboard.entity.employees.dto.EmployeesDtoRequest;
import com.example.taskboard.entity.employees.dto.EmployeesDtoResponse;
import com.example.taskboard.entity.employees.dto.EmployeesDtoShortRequest;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployeesDataService {
    List<EmployeesDtoResponse> findAll();
    DtoPage<EmployeesDtoResponse> findAllPageable(Pageable pageable);
    EmployeesDtoResponse findById(Long id);
    Employees findByIdNoConvert(Long id);
    EmployeesDtoResponse findEmployeesByFullName(String empSurname, String empName, String empMidname);
    Boolean deleteById(Long id);
    EmployeesDtoResponse create(EmployeesDtoRequest employeesDtoRequest);
    EmployeesDtoResponse createEmpIfUserExist(Long userId, EmployeesDtoShortRequest employeesDtoShortRequest);
    Boolean updateUser(Long empid, Long userId);
    Boolean update(Long id, EmployeesDtoRequest employeesDtoRequest);
}

