package com.example.taskboard.model.service.dataservice.employees;

import com.example.taskboard.entity.employees.Employees;
import com.example.taskboard.entity.employees.dto.EmployeesDtoRequest;
import com.example.taskboard.entity.employees.dto.EmployeesDtoResponse;
import com.example.taskboard.entity.employees.dto.EmployeesDtoShortRequest;
import com.example.taskboard.entity.users.Users;
import com.example.taskboard.model.dataexeptions.DataNotFoundException;
import com.example.taskboard.model.dataexeptions.ElementNotFoundException;
import com.example.taskboard.model.dataexeptions.EmployeeNotCreatedException;
import com.example.taskboard.model.dataexeptions.EmployeeNotFoundException;
import com.example.taskboard.model.dataexeptions.UserUsedByAnotherEmployeeException;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.dtoPageBuilder.DtoPageBuilder;
import com.example.taskboard.model.service.converter.request.DtoEmployeesConverter;
import com.example.taskboard.model.service.converter.request.DtoEmployeesShortConverter;
import com.example.taskboard.model.service.converter.request.DtoUsersConverter;
import com.example.taskboard.model.service.converter.response.EmployeesConverter;
import com.example.taskboard.model.service.dataservice.users.UsersDataService;
import com.example.taskboard.repo.EmployeesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesDataService implements IEmployeesDataService {
    private final EmployeesRepository employeesRepository;
    private final UsersDataService usersDataService;
    private final EmployeesConverter employeesConverter;
    private final DtoEmployeesConverter dtoEmployeesConverter;
    private final DtoEmployeesShortConverter dtoEmployeesShortConverter;
    private final DtoUsersConverter dtoUsersConverter;

    public EmployeesDataService(EmployeesRepository employeesRepository,
                                UsersDataService usersDataService,
                                EmployeesConverter employeesConverter,
                                DtoEmployeesConverter dtoEmployeesConverter,
                                DtoUsersConverter dtoUsersConverter,
                                DtoEmployeesShortConverter dtoEmployeesShortConverter) {
        this.employeesRepository = employeesRepository;
        this.employeesConverter = employeesConverter;
        this.dtoEmployeesConverter = dtoEmployeesConverter;
        this.dtoUsersConverter = dtoUsersConverter;
        this.dtoEmployeesShortConverter = dtoEmployeesShortConverter;
        this.usersDataService = usersDataService;
    }

    @Override
    public List<EmployeesDtoResponse> findAll() {
        List<Employees> employeesList = employeesRepository.findAll();
        if (employeesList.size() == 0) throw new DataNotFoundException();
        return employeesConverter.convertToDto(employeesList);
    }

    @Override
    public DtoPage<EmployeesDtoResponse> findAllPageable(Pageable pageable) {
        Page<Employees> employeesPage = employeesRepository.findAll(pageable);
        if (employeesPage.getContent().size() == 0) throw new DataNotFoundException();
        List<EmployeesDtoResponse> employeesDtoResponseList = employeesConverter.convertToDto(employeesPage.getContent());
        return new DtoPageBuilder<EmployeesDtoResponse>()
                .setContent(employeesDtoResponseList)
                .setTotalPages(employeesPage.getTotalPages())
                .setTotalElements(employeesPage.getTotalElements())
                .build();
    }

    @Override
    public EmployeesDtoResponse findById(Long id) {
        Optional<Employees> employee = employeesRepository.findById(id);
        return employeesConverter.convertToDto(employee.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    public EmployeesDtoResponse findEmployeesByFullName(String empSurname, String empName, String empMidname) {
        Optional<Employees> employee = employeesRepository.findEmployeesByEmpSurnameAndEmpNameAndEmpMidname(empSurname, empName, empMidname);
        return employeesConverter.convertToDto(employee.orElseThrow(() -> new EmployeeNotFoundException(empSurname, empName, empMidname)));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (employeesRepository.existsById(id)){
            employeesRepository.deleteById(id);
            return true;
        }else throw new ElementNotFoundException(id);
    }

    @Override
    public EmployeesDtoResponse create(EmployeesDtoRequest employeesDtoRequest) {
        Employees employee = dtoEmployeesConverter.convertToEntity(employeesDtoRequest);
        employee = employeesRepository.save(employee);
        if (findEmployeesByFullName(employee.getEmpSurname(), employee.getEmpName(), employee.getEmpMidname()).toString().isBlank())
            throw new EmployeeNotCreatedException(employeesDtoRequest.getEmpSurname(), employeesDtoRequest.getEmpName(), employeesDtoRequest.getEmpMidname());
        return findById(employee.getEmpId());
    }

    public EmployeesDtoResponse createEmpIfUserExist(Long userId, EmployeesDtoShortRequest employeesDtoShortRequest) {
        Employees employee = dtoEmployeesShortConverter.convertToEntity(employeesDtoShortRequest);
        Users user = usersDataService.findByIdNoConvert(userId);
        employee.setUser(user);
        employee = employeesRepository.save(employee);
        if (findEmployeesByFullName(employee.getEmpSurname(), employee.getEmpName(), employee.getEmpMidname()).toString().isBlank())
            throw new EmployeeNotCreatedException(employeesDtoShortRequest.getEmpSurname(),
                                                  employeesDtoShortRequest.getEmpName(),
                                                  employeesDtoShortRequest.getEmpMidname());
        return findById(employee.getEmpId());
    }

    @Override
    public Boolean update(Long id, EmployeesDtoRequest employeesDtoRequest) {
        Employees employee = findByIdNoConvert(id);

        if(employeesDtoRequest.getEmpSurname() != null)
            employee.setEmpSurname(employeesDtoRequest.getEmpSurname());

        if(employeesDtoRequest.getEmpName() != null)
            employee.setEmpName(employeesDtoRequest.getEmpName());

        if(employeesDtoRequest.getEmpMidname() != null)
            employee.setEmpMidname(employeesDtoRequest.getEmpMidname());

        if(employeesDtoRequest.getUser() != null)
            employee.setUser(dtoUsersConverter.convertToEntityCreate(employeesDtoRequest.getUser()));

        employeesRepository.save(employee);
        return true;
    }

    @Override
    public Boolean updateUser(Long empid, Long userId) {
        List<Employees> employeesList = employeesRepository.findAll();
        employeesList.stream().filter(e -> e.getUser().getUserId().equals(userId)).forEach(e -> {
            throw new UserUsedByAnotherEmployeeException(userId, e.getEmpId());
        });
        Employees employee = findByIdNoConvert(empid);
        Users user = usersDataService.findByIdNoConvert(userId);
        employee.setUser(user);
        employeesRepository.save(employee);
        return true;
    }

    @Override
    public Employees findByIdNoConvert(Long id) {
        Optional<Employees> employee = employeesRepository.findById(id);
        return employee.orElseThrow(() -> new ElementNotFoundException(id));
    }
}
