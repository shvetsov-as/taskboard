package com.example.taskboard.repo;

import com.example.taskboard.entity.employees.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, UUID> {
    Optional<Employees> findEmployeesByEmpSurnameAndEmpNameAndEmpMidname(String empSurname, String empName, String empMidname);
    Employees findEmployeesByUser_UserId(UUID userId);
    Page<Employees> findAll (@NotNull Pageable pageable);
}
