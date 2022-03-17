package com.example.taskboard.entity.tasks.dto;

import com.example.taskboard.entity.classifier.Status;
import com.example.taskboard.entity.employees.dto.EmployeesDtoRequest;
import com.example.taskboard.entity.release.dto.ReleaseDtoRequest;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoRequest;
import com.sun.istack.NotNull;

public class TasksDtoRequest {

    private Long taskId;
    @NotNull
    private String taskName;
    @NotNull
    private String taskToDo;
    @NotNull
    private Status taskStatus;
    private TaskboardDtoRequest taskboardId;
    @NotNull
    private EmployeesDtoRequest empIdExec;
    @NotNull
    private EmployeesDtoRequest empIdAuthor;
    @NotNull
    private ReleaseDtoRequest releaseVersion;

    public TasksDtoRequest() {
    }

    public TasksDtoRequest(String taskName,
                           String taskToDo,
                           Status taskStatus,
                           EmployeesDtoRequest empIdExec,
                           EmployeesDtoRequest empIdAuthor,
                           ReleaseDtoRequest releaseVersion) {

        this.taskName = taskName;
        this.taskToDo = taskToDo;
        this.taskStatus = taskStatus;
        this.empIdExec = empIdExec;
        this.empIdAuthor = empIdAuthor;
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

    public TaskboardDtoRequest getTaskboardId() {
        return taskboardId;
    }

    public void setTaskboardId(TaskboardDtoRequest taskboardId) {
        this.taskboardId = taskboardId;
    }

    public EmployeesDtoRequest getEmpIdExec() {
        return empIdExec;
    }

    public void setEmpIdExec(EmployeesDtoRequest empIdExec) {
        this.empIdExec = empIdExec;
    }

    public EmployeesDtoRequest getEmpIdAuthor() {
        return empIdAuthor;
    }

    public void setEmpIdAuthor(EmployeesDtoRequest empIdAuthor) {
        this.empIdAuthor = empIdAuthor;
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
        if (!(o instanceof TasksDtoRequest)) return false;

        TasksDtoRequest that = (TasksDtoRequest) o;

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
