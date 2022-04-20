package com.example.taskboard.model.service.dataservice.taskboard;

import com.example.taskboard.entity.taskboard.Taskboard;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoRequest;
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
        if (taskboardList.size() == 0) throw new DataNotFoundException();
        return taskboardShortMapper.taskboardListToTaskboardDtoShortResponseList(taskboardList);
    }

    @Override
    public DtoPage<TaskboardDtoShortResponse> findAllPageable(Pageable pageable) {
        Page<Taskboard> taskboardPage = taskboardRepository.findAll(pageable);
        if (taskboardPage.getContent().size() == 0) throw new DataNotFoundException();
        List<TaskboardDtoShortResponse> taskboardDtoShortResponseList =
                taskboardShortMapper.taskboardListToTaskboardDtoShortResponseList(taskboardPage.getContent());

        return new DtoPageBuilder<TaskboardDtoShortResponse>()
                .setContent(taskboardDtoShortResponseList)
                .setTotalPages(taskboardPage.getTotalPages())
                .setTotalElements(taskboardPage.getTotalElements())
                .build();
    }

    @Override
    public TaskboardDtoShortResponse findById(Long id) {
        Optional<Taskboard> taskboard = taskboardRepository.findById(id);
        return taskboardShortMapper.taskboardToTaskboardDtoShortResponse(taskboard.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    public Taskboard findByIdNoConvert(Long id) {
        Optional<Taskboard> taskboard = taskboardRepository.findById(id);
        return taskboard.orElseThrow(() -> new ElementNotFoundException(id));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (!taskboardRepository.existsById(id)) throw new ElementNotFoundException(id);
            taskboardRepository.deleteById(id);
            return true;
    }

    @Override
    @Transactional
    public TaskboardDtoShortResponse create(TaskboardDtoRequest taskboardDtoRequest) {
        List<Taskboard> taskboardList = taskboardRepository.findAll();
        if (taskboardList.size() != 0) {
            throw new TaskboardAlreadyExistsException(taskboardList.get(0).getTaskboardId());
        }
        Taskboard taskboard = taskboardMapper.taskboardDtoRequestToTaskboard(taskboardDtoRequest);
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
