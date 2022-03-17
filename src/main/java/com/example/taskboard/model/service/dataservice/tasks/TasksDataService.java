package com.example.taskboard.model.service.dataservice.tasks;

import com.example.taskboard.entity.employees.Employees;
import com.example.taskboard.entity.taskboard.Taskboard;
import com.example.taskboard.entity.tasks.Tasks;
import com.example.taskboard.entity.tasks.dto.TasksDtoResponse;
import com.example.taskboard.entity.tasks.dto.TasksDtoShortRequest;
import com.example.taskboard.model.dataexeptions.DataNotFoundException;
import com.example.taskboard.model.dataexeptions.ElementNotFoundException;
import com.example.taskboard.model.dataexeptions.TaskNotCreatedException;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.dtoPageBuilder.DtoPageBuilder;
import com.example.taskboard.model.service.converter.request.DtoReleaseConverter;
import com.example.taskboard.model.service.converter.request.DtoTasksShortConverter;
import com.example.taskboard.model.service.converter.response.TasksConverter;
import com.example.taskboard.model.service.dataservice.employees.EmployeesDataService;
import com.example.taskboard.model.service.dataservice.taskboard.TaskboardDataService;
import com.example.taskboard.repo.TasksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksDataService implements ITasksDataService{
    private final TasksRepository tasksRepository;
    private final TasksConverter tasksConverter;
    private final DtoTasksShortConverter dtoTasksShortConverter;
    private final EmployeesDataService employeesDataService;
    private final TaskboardDataService taskboardDataService;
    private final DtoReleaseConverter dtoReleaseConverter;

    public TasksDataService(TasksRepository tasksRepository,
                            TasksConverter tasksConverter,
                            DtoTasksShortConverter dtoTasksShortConverter,
                            EmployeesDataService employeesDataService,
                            TaskboardDataService taskboardDataService, DtoReleaseConverter dtoReleaseConverter) {
        this.tasksRepository = tasksRepository;
        this.tasksConverter = tasksConverter;
        this.dtoTasksShortConverter = dtoTasksShortConverter;
        this.employeesDataService = employeesDataService;
        this.taskboardDataService = taskboardDataService;
        this.dtoReleaseConverter = dtoReleaseConverter;
    }

    public List<TasksDtoResponse> findAll() {
        List<Tasks> tasksList = tasksRepository.findAll();
        if (tasksList.size() == 0) throw new DataNotFoundException();
        return tasksConverter.convertToDto(tasksList);

    }

    @Override
    public DtoPage<TasksDtoResponse> findAllPageable(Pageable pageable) {
        Page<Tasks> tasksPage = tasksRepository.findAll(pageable);
        if (tasksPage.getContent().size() == 0) throw new DataNotFoundException();
        List<TasksDtoResponse> tasksDtoResponseList = tasksConverter.convertToDto(tasksPage.getContent());
        return new DtoPageBuilder<TasksDtoResponse>()
                .setContent(tasksDtoResponseList)
                .setTotalPages(tasksPage.getTotalPages())
                .setTotalElements(tasksPage.getTotalElements())
                .build();
    }

    @Override
    public TasksDtoResponse findById(Long id) {
        Optional<Tasks> task = tasksRepository.findById(id);
        return tasksConverter.convertToDto(task.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    public Tasks findByIdNoConvert(Long id) {
        Optional<Tasks> task = tasksRepository.findById(id);
        return task.orElseThrow(() -> new ElementNotFoundException(id));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (tasksRepository.existsById(id)){
            tasksRepository.deleteById(id);
            return true;
        }else throw new ElementNotFoundException(id);
    }

    @Override
    public TasksDtoResponse create(TasksDtoShortRequest tasksDtoShortRequest, Long taskboardId, Long empExecId, Long empAuthorId) {
        Tasks task = dtoTasksShortConverter.convertToEntity(tasksDtoShortRequest);
        Employees employeeExec = employeesDataService.findByIdNoConvert(empExecId);
        Employees employeeAuthor = employeesDataService.findByIdNoConvert(empAuthorId);
        Taskboard taskboard = taskboardDataService.findByIdNoConvert(taskboardId);

        task.setEmpIdExec(employeeExec);
        task.setEmpIdAuthor(employeeAuthor);
        task.setTaskboardId(taskboard);

        task = tasksRepository.save(task);
        if (findById(task.getTaskId()).toString().isBlank())
            throw new TaskNotCreatedException(tasksDtoShortRequest.getTaskName());
        return findById(task.getTaskId());
    }

    @Override
    public Boolean update(Long id, TasksDtoShortRequest tasksDtoShortRequest) {
        Tasks task = findByIdNoConvert(id);

        if(tasksDtoShortRequest.getTaskName() != null)
            task.setTaskName(tasksDtoShortRequest.getTaskName());

        if(tasksDtoShortRequest.getTaskToDo() != null)
            task.setTaskToDo(tasksDtoShortRequest.getTaskToDo());

        if(tasksDtoShortRequest.getTaskStatus() != null)
            task.setTaskStatus(tasksDtoShortRequest.getTaskStatus());

        if(tasksDtoShortRequest.getReleaseVersion() != null)
            task.setReleaseVersion(dtoReleaseConverter.convertToEntity(tasksDtoShortRequest.getReleaseVersion()));

        tasksRepository.save(task);
        return true;
    }

}
