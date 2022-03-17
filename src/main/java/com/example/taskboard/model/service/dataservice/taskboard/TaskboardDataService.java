package com.example.taskboard.model.service.dataservice.taskboard;

import com.example.taskboard.entity.taskboard.Taskboard;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoRequest;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortResponse;
import com.example.taskboard.model.dataexeptions.DataNotFoundException;
import com.example.taskboard.model.dataexeptions.ElementNotFoundException;
import com.example.taskboard.model.dataexeptions.TaskboardAlreadyExistsException;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.dtoPageBuilder.DtoPageBuilder;
import com.example.taskboard.model.service.converter.request.DtoTaskboardConverter;
import com.example.taskboard.model.service.converter.response.TaskboardConverter;
import com.example.taskboard.model.service.converter.response.TaskboardShortConverter;
import com.example.taskboard.repo.TaskboardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskboardDataService implements ITaskboardDataService {

    private final TaskboardRepository taskboardRepository;
    private final TaskboardConverter taskboardConverter;
    private final DtoTaskboardConverter dtoTaskboardConverter;
    private final TaskboardShortConverter taskboardShortConverter;

    public TaskboardDataService(TaskboardRepository taskboardRepository, TaskboardConverter taskboardConverter, DtoTaskboardConverter dtoTaskboardConverter, TaskboardShortConverter taskboardShortConverter) {
        this.taskboardRepository = taskboardRepository;
        this.taskboardConverter = taskboardConverter;
        this.dtoTaskboardConverter = dtoTaskboardConverter;
        this.taskboardShortConverter = taskboardShortConverter;
    }

    public List<TaskboardDtoShortResponse> findAll() {
        List<Taskboard> taskboardList = taskboardRepository.findAll();
        if (taskboardList.size() == 0) throw new DataNotFoundException();
        return taskboardShortConverter.convertToDto(taskboardList);
    }

    @Override
    public DtoPage<TaskboardDtoShortResponse> findAllPageable(Pageable pageable) {
        Page<Taskboard> taskboardPage = taskboardRepository.findAll(pageable);
        if (taskboardPage.getContent().size() == 0) throw new DataNotFoundException();
        List<TaskboardDtoShortResponse> taskboardDtoShortResponseList = taskboardShortConverter.convertToDto(taskboardPage.getContent());
        return new DtoPageBuilder<TaskboardDtoShortResponse>()
                .setContent(taskboardDtoShortResponseList)
                .setTotalPages(taskboardPage.getTotalPages())
                .setTotalElements(taskboardPage.getTotalElements())
                .build();
    }

    @Override
    public TaskboardDtoShortResponse findById(Long id) {
        Optional<Taskboard> taskboard = taskboardRepository.findById(id);
        return taskboardShortConverter.convertToDto(taskboard.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    public Taskboard findByIdNoConvert(Long id) {
        Optional<Taskboard> taskboard = taskboardRepository.findById(id);
        return taskboard.orElseThrow(() -> new ElementNotFoundException(id));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (taskboardRepository.existsById(id)) {
            taskboardRepository.deleteById(id);
            return true;
        } else throw new ElementNotFoundException(id);
    }

    @Override
    public TaskboardDtoShortResponse create(TaskboardDtoRequest taskboardDtoRequest) {
        List<Taskboard> taskboardList = taskboardRepository.findAll();
        if (taskboardList.size() != 0) {
            throw new TaskboardAlreadyExistsException(taskboardList.get(0).getTaskboardId());
        }
        Taskboard taskboard = dtoTaskboardConverter.convertToEntity(taskboardDtoRequest);
        taskboard = taskboardRepository.save(taskboard);
        return findById(taskboard.getTaskboardId());
    }

    @Override
    public Boolean update(Long id, TaskboardDtoRequest taskboardDtoRequest) {
        Taskboard taskboard = findByIdNoConvert(id);
        if (taskboardDtoRequest.getTaskboardName() != null) {
            taskboard.setTaskboardName(taskboardDtoRequest.getTaskboardName());
        }
        if (taskboardDtoRequest.getProjectName() != null) {
            taskboard.setProjectName(taskboardDtoRequest.getProjectName());
        }
        if (taskboardDtoRequest.getProjectStartDate() != null) {
            taskboard.setProjectStartDate(taskboardDtoRequest.getProjectStartDate());
        }
        if (taskboardDtoRequest.getProjectStatus() != null) {
            taskboard.setProjectStatus(taskboardDtoRequest.getProjectStatus());
        }
        taskboardRepository.save(taskboard);
        return true;
    }
}
