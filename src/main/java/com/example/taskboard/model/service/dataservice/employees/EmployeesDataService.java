package com.example.taskboard.model.service.dataservice.employees;

import com.example.taskboard.entity.employees.Employees;
import com.example.taskboard.entity.employees.dto.EmployeesDtoRequest;
import com.example.taskboard.entity.employees.dto.EmployeesDtoResponse;
import com.example.taskboard.entity.employees.dto.EmployeesDtoShortRequest;
import com.example.taskboard.entity.employees.mapper.EmployeesMapper;
import com.example.taskboard.entity.employees.mapper.EmployeesShortMapper;
import com.example.taskboard.entity.users.Users;
import com.example.taskboard.model.dataexeptions.DataNotFoundException;
import com.example.taskboard.model.dataexeptions.ElementNotFoundException;
import com.example.taskboard.model.dataexeptions.EmployeeNotCreatedException;
import com.example.taskboard.model.dataexeptions.EmployeeNotFoundException;
import com.example.taskboard.model.dataexeptions.UserUsedByAnotherEmployeeException;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.dtoPageBuilder.DtoPageBuilder;
import com.example.taskboard.model.service.dataservice.users.UsersDataService;
import com.example.taskboard.repo.EmployeesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeesDataService implements IEmployeesDataService {
    private final EmployeesRepository employeesRepository;
    private final UsersDataService usersDataService;
    private final EmployeesMapper employeesMapper;
    private final EmployeesShortMapper employeesShortMapper;


    public EmployeesDataService(EmployeesRepository employeesRepository,
                                UsersDataService usersDataService,
                                EmployeesMapper employeesMapper,
                                EmployeesShortMapper employeesShortMapper) {
        this.employeesRepository = employeesRepository;
        this.usersDataService = usersDataService;
        this.employeesMapper = employeesMapper;
        this.employeesShortMapper = employeesShortMapper;
    }

    @Override
    public List<EmployeesDtoResponse> findAll() {
        List<Employees> employeesList = employeesRepository.findAll();
        if (employeesList.isEmpty()) throw new DataNotFoundException();
        return employeesMapper.employeesListToEmployeesDtoResponseList(employeesList);
    }

    @Override
    public DtoPage<EmployeesDtoResponse> findAllPageable(Pageable pageable) {
        Page<Employees> employeesPage = employeesRepository.findAll(pageable);
        if (employeesPage.getContent().isEmpty()) throw new DataNotFoundException();
        List<EmployeesDtoResponse> employeesDtoResponseList = employeesMapper.employeesListToEmployeesDtoResponseList(employeesPage.getContent());
        return new DtoPageBuilder<EmployeesDtoResponse>()
                .setContent(employeesDtoResponseList)
                .setTotalPages(employeesPage.getTotalPages())
                .setTotalElements(employeesPage.getTotalElements())
                .build();
    }

    @Override
    public EmployeesDtoResponse findById(UUID id) {
        Optional<Employees> employee = employeesRepository.findById(id);
        return employeesMapper.employeesToEmployeesDtoResponse(employee.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    public EmployeesDtoResponse findEmployeesByFullName(String empSurname, String empName, String empMidname) {
        Optional<Employees> employee = employeesRepository.findEmployeesByEmpSurnameAndEmpNameAndEmpMidname(empSurname, empName, empMidname);
        return employeesMapper.employeesToEmployeesDtoResponse(employee.orElseThrow(() -> new EmployeeNotFoundException(empSurname, empName, empMidname)));
    }

    @Override
    @Transactional
    public Boolean deleteById(UUID id) {
        if (!employeesRepository.existsById(id)) throw new ElementNotFoundException(id);
        employeesRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public EmployeesDtoResponse create(EmployeesDtoRequest employeesDtoRequest) {

        Employees employee = employeesMapper.employeesDtoRequestToEmployees(employeesDtoRequest);

        employee.setEmpId(UUID.randomUUID());
        employee = employeesRepository.save(employee);

        employee = employeesRepository.findById(employee.getEmpId()).orElseThrow(() -> new EmployeeNotCreatedException(
                    employeesDtoRequest.getEmpSurname(), employeesDtoRequest.getEmpName(), employeesDtoRequest.getEmpMidname()));

        return employeesMapper.employeesToEmployeesDtoResponse(employee);
    }

    @Override
    @Transactional
    public EmployeesDtoResponse createEmpIfUserExist(UUID userId, EmployeesDtoShortRequest employeesDtoShortRequest) {
        Employees employee = employeesShortMapper.employeesDtoShortRequestToEmployees(employeesDtoShortRequest);

        Users user = usersDataService.findByIdNoConvert(userId);
        employee.setUser(user);

        employee.setEmpId(UUID.randomUUID());
        employee = employeesRepository.save(employee);

        employee = employeesRepository.findById(
                employee.getEmpId()).orElseThrow(
                        () -> new EmployeeNotCreatedException(
                                employeesDtoShortRequest.getEmpSurname(),
                                employeesDtoShortRequest.getEmpName(),
                                employeesDtoShortRequest.getEmpMidname()));

        return employeesMapper.employeesToEmployeesDtoResponse(employee);
    }

    @Override
    @Transactional
    public Boolean update(UUID id, EmployeesDtoRequest employeesDtoRequest) {
        Employees employee = findByIdNoConvert(id);

        if (employeesDtoRequest.getEmpSurname() != null) {
            employee.setEmpSurname(employeesDtoRequest.getEmpSurname());
        }

        if (employeesDtoRequest.getEmpName() != null) {
            employee.setEmpName(employeesDtoRequest.getEmpName());
        }

        if (employeesDtoRequest.getEmpMidname() != null) {
            employee.setEmpMidname(employeesDtoRequest.getEmpMidname());
        }

        if (employeesDtoRequest.getUser() != null) {
            usersDataService.update(employeesDtoRequest.getUser().getUserId(), employeesDtoRequest.getUser());
        }

        employeesRepository.save(employee);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateUser(UUID empid, UUID userId) {

        Employees employee = employeesRepository.findEmployeesByUser_UserId(userId);
        if (employee != null) {
            throw new UserUsedByAnotherEmployeeException(userId, employee.getEmpId());
        } else {
            employee = findByIdNoConvert(empid);
            Users user = usersDataService.findByIdNoConvert(userId);
            employee.setUser(user);
            employeesRepository.save(employee);
            return true;
        }
    }

    @Override
    public Employees findByIdNoConvert(UUID id) {
        Optional<Employees> employee = employeesRepository.findById(id);
        return employee.orElseThrow(() -> new ElementNotFoundException(id));
    }
}
