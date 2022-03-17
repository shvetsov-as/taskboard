package com.example.taskboard.model.service.converter.response;

import com.example.taskboard.entity.taskboard.dto.TaskboardDtoResponse;
import com.example.taskboard.entity.taskboard.Taskboard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskboardConverter {

    private final ReleaseConverter releaseConverter;

    public TaskboardConverter(ReleaseConverter releaseConverter) {
        this.releaseConverter = releaseConverter;
    }

    public TaskboardDtoResponse convertToDto (Taskboard taskboard){

        return new TaskboardDtoResponse(taskboard.getTaskboardId(),
                taskboard.getTaskboardName(), taskboard.getProjectName(), taskboard.getProjectStartDate(),
                taskboard.getProjectStatus(), releaseConverter.convertToDto(taskboard.getListRelease()));
    }

    public List<TaskboardDtoResponse> convertToDto (List<Taskboard> taskboards){
        return taskboards.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public TaskboardDtoResponse convertToDtoWithoutRelease (Taskboard taskboard){

        return new TaskboardDtoResponse(taskboard.getTaskboardId(), taskboard.getTaskboardName(),
                taskboard.getProjectName(), taskboard.getProjectStartDate(), taskboard.getProjectStatus());
    }

    public List<TaskboardDtoResponse> convertToDtoWithoutRelease (List<Taskboard> taskboards){
        return taskboards.stream().map(this::convertToDtoWithoutRelease).collect(Collectors.toList());

    }
}
