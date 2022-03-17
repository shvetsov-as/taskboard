package com.example.taskboard.model.service.converter.request;

import com.example.taskboard.entity.taskboard.Taskboard;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoRequest;
import org.springframework.stereotype.Service;

@Service
public class DtoTaskboardConverter {
    public Taskboard convertToEntity (TaskboardDtoRequest taskboardDtoRequest){
        return new Taskboard(taskboardDtoRequest.getTaskboardName(),
                             taskboardDtoRequest.getProjectName(),
                             taskboardDtoRequest.getProjectStartDate(),
                             taskboardDtoRequest.getProjectStatus());
    }
}
