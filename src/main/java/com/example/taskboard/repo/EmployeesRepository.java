package com.example.taskboard.repo;

import com.example.taskboard.entity.employees.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    Optional<Employees> findEmployeesByEmpSurnameAndEmpNameAndEmpMidname(String empSurname, String empName, String empMidname);
    Employees findEmployeesByUser_UserId(Long userId);
    Page<Employees> findAll (Pageable pageable);
}
