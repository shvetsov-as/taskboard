package com.example.taskboard.entity.employees.dto;

public class EmployeesDtoShortResponse {

    private Long empId;
    private String empSurname;
    private String empName;
    private String empMidname;

    public EmployeesDtoShortResponse() {
    }

    public EmployeesDtoShortResponse(Long empId, String empSurname, String empName, String empMidname) {
        this.empId = empId;
        this.empSurname = empSurname;
        this.empName = empName;
        this.empMidname = empMidname;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeesDtoShortResponse)) return false;

        EmployeesDtoShortResponse that = (EmployeesDtoShortResponse) o;

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
