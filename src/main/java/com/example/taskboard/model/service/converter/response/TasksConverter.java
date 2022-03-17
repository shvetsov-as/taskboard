package com.example.taskboard.model.service.converter.response;

import com.example.taskboard.entity.tasks.dto.TasksDtoResponse;
import com.example.taskboard.entity.tasks.Tasks;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasksConverter {

    private final TaskboardShortConverter taskboardShortConverter;
    private final EmployeesShortConverter employeesShortConverter;
    private final ReleaseConverter releaseConverter;

    public TasksConverter(TaskboardShortConverter taskboardShortConverter,
                          EmployeesShortConverter employeesShortConverter,
                          ReleaseConverter releaseConverter) {

        this.taskboardShortConverter = taskboardShortConverter;
        this.employeesShortConverter = employeesShortConverter;
        this.releaseConverter = releaseConverter;
    }

    public TasksDtoResponse convertToDto(Tasks task) {
        return new TasksDtoResponse(task.getTaskId(), task.getTaskName(),
                task.getTaskToDo(), task.getTaskStatus(), taskboardShortConverter.convertToDto(task.getTaskboardId()),
                employeesShortConverter.convertToDto(task.getEmpIdExec()),
                employeesShortConverter.convertToDto(task.getEmpIdAuthor()),
                releaseConverter.convertToDto(task.getReleaseVersion()));
    }

    public List<TasksDtoResponse> convertToDto(List<Tasks> tasks) {
        return tasks.stream().map(this::convertToDto).collect(Collectors.toList());
    }


}

