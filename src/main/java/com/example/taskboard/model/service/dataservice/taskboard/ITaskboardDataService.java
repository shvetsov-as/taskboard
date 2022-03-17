package com.example.taskboard.model.service.dataservice.taskboard;

import com.example.taskboard.entity.taskboard.Taskboard;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoRequest;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortResponse;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITaskboardDataService {
    List<TaskboardDtoShortResponse> findAll();
    DtoPage<TaskboardDtoShortResponse> findAllPageable(Pageable pageable);
    TaskboardDtoShortResponse findById(Long id);
    Taskboard findByIdNoConvert(Long id);
    Boolean deleteById(Long id);
    TaskboardDtoShortResponse create(TaskboardDtoRequest taskboardDtoRequest);
    Boolean update(Long id, TaskboardDtoRequest taskboardDtoRequest);
}
