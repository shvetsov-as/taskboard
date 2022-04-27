package com.example.taskboard.model.service.dataservice.tasks;

import com.example.taskboard.entity.employees.Employees;
import com.example.taskboard.entity.release.mapper.ReleaseMapper;
import com.example.taskboard.entity.taskboard.Taskboard;
import com.example.taskboard.entity.tasks.Tasks;
import com.example.taskboard.entity.tasks.dto.TasksDtoResponse;
import com.example.taskboard.entity.tasks.dto.TasksDtoShortRequest;
import com.example.taskboard.entity.tasks.mapper.TasksMapper;
import com.example.taskboard.entity.tasks.mapper.TasksShortMapper;
import com.example.taskboard.model.dataexeptions.DataNotFoundException;
import com.example.taskboard.model.dataexeptions.ElementNotFoundException;
import com.example.taskboard.model.dataexeptions.TaskNotCreatedException;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.dtoPageBuilder.DtoPageBuilder;
import com.example.taskboard.model.service.dataservice.employees.EmployeesDataService;
import com.example.taskboard.model.service.dataservice.taskboard.TaskboardDataService;
import com.example.taskboard.repo.TasksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TasksDataService implements ITasksDataService {
    private final TasksRepository tasksRepository;
    private final EmployeesDataService employeesDataService;
    private final TaskboardDataService taskboardDataService;
    private final TasksMapper tasksMapper;
    private final TasksShortMapper tasksShortMapper;
    private final ReleaseMapper releaseMapper;

    public TasksDataService(TasksRepository tasksRepository,
                            EmployeesDataService employeesDataService,
                            TaskboardDataService taskboardDataService,
                            TasksMapper tasksMapper,
                            TasksShortMapper tasksShortMapper,
                            ReleaseMapper releaseMapper) {
        this.tasksRepository = tasksRepository;
        this.employeesDataService = employeesDataService;
        this.taskboardDataService = taskboardDataService;
        this.tasksMapper = tasksMapper;
        this.tasksShortMapper = tasksShortMapper;
        this.releaseMapper = releaseMapper;
    }

    public List<TasksDtoResponse> findAll() {
        List<Tasks> tasksList = tasksRepository.findAll();
        if (tasksList.isEmpty()) throw new DataNotFoundException();
        return tasksMapper.TasksListToTasksDtoResponseList(tasksList);
    }

    @Override
    public DtoPage<TasksDtoResponse> findAllPageable(Pageable pageable) {
        Page<Tasks> tasksPage = tasksRepository.findAll(pageable);
        if (tasksPage.getContent().isEmpty()) throw new DataNotFoundException();
        List<TasksDtoResponse> tasksDtoResponseList = tasksMapper.TasksListToTasksDtoResponseList(tasksPage.getContent());
        return new DtoPageBuilder<TasksDtoResponse>()
                .setContent(tasksDtoResponseList)
                .setTotalPages(tasksPage.getTotalPages())
                .setTotalElements(tasksPage.getTotalElements())
                .build();
    }

    @Override
    public TasksDtoResponse findById(UUID id) {
        Optional<Tasks> task = tasksRepository.findById(id);
        return tasksMapper.tasksToTasksDtoResponse(task.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    public Tasks findByIdNoConvert(UUID id) {
        Optional<Tasks> task = tasksRepository.findById(id);
        return task.orElseThrow(() -> new ElementNotFoundException(id));
    }

    @Override
    @Transactional
    public Boolean deleteById(UUID id) {
        if (!tasksRepository.existsById(id)) throw new ElementNotFoundException(id);
            tasksRepository.deleteById(id);
            return true;
    }

    @Override
    @Transactional
    public TasksDtoResponse create(TasksDtoShortRequest tasksDtoShortRequest, UUID taskboardId, UUID empExecId, UUID empAuthorId) {

        Tasks task = tasksShortMapper.tasksDtoShortRequestToTasks(tasksDtoShortRequest);
        Employees employeeExec = employeesDataService.findByIdNoConvert(empExecId);
        Employees employeeAuthor = employeesDataService.findByIdNoConvert(empAuthorId);
        Taskboard taskboard = taskboardDataService.findByIdNoConvert(taskboardId);

        task.setTaskId(UUID.randomUUID());
        task.getReleaseVersion().setRelId(UUID.randomUUID());
        task.setEmpIdExec(employeeExec);
        task.setEmpIdAuthor(employeeAuthor);
        task.setTaskboardId(taskboard);

        task = tasksRepository.save(task);
        task = tasksRepository.findById(task.getTaskId()).orElseThrow(() -> new TaskNotCreatedException(tasksDtoShortRequest.getTaskName()));
        return tasksMapper.tasksToTasksDtoResponse(task);
    }

    @Override
    @Transactional
    public Boolean update(UUID id, TasksDtoShortRequest tasksDtoShortRequest) {
        Tasks task = findByIdNoConvert(id);

        if (tasksDtoShortRequest.getTaskName() != null){
            task.setTaskName(tasksDtoShortRequest.getTaskName());
        }

        if (tasksDtoShortRequest.getTaskToDo() != null){
            task.setTaskToDo(tasksDtoShortRequest.getTaskToDo());
        }

        if (tasksDtoShortRequest.getTaskStatus() != null){
            task.setTaskStatus(tasksDtoShortRequest.getTaskStatus());
        }

        if (tasksDtoShortRequest.getReleaseVersion() != null){
            task.setReleaseVersion(releaseMapper.releaseDtoRequestToRelease(tasksDtoShortRequest.getReleaseVersion()));
        }

        tasksRepository.save(task);
        return true;
    }

}
