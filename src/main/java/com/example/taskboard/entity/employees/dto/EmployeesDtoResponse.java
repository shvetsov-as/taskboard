package com.example.taskboard.entity.employees.dto;

import com.example.taskboard.entity.users.dto.UsersDtoResponse;

import java.util.UUID;

public class EmployeesDtoResponse {

    private UUID empId;
    private String empSurname;
    private String empName;
    private String empMidname;
    private UsersDtoResponse user;

    public EmployeesDtoResponse() {
    }

    public EmployeesDtoResponse(UUID empId, String empSurname, String empName, String empMidname) {
        this.empId = empId;
        this.empSurname = empSurname;
        this.empName = empName;
        this.empMidname = empMidname;
    }

    public EmployeesDtoResponse(UUID empId, String empSurname, String empName, String empMidname, UsersDtoResponse user) {
        this.empId = empId;
        this.empSurname = empSurname;
        this.empName = empName;
        this.empMidname = empMidname;
        this.user = user;
    }

    public UUID getEmpId() {
        return empId;
    }

    public void setEmpId(UUID empId) {
        this.empId = empId;
    }

    public String getEmpSurname() {
        return empSurname;
    }

    public void setEmpSurname(String empSurname) {
        this.empSurname = empSurname;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpMidname() {
        return empMidname;
    }

    public void setEmpMidname(String empMidname) {
        this.empMidname = empMidname;
    }

    public UsersDtoResponse getUser() {
        return user;
    }

    public void setUser(UsersDtoResponse user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeesDtoResponse)) return false;

        EmployeesDtoResponse that = (EmployeesDtoResponse) o;

        if (!getEmpId().equals(that.getEmpId())) return false;
        if (!getEmpSurname().equals(that.getEmpSurname())) return false;
        if (!getEmpName().equals(that.getEmpName())) return false;
        return getEmpMidname().equals(that.getEmpMidname());
    }

    @Override
    public int hashCode() {
        int result = getEmpId().hashCode();
        result = 31 * result + getEmpSurname().hashCode();
        result = 31 * result + getEmpName().hashCode();
        result = 31 * result + getEmpMidname().hashCode();
        return result;
    }
}
