package com.example.taskboard.model.service.dataservice.taskboard;

import com.example.taskboard.entity.taskboard.Taskboard;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortRequest;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortResponse;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ITaskboardDataService {
    List<TaskboardDtoShortResponse> findAll();
    DtoPage<TaskboardDtoShortResponse> findAllPageable(Pageable pageable);
    TaskboardDtoShortResponse findById(UUID id);
    Taskboard findByIdNoConvert(UUID id);
    Boolean deleteById(UUID id);
    TaskboardDtoShortResponse create(TaskboardDtoShortRequest taskboardDtoShortRequest);
    Boolean update(UUID id, TaskboardDtoShortRequest taskboardDtoShortRequest);
}
