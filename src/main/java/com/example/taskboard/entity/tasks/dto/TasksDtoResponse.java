package com.example.taskboard.entity.tasks.dto;

import com.example.taskboard.entity.classifier.Status;
import com.example.taskboard.entity.employees.dto.EmployeesDtoShortResponse;
import com.example.taskboard.entity.release.dto.ReleaseDtoResponse;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortResponse;

public class TasksDtoResponse {

    private Long taskId;
    private String taskName;
    private String taskToDo;
    private Status taskStatus;
    private TaskboardDtoShortResponse taskboardId;
    private EmployeesDtoShortResponse empIdExec;
    private EmployeesDtoShortResponse empIdAuthor;
    private ReleaseDtoResponse releaseVersion;

    public TasksDtoResponse() {
    }

    public TasksDtoResponse(Long taskId, String taskName, String taskToDo, Status taskStatus) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskToDo = taskToDo;
        this.taskStatus = taskStatus;
    }

    public TasksDtoResponse(Long taskId, String taskName, String taskToDo, Status taskStatus,
                            TaskboardDtoShortResponse taskboardId, EmployeesDtoShortResponse empIdExec,
                            EmployeesDtoShortResponse empIdAuthor, ReleaseDtoResponse releaseVersion) {
        this.taskId = taskId;
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

    public TaskboardDtoShortResponse getTaskboardId() {
        return taskboardId;
    }

    public void setTaskboardId(TaskboardDtoShortResponse taskboardId) {
        this.taskboardId = taskboardId;
    }

    public EmployeesDtoShortResponse getEmpIdExec() {
        return empIdExec;
    }

    public void setEmpIdExec(EmployeesDtoShortResponse empIdExec) {
        this.empIdExec = empIdExec;
    }

    public EmployeesDtoShortResponse getEmpIdAuthor() {
        return empIdAuthor;
    }

    public void setEmpIdAuthor(EmployeesDtoShortResponse empIdAuthor) {
        this.empIdAuthor = empIdAuthor;
    }

    public ReleaseDtoResponse getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(ReleaseDtoResponse releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TasksDtoResponse)) return false;

        TasksDtoResponse that = (TasksDtoResponse) o;

        if (!getTaskId().equals(that.getTaskId())) return false;
        if (!getTaskName().equals(that.getTaskName())) return false;
        return getTaskToDo().equals(that.getTaskToDo());
    }

    @Override
    public int hashCode() {
        int result = getTaskId().hashCode();
        result = 31 * result + getTaskName().hashCode();
        result = 31 * result + getTaskToDo().hashCode();
        return result;
    }
}
