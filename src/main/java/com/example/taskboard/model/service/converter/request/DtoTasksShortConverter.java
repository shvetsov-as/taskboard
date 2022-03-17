package com.example.taskboard.model.service.converter.request;

import com.example.taskboard.entity.release.Release;
import com.example.taskboard.entity.tasks.Tasks;
import com.example.taskboard.entity.tasks.dto.TasksDtoShortRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoTasksShortConverter {

    private final DtoReleaseConverter dtoReleaseConverter;

    public DtoTasksShortConverter(DtoReleaseConverter dtoReleaseConverter) {
        this.dtoReleaseConverter = dtoReleaseConverter;
    }

    public Tasks convertToEntity (TasksDtoShortRequest tasksDtoShorRequest){
        Release release = dtoReleaseConverter.convertToEntity(tasksDtoShorRequest.getReleaseVersion());
        return new Tasks(tasksDtoShorRequest.getTaskName(), tasksDtoShorRequest.getTaskToDo(), tasksDtoShorRequest.getTaskStatus(), release);
    }

    public List<Tasks> convertToEntity (List<TasksDtoShortRequest> tasksDtoShorRequest){
        return tasksDtoShorRequest.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
