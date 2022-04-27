package com.example.taskboard.entity.taskboard.dto;

import com.example.taskboard.entity.classifier.Status;
import com.example.taskboard.entity.release.dto.ReleaseDtoResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskboardDtoResponse {

    private UUID taskboardId;
    private String taskboardName;
    private String projectName;
    private Date projectStartDate;
    private Status projectStatus;
    private List<ReleaseDtoResponse> listRelease = new ArrayList<>();

    public TaskboardDtoResponse() {
    }

    public TaskboardDtoResponse(UUID taskboardId, String taskboardName, String projectName, Date projectStartDate,
                                Status projectStatus) {
        this.taskboardId = taskboardId;
        this.taskboardName = taskboardName;
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectStatus = projectStatus;
    }

    public TaskboardDtoResponse(UUID taskboardId, String taskboardName, String projectName, Date projectStartDate,
                                Status projectStatus, List<ReleaseDtoResponse> listRelease) {
        this.taskboardId = taskboardId;
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

    public List<ReleaseDtoResponse> getListRelease() {
        return listRelease;
    }

    public void setListRelease(List<ReleaseDtoResponse> listRelease) {
        this.listRelease = listRelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskboardDtoResponse)) return false;

        TaskboardDtoResponse that = (TaskboardDtoResponse) o;

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
