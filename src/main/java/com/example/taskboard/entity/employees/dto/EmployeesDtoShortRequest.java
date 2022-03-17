package com.example.taskboard.entity.employees.dto;

import com.sun.istack.NotNull;

public class EmployeesDtoShortRequest {

    private Long empId;
    @NotNull
    private String empSurname;
    @NotNull
    private String empName;
    @NotNull
    private String empMidname;

    public EmployeesDtoShortRequest() {
    }

    public EmployeesDtoShortRequest(String empSurname, String empName, String empMidname) {
        this.empSurname = empSurname;
        this.empName = empName;
        this.empMidname = empMidname;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getEmpId() {
        return empId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeesDtoShortRequest)) return false;

        EmployeesDtoShortRequest that = (EmployeesDtoShortRequest) o;

        if (getEmpId() != null ? !getEmpId().equals(that.getEmpId()) : that.getEmpId() != null) return false;
        if (!getEmpSurname().equals(that.getEmpSurname())) return false;
        if (!getEmpName().equals(that.getEmpName())) return false;
        return getEmpMidname().equals(that.getEmpMidname());
    }

    @Override
    public int hashCode() {
        int result = getEmpId() != null ? getEmpId().hashCode() : 0;
        result = 31 * result + getEmpSurname().hashCode();
        result = 31 * result + getEmpName().hashCode();
        result = 31 * result + getEmpMidname().hashCode();
        return result;
    }
}
