package com.example.taskboard.entity.employees;

import com.example.taskboard.entity.users.Users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "employees")
public class Employees {

//    @Id
//    @SequenceGenerator(
//            name = "employees_sequence",
//            sequenceName = "employees_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "employees_sequence"
//    )
//    @Column(name = "emp_id", nullable = false)
//    @NotNull
//    private Long empId;

    @Id
    @Column(name = "emp_id", nullable = false)
    @NotNull
    private UUID empId;

    @Column(name = "emp_surname", nullable = false)
    @NotNull
    private String empSurname;

    @Column(name = "emp_name", nullable = false)
    @NotNull
    private String empName;

    @Column(name = "emp_midname", nullable = false)
    @NotNull
    private String empMidname;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_users")
    private Users user;

    public Employees() {
    }

    public Employees(String empSurname, String empName, String empMidname, Users user) {
        this.empSurname = empSurname;
        this.empName = empName;
        this.empMidname = empMidname;
        this.user = user;
    }

    public Employees(String empSurname, String empName, String empMidname) {
        this.empSurname = empSurname;
        this.empName = empName;
        this.empMidname = empMidname;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employees)) return false;

        Employees employees = (Employees) o;

        if (!empId.equals(employees.empId)) return false;
        if (!empSurname.equals(employees.empSurname)) return false;
        if (!empName.equals(employees.empName)) return false;
        return empMidname.equals(employees.empMidname);
    }

    @Override
    public int hashCode() {
        int result = empId.hashCode();
        result = 31 * result + empSurname.hashCode();
        result = 31 * result + empName.hashCode();
        result = 31 * result + empMidname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "empId=" + empId +
                ", empSurname='" + empSurname + '\'' +
                ", empName='" + empName + '\'' +
                ", empMidname='" + empMidname + '\'' +
                '}';
    }
}
