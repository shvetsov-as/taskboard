package com.example.taskboard.entity.tasks.mapper;

import com.example.taskboard.entity.tasks.Tasks;
import com.example.taskboard.entity.tasks.dto.TasksDtoRequest;
import com.example.taskboard.entity.tasks.dto.TasksDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TasksMapper {
    Tasks tasksDtoRequestToTasks(TasksDtoRequest tasksDtoRequest);

    TasksDtoResponse tasksToTasksDtoResponse(Tasks tasks);

    List<TasksDtoResponse> TasksListToTasksDtoResponseList(List<Tasks> tasks);
}
