package com.example.taskboard.entity.tasks.dto;

import com.example.taskboard.entity.classifier.Status;
import com.example.taskboard.entity.release.dto.ReleaseDtoRequest;
import com.sun.istack.NotNull;

public class TasksDtoShortRequest {

    private Long taskId;
    @NotNull
    private String taskName;
    @NotNull
    private String taskToDo;
    @NotNull
    private Status taskStatus;
    @NotNull
    private ReleaseDtoRequest releaseVersion;

    public TasksDtoShortRequest() {
    }

    public TasksDtoShortRequest(String taskName, String taskToDo, Status taskStatus, ReleaseDtoRequest releaseVersion) {
        this.taskName = taskName;
        this.taskToDo = taskToDo;
        this.taskStatus = taskStatus;
        this.releaseVersion = releaseVersion;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public ReleaseDtoRequest getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(ReleaseDtoRequest releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TasksDtoShortRequest)) return false;

        TasksDtoShortRequest that = (TasksDtoShortRequest) o;

        if (!getTaskName().equals(that.getTaskName())) return false;
        if (!getTaskToDo().equals(that.getTaskToDo())) return false;
        return getTaskStatus() == that.getTaskStatus();
    }

    @Override
    public int hashCode() {
        int result = getTaskName().hashCode();
        result = 31 * result + getTaskToDo().hashCode();
        result = 31 * result + getTaskStatus().hashCode();
        return result;
    }
}
