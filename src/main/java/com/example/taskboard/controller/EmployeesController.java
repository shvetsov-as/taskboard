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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeesDtoResponse>> findAll() {
        return ResponseEntity.ok(employeesDataService.findAll());
    }

    @GetMapping("/employees/page={page}&size={size}")
    public ResponseEntity<DtoPage<EmployeesDtoResponse>> findAllPageable(@PathVariable Integer page,
                                                                         @PathVariable Integer size) {
        return ResponseEntity.ok(employeesDataService.findAllPageable(PageRequest.of(page, size)));
    }

    @GetMapping("/employees/surname={surname}&name={name}&middlename={midname}")
    public ResponseEntity<EmployeesDtoResponse> findByFullName(@PathVariable String surname,
                                                               @PathVariable String name,
                                                               @PathVariable String midname) {
        return ResponseEntity.ok(employeesDataService.findEmployeesByFullName(surname, name, midname));
    }

    @GetMapping("/employees/id={id}")
    public ResponseEntity<EmployeesDtoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(employeesDataService.findById(id));
    }

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

    @PostMapping("/employees/id={userId}")
    public ResponseEntity<EmployeesDtoResponse> createEmployeeIfUserExist(@RequestBody EmployeesDtoShortRequest employeesDtoShortRequest,
                                                                          @PathVariable Long userId) {
        employeesDataService.createEmpIfUserExist(userId, employeesDtoShortRequest);
        EmployeesDtoResponse employee = employeesDataService.findEmployeesByFullName(employeesDtoShortRequest.getEmpSurname(),
                employeesDtoShortRequest.getEmpName(),
                employeesDtoShortRequest.getEmpMidname());
        return ResponseEntity
                .created(customUriComponentsBuilder.buildUriRedirectByEntityId(pathParameterPrefix, employee.getEmpId()))
                .body(employeesDataService.findById(employee.getEmpId()));
    }


    @Operation(summary = "update employee", description = " !hint : if we want to change user's record fields thru employee's record than we need to provide \"userId\" in json requestbody")
    @PutMapping("/employees/id={id}")
    public ResponseEntity<Boolean> updateEmployee(@RequestBody EmployeesDtoRequest employeesDtoRequest, @PathVariable Long id) {
        return ResponseEntity.ok(employeesDataService.update(id, employeesDtoRequest));
    }

    @PutMapping("/employees/empid={employeeId}&uid={userId}")
    public ResponseEntity<Boolean> updateEmployeeChangeUser(@PathVariable Long employeeId, @PathVariable Long userId) {
        return ResponseEntity.ok(employeesDataService.updateUser(employeeId, userId));
    }

    @DeleteMapping("/employees/id={id}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeesDataService.deleteById(id));
    }
}

