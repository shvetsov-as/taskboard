package com.example.taskboard.model.service.converter.response;

import com.example.taskboard.entity.taskboard.Taskboard;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskboardShortConverter {

    public TaskboardDtoShortResponse convertToDto (Taskboard taskboard){

        return new TaskboardDtoShortResponse(taskboard.getTaskboardId(),
                taskboard.getTaskboardName(), taskboard.getProjectName(), taskboard.getProjectStartDate(),
                taskboard.getProjectStatus());
    }

    public List<TaskboardDtoShortResponse> convertToDto (List<Taskboard> taskboards){
        return taskboards.stream().map(this::convertToDto).collect(Collectors.toList());

    }
}
