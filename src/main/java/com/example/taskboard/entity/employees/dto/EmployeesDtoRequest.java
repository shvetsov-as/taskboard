package com.example.taskboard.entity.employees.dto;

import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.sun.istack.NotNull;

public class EmployeesDtoRequest {

    private Long empId;
    @NotNull
    private String empSurname;
    @NotNull
    private String empName;
    @NotNull
    private String empMidname;
    @NotNull
    private UsersDtoRequest user;

    public EmployeesDtoRequest() {
    }

    public EmployeesDtoRequest(String empSurname, String empName, String empMidname, UsersDtoRequest user) {
        this.empSurname = empSurname;
        this.empName = empName;
        this.empMidname = empMidname;
        this.user = user;
    }

    public EmployeesDtoRequest(Long empId, String empSurname, String empName, String empMidname, UsersDtoRequest user) {
        this.empId = empId;
        this.empSurname = empSurname;
        this.empName = empName;
        this.empMidname = empMidname;
        this.user = user;
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

    public UsersDtoRequest getUser() {
        return user;
    }

    public void setUser(UsersDtoRequest user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeesDtoRequest)) return false;

        EmployeesDtoRequest that = (EmployeesDtoRequest) o;

        if (getEmpSurname() != null ? !getEmpSurname().equals(that.getEmpSurname()) : that.getEmpSurname() != null)
            return false;
        if (getEmpName() != null ? !getEmpName().equals(that.getEmpName()) : that.getEmpName() != null) return false;
        if (getEmpMidname() != null ? !getEmpMidname().equals(that.getEmpMidname()) : that.getEmpMidname() != null)
            return false;
        return getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        int result = getEmpSurname() != null ? getEmpSurname().hashCode() : 0;
        result = 31 * result + (getEmpName() != null ? getEmpName().hashCode() : 0);
        result = 31 * result + (getEmpMidname() != null ? getEmpMidname().hashCode() : 0);
        result = 31 * result + getUser().hashCode();
        return result;
    }
}
