package com.example.taskboard.model.service.dataservice.tasks;

import com.example.taskboard.entity.tasks.Tasks;
import com.example.taskboard.entity.tasks.dto.TasksDtoResponse;
import com.example.taskboard.entity.tasks.dto.TasksDtoShortRequest;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ITasksDataService {
    List<TasksDtoResponse> findAll();
    DtoPage<TasksDtoResponse> findAllPageable(Pageable pageable);
    TasksDtoResponse findById(UUID id);
    Tasks findByIdNoConvert(UUID id);
    Boolean deleteById(UUID id);
    TasksDtoResponse create(TasksDtoShortRequest tasksDtoShortRequest, UUID taskboardId, UUID empExecId, UUID empAuthorId);
    Boolean update(UUID id, TasksDtoShortRequest tasksDtoShortRequest);
}
