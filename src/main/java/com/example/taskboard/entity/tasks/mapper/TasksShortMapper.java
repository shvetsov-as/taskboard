package com.example.taskboard.entity.tasks.mapper;

import com.example.taskboard.entity.tasks.Tasks;
import com.example.taskboard.entity.tasks.dto.TasksDtoShortRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TasksShortMapper {
    Tasks tasksDtoShortRequestToTasks(TasksDtoShortRequest tasksDtoShortRequest);

}
