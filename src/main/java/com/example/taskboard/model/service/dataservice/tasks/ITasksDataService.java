package com.example.taskboard.model.service.dataservice.tasks;

import com.example.taskboard.entity.tasks.Tasks;
import com.example.taskboard.entity.tasks.dto.TasksDtoResponse;
import com.example.taskboard.entity.tasks.dto.TasksDtoShortRequest;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITasksDataService {
    List<TasksDtoResponse> findAll();
    DtoPage<TasksDtoResponse> findAllPageable(Pageable pageable);
    TasksDtoResponse findById(Long id);
    Tasks findByIdNoConvert(Long id);
    Boolean deleteById(Long id);
    TasksDtoResponse create(TasksDtoShortRequest tasksDtoShortRequest, Long taskboardId, Long empExecId, Long empAuthorId);
    Boolean update(Long id, TasksDtoShortRequest tasksDtoShortRequest);
}
