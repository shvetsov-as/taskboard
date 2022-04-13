package com.example.taskboard.entity.tasks;

import com.example.taskboard.entity.classifier.Status;
import com.example.taskboard.entity.employees.Employees;
import com.example.taskboard.entity.release.Release;
import com.example.taskboard.entity.taskboard.Taskboard;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @SequenceGenerator(
            name = "tasks_sequence",
            sequenceName = "tasks_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tasks_sequence"
    )
    @Column(name = "task_id", nullable = false)
    @NotNull
    private Long taskId;

    @Column(name = "task_name", nullable = false)
    @NotNull
    private String taskName;

    @Column(name = "task_todo", nullable = false)
    @NotNull
    private String taskToDo;

    @Column(name = "task_status", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "taskboard_id_taskboard", referencedColumnName = "taskboard_id")
    private Taskboard taskboardId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id_employees_exec", referencedColumnName = "emp_id")
    private Employees empIdExec;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id_employees_author", referencedColumnName = "emp_id")
    private Employees empIdAuthor;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "rel_id_release", referencedColumnName = "rel_id")
    private Release releaseVersion;

    public Tasks() {
    }

    public Tasks(String taskName, String taskToDo, Status taskStatus, Release releaseVersion) {
        this.taskName = taskName;
        this.taskToDo = taskToDo;
        this.taskStatus = taskStatus;
        this.releaseVersion = releaseVersion;
    }

    public Tasks(String taskName, String taskToDo, Status taskStatus, Taskboard taskboardId, Employees empIdExec, Employees empIdAuthor, Release releaseVersion) {
        this.taskName = taskName;
        this.taskToDo = taskToDo;
        this.taskStatus = taskStatus;
        this.taskboardId = taskboardId;
        this.empIdExec = empIdExec;
        this.empIdAuthor = empIdAuthor;
        this.releaseVersion = releaseVersion;
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskToDo() {
        return taskToDo;
    }

    public void setTaskToDo(String taskToDo) {
        this.taskToDo = taskToDo;
    }

    public Status getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Status taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Taskboard getTaskboardId() {
        return taskboardId;
    }

    public void setTaskboardId(Taskboard taskboardId) {
        this.taskboardId = taskboardId;
    }

    public Employees getEmpIdExec() {
        return empIdExec;
    }

    public void setEmpIdExec(Employees empIdExec) {
        this.empIdExec = empIdExec;
    }

    public Employees getEmpIdAuthor() {
        return empIdAuthor;
    }

    public void setEmpIdAuthor(Employees empIdAuthor) {
        this.empIdAuthor = empIdAuthor;
    }

    public Release getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(Release releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tasks)) return false;

        Tasks tasks = (Tasks) o;

        if (!getTaskId().equals(tasks.getTaskId())) return false;
        return getTaskName().equals(tasks.getTaskName());
    }

    @Override
    public int hashCode() {
        int result = getTaskId().hashCode();
        result = 31 * result + getTaskName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskToDo='" + taskToDo + '\'' +
                ", taskStatus=" + taskStatus +
                '}';
    }
}
