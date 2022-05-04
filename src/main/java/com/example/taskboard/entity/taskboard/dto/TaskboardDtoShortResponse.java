package com.example.taskboard.entity.taskboard.dto;

import com.example.taskboard.entity.classifier.Status;

import java.util.Date;
import java.util.UUID;

public class TaskboardDtoShortResponse {

    private UUID taskboardId;
    private String taskboardName;
    private String projectName;
    private Date projectStartDate;
    private Status projectStatus;

    public TaskboardDtoShortResponse() {
    }

    public TaskboardDtoShortResponse(UUID taskboardId, String taskboardName, String projectName, Date projectStartDate,
                                     Status projectStatus) {
        this.taskboardId = taskboardId;
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
        if (!(o instanceof TaskboardDtoShortResponse)) return false;

        TaskboardDtoShortResponse that = (TaskboardDtoShortResponse) o;

        if (!getTaskboardId().equals(that.getTaskboardId())) return false;
        if (!getTaskboardName().equals(that.getTaskboardName())) return false;
        return getProjectName().equals(that.getProjectName());
    }

    @Override
    public int hashCode() {
        int result = getTaskboardId().hashCode();
        result = 31 * result + getTaskboardName().hashCode();
        result = 31 * result + getProjectName().hashCode();
        return result;
    }
}
