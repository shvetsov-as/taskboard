package com.example.taskboard.entity.taskboard.dto;

import com.example.taskboard.entity.classifier.Status;
import com.sun.istack.NotNull;

import java.util.Date;
import java.util.UUID;

public class TaskboardDtoShortRequest {

    private UUID taskboardId;
    @NotNull
    private String taskboardName;
    @NotNull
    private String projectName;
    @NotNull
    private Date projectStartDate;
    @NotNull
    private Status projectStatus;

    public TaskboardDtoShortRequest() {
    }

    public TaskboardDtoShortRequest(String taskboardName, String projectName,
                                    Date projectStartDate, Status projectStatus) {
        this.taskboardName = taskboardName;
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectStatus = projectStatus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskboardDtoShortRequest)) return false;

        TaskboardDtoShortRequest that = (TaskboardDtoShortRequest) o;

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
