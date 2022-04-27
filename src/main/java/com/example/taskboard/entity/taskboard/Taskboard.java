package com.example.taskboard.entity.taskboard;

import com.example.taskboard.entity.classifier.Status;
import com.example.taskboard.entity.release.Release;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "taskboard")
public class Taskboard {

    @Id
    @Column(name = "taskboard_id", nullable = false)
    @NotNull
    private UUID taskboardId;

    @Column(name = "taskboard_name", nullable = false, unique = true)
    @NotNull
    private String taskboardName;

    @Column(name = "project_name", nullable = false, unique = true)
    @NotNull
    private String projectName;

    @Column(name = "project_start_date", nullable = false)
    @NotNull
    private Date projectStartDate;

    @Column(name = "project_status", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status projectStatus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "taskboard_id_taskboard")
    private List<Release> listRelease = new ArrayList<>();

    public Taskboard() {
    }

    public Taskboard(String taskboardName, String projectName, Date projectStartDate, Status projectStatus) {
        this.taskboardName = taskboardName;
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectStatus = projectStatus;
    }

    public Taskboard(String taskboardName, String projectName, Date projectStartDate, Status projectStatus, List<Release> listRelease) {
        this.taskboardName = taskboardName;
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectStatus = projectStatus;
        this.listRelease = listRelease;
    }

    public UUID getTaskboardId() {
        return taskboardId;
    }

    public void setTaskboardId(UUID taskboardId) {
        this.taskboardId = taskboardId;
    }

    public String getTaskboardName() {
        return taskboardName;
    }

    public void setTaskboardName(String taskboardName) {
        this.taskboardName = taskboardName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Status getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Status projectStatus) {
        this.projectStatus = projectStatus;
    }

    public List<Release> getListRelease() {
        return listRelease;
    }

    public void setListRelease(List<Release> listRelease) {
        this.listRelease = listRelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Taskboard)) return false;

        Taskboard taskboard = (Taskboard) o;

        if (!taskboardId.equals(taskboard.taskboardId)) return false;
        return taskboardName.equals(taskboard.taskboardName);
    }

    @Override
    public int hashCode() {
        int result = taskboardId.hashCode();
        result = 31 * result + taskboardName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Taskboard{" +
                "taskboardId=" + taskboardId +
                ", taskboardName='" + taskboardName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectStartDate=" + projectStartDate +
                ", projectStatus=" + projectStatus +
                '}';
    }
}
