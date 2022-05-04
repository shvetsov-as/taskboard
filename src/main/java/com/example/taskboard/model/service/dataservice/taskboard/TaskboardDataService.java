package com.example.taskboard.model.service.dataservice.taskboard;

import com.example.taskboard.entity.taskboard.Taskboard;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortRequest;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortResponse;
import com.example.taskboard.entity.taskboard.mapper.TaskboardMapper;
import com.example.taskboard.entity.taskboard.mapper.TaskboardShortMapper;
import com.example.taskboard.model.dataexeptions.DataNotFoundException;
import com.example.taskboard.model.dataexeptions.ElementNotFoundException;
import com.example.taskboard.model.dataexeptions.TaskboardAlreadyExistsException;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.dtoPageBuilder.DtoPageBuilder;
import com.example.taskboard.repo.TaskboardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskboardDataService implements ITaskboardDataService {

    private final TaskboardRepository taskboardRepository;
    private final TaskboardMapper taskboardMapper;
    private final TaskboardShortMapper taskboardShortMapper;

    public TaskboardDataService(TaskboardRepository taskboardRepository,
                                TaskboardShortMapper taskboardShortMapper,
                                TaskboardMapper taskboardMapper) {
        this.taskboardRepository = taskboardRepository;
        this.taskboardShortMapper = taskboardShortMapper;
        this.taskboardMapper = taskboardMapper;
    }

    public List<TaskboardDtoShortResponse> findAll() {
        List<Taskboard> taskboardList = taskboardRepository.findAll();
        if (taskboardList.isEmpty()) throw new DataNotFoundException();
        return taskboardShortMapper.taskboardListToTaskboardDtoShortResponseList(taskboardList);
    }

    @Override
    public DtoPage<TaskboardDtoShortResponse> findAllPageable(Pageable pageable) {
        Page<Taskboard> taskboardPage = taskboardRepository.findAll(pageable);
        if (taskboardPage.getContent().isEmpty()) throw new DataNotFoundException();
        List<TaskboardDtoShortResponse> taskboardDtoShortResponseList =
                taskboardShortMapper.taskboardListToTaskboardDtoShortResponseList(taskboardPage.getContent());

        return new DtoPageBuilder<TaskboardDtoShortResponse>()
                .setContent(taskboardDtoShortResponseList)
                .setTotalPages(taskboardPage.getTotalPages())
                .setTotalElements(taskboardPage.getTotalElements())
                .build();
    }

    @Override
    public TaskboardDtoShortResponse findById(UUID id) {
        Optional<Taskboard> taskboard = taskboardRepository.findById(id);
        return taskboardShortMapper.taskboardToTaskboardDtoShortResponse(taskboard.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    public Taskboard findByIdNoConvert(UUID id) {
        Optional<Taskboard> taskboard = taskboardRepository.findById(id);
        return taskboard.orElseThrow(() -> new ElementNotFoundException(id));
    }

    @Override
    @Transactional
    public Boolean deleteById(UUID id) {
        if (!taskboardRepository.existsById(id)) throw new ElementNotFoundException(id);
            taskboardRepository.deleteById(id);
            return true;
    }

    @Override
    @Transactional
    public TaskboardDtoShortResponse create(TaskboardDtoShortRequest taskboardDtoShortRequest) {
        List<Taskboard> taskboardList = taskboardRepository.findAll();
        if (!taskboardList.isEmpty()) throw new TaskboardAlreadyExistsException(taskboardList.get(0).getTaskboardId());

        Taskboard taskboard = taskboardShortMapper.taskboardDtoShortRequestToTaskboard(taskboardDtoShortRequest);

        taskboard.setTaskboardId(UUID.randomUUID());
        taskboard = taskboardRepository.save(taskboard);

        return findById(taskboard.getTaskboardId());
    }

    @Override
    @Transactional
    public Boolean update(UUID id, TaskboardDtoShortRequest taskboardDtoShortRequest) {
        Taskboard taskboard = findByIdNoConvert(id);

        if (taskboardDtoShortRequest.getTaskboardName() != null) {
            taskboard.setTaskboardName(taskboardDtoShortRequest.getTaskboardName());
        }

        if (taskboardDtoShortRequest.getProjectName() != null) {
            taskboard.setProjectName(taskboardDtoShortRequest.getProjectName());
        }

        if (taskboardDtoShortRequest.getProjectStartDate() != null) {
            taskboard.setProjectStartDate(taskboardDtoShortRequest.getProjectStartDate());
        }

        if (taskboardDtoShortRequest.getProjectStatus() != null) {
            taskboard.setProjectStatus(taskboardDtoShortRequest.getProjectStatus());
        }

        taskboardRepository.save(taskboard);
        return true;
    }
}
