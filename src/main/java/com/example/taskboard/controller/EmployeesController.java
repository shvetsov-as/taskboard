package com.example.taskboard.controller;

import com.example.taskboard.entity.employees.dto.EmployeesDtoRequest;
import com.example.taskboard.entity.employees.dto.EmployeesDtoResponse;
import com.example.taskboard.entity.employees.dto.EmployeesDtoShortRequest;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.service.dataservice.employees.IEmployeesDataService;
import com.example.taskboard.model.service.uriBuilder.CustomUriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "API internal type")
@Tag(name = "API v.1")
@Tag(name = "Employee controller")
@RestController
@RequestMapping("/api/v1")
public class EmployeesController {

    private final IEmployeesDataService employeesDataService;
    private final CustomUriComponentsBuilder customUriComponentsBuilder;
    private final String pathParameterPrefix = "/employee/id=";

    public EmployeesController(IEmployeesDataService employeesDataService,
                               CustomUriComponentsBuilder customUriComponentsBuilder) {
        this.employeesDataService = employeesDataService;
        this.customUriComponentsBuilder = customUriComponentsBuilder;
    }

    @Operation(summary = "find all employees")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeesDtoResponse>> findAll() {
        return ResponseEntity.ok(employeesDataService.findAll());
    }

    @Operation(summary = "find employee with pagination")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/employees/param")
    public ResponseEntity<DtoPage<EmployeesDtoResponse>> findAllPageable(@RequestParam(name = "page") Integer page,
                                                                         @RequestParam(name = "size") Integer size) {
        return ResponseEntity.ok(employeesDataService.findAllPageable(PageRequest.of(page, size)));
    }

    @Operation(summary = "find employee by full name")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/employees/find-by-full-name")
    public ResponseEntity<EmployeesDtoResponse> findByFullName(@RequestParam String surname,
                                                               @RequestParam String name,
                                                               @RequestParam(name = "middle-name") String midname) {
        return ResponseEntity.ok(employeesDataService.findEmployeesByFullName(surname, name, midname));
    }

    @Operation(summary = "find employee by Id")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeesDtoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(employeesDataService.findById(id));
    }

    @Operation(summary = "create new employee")
    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping("/employees")
    public ResponseEntity<EmployeesDtoResponse> createEmployee(@RequestBody EmployeesDtoRequest employeesDtoRequest) {
        employeesDataService.create(employeesDtoRequest);
        EmployeesDtoResponse employee = employeesDataService.findEmployeesByFullName(employeesDtoRequest.getEmpSurname(),
                employeesDtoRequest.getEmpName(),
                employeesDtoRequest.getEmpMidname());

        return ResponseEntity
                .created(customUriComponentsBuilder.buildUriRedirectByEntityId(pathParameterPrefix, employee.getEmpId()))
                .body(employeesDataService.findById(employee.getEmpId()));
    }

    @Operation(summary = "create employee if user exist")
    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping("/employees/{userId}")
    public ResponseEntity<EmployeesDtoResponse> createEmployeeIfUserExist(@RequestBody EmployeesDtoShortRequest employeesDtoShortRequest,
                                                                          @PathVariable UUID userId) {
        employeesDataService.createEmpIfUserExist(userId, employeesDtoShortRequest);
        EmployeesDtoResponse employee = employeesDataService.findEmployeesByFullName(employeesDtoShortRequest.getEmpSurname(),
                employeesDtoShortRequest.getEmpName(),
                employeesDtoShortRequest.getEmpMidname());
        return ResponseEntity
                .created(customUriComponentsBuilder.buildUriRedirectByEntityId(pathParameterPrefix, employee.getEmpId()))
                .body(employeesDataService.findById(employee.getEmpId()));
    }

    @Operation(summary = "update employee", description = " !hint : if we want to change user's record fields thru employee's record than we need to provide \"userId\" in json requestbody")
    @PreAuthorize("hasAuthority('user:modify')")
    @PutMapping("/employees/{id}")
    public ResponseEntity<Boolean> updateEmployee(@RequestBody EmployeesDtoRequest employeesDtoRequest, @PathVariable UUID id) {
        return ResponseEntity.ok(employeesDataService.update(id, employeesDtoRequest));
    }

    @Operation(summary = "update employee change user")
    @PreAuthorize("hasAuthority('user:modify')")
    @PutMapping("/employees/change-user")
    public ResponseEntity<Boolean> updateEmployeeChangeUser(@RequestParam(name = "empId") UUID employeeId,
                                                            @RequestParam(name = "uId") UUID userId) {
        return ResponseEntity.ok(employeesDataService.updateUser(employeeId, userId));
    }

    @Operation(summary = "delete employee by Id")
    @PreAuthorize("hasAuthority('user:delete')")
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable UUID id) {
        return ResponseEntity.ok(employeesDataService.deleteById(id));
    }
}

