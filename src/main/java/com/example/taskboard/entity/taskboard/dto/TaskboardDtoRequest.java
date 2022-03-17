package com.example.taskboard.entity.taskboard.dto;

import com.example.taskboard.entity.classifier.Status;
import com.example.taskboard.entity.release.dto.ReleaseDtoRequest;
import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskboardDtoRequest {

    private Long taskboardId;
    @NotNull
    private String taskboardName;
    @NotNull
    private String projectName;
    @NotNull
    private Date projectStartDate;
    @NotNull
    private Status projectStatus;
    private List<ReleaseDtoRequest> listRelease = new ArrayList<>();

    public TaskboardDtoRequest() {
    }

    public TaskboardDtoRequest(String taskboardName, String projectName,
                               Date projectStartDate, Status projectStatus) {
        this.taskboardName = taskboardName;
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectStatus = projectStatus;
    }

    public TaskboardDtoRequest(String taskboardName, String projectName,
                               Date projectStartDate, Status projectStatus,
                               @NotNull List<ReleaseDtoRequest> listRelease) {
        this.taskboardName = taskboardName;
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectStatus = projectStatus;
        this.listRelease = listRelease;
    }


    public Long getTaskboardId() {
        return taskboardId;
    }

    public void setTaskboardId(Long taskboardId) {
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

    public List<ReleaseDtoRequest> getListRelease() {
        return listRelease;
    }

    public void setListRelease(List<ReleaseDtoRequest> listRelease) {
        this.listRelease = listRelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskboardDtoRequest)) return false;

        TaskboardDtoRequest that = (TaskboardDtoRequest) o;

        if (!getTaskboardName().equals(that.getTaskboardName())) return false;
        if (!getProjectName().equals(that.getProjectName())) return false;
        if (!getProjectStartDate().equals(that.getProjectStartDate())) return false;
        return getProjectStatus() == that.getProjectStatus();
    }

    @Override
    public int hashCode() {
        int result = getTaskboardName().hashCode();
        result = 31 * result + getProjectName().hashCode();
        result = 31 * result + getProjectStartDate().hashCode();
        result = 31 * result + getProjectStatus().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TaskboardDtoRequest{" +
                "taskboardName='" + taskboardName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectStartDate=" + projectStartDate +
                ", projectStatus=" + projectStatus +
                '}';
    }
}
