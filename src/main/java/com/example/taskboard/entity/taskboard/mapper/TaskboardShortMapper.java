package com.example.taskboard.entity.taskboard.mapper;

import com.example.taskboard.entity.taskboard.Taskboard;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskboardShortMapper {

    TaskboardDtoShortResponse taskboardToTaskboardDtoShortResponse(Taskboard taskboard);

    List<TaskboardDtoShortResponse> taskboardListToTaskboardDtoShortResponseList(List<Taskboard> taskboard);
}






