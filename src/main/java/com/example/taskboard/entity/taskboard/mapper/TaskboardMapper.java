package com.example.taskboard.entity.taskboard.mapper;

import com.example.taskboard.entity.taskboard.Taskboard;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoRequest;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskboardMapper {
    Taskboard taskboardDtoRequestToTaskboard(TaskboardDtoRequest taskboardDtoRequest);

    TaskboardDtoResponse taskboardToTaskboardDtoResponse(Taskboard taskboard);

    List<TaskboardDtoResponse> taskboardListToTaskboardDtoResponseList(List<Taskboard> taskboard);
}






