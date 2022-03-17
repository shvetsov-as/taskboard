package com.example.taskboard.controller;

import com.example.taskboard.entity.tasks.dto.TasksDtoResponse;
import com.example.taskboard.entity.tasks.dto.TasksDtoShortRequest;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.service.dataservice.tasks.ITasksDataService;
import com.example.taskboard.model.service.uriBuilder.CustomUriComponentsBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "API internal type")
@Tag(name = "API v.1")
@Tag(name = "Task controller")
@RestController
@RequestMapping("/api/v1")
public class TasksController {

    private final ITasksDataService tasksDataService;
    private final CustomUriComponentsBuilder customUriComponentsBuilder;
    private final String pathParameterPrefix = "/tasks/id=";

    public TasksController(ITasksDataService tasksDataService, CustomUriComponentsBuilder customUriComponentsBuilder) {
        this.tasksDataService = tasksDataService;
        this.customUriComponentsBuilder = customUriComponentsBuilder;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TasksDtoResponse>> findAll(){
        return ResponseEntity.ok(tasksDataService.findAll());
    }

    @GetMapping("/tasks/page={page}&size={size}")
    public ResponseEntity<DtoPage<TasksDtoResponse>> findAllPageable (@PathVariable Integer page,
                                                                      @PathVariable Integer size) {
        return ResponseEntity.ok(tasksDataService.findAllPageable(PageRequest.of(page, size)));
    }

    @GetMapping("/tasks/id={id}")
    public ResponseEntity<TasksDtoResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(tasksDataService.findById(id));
    }

    @PostMapping("/tasks/board={taskboardId}&exec={empExecId}&author={empAuthorId}")
    public ResponseEntity<TasksDtoResponse> createTask(@RequestBody TasksDtoShortRequest tasksDtoShortRequest,
                                                           @PathVariable Long taskboardId,
                                                           @PathVariable Long empExecId,
                                                           @PathVariable Long empAuthorId) {
        TasksDtoResponse task = tasksDataService.create(tasksDtoShortRequest,taskboardId,empExecId,empAuthorId);
        return ResponseEntity
                .created(customUriComponentsBuilder.buildUriRedirectByEntityId(pathParameterPrefix, task.getTaskId()))
                .body(tasksDataService.findById(task.getTaskId()));
    }

    @PutMapping("/tasks/id={id}")
    public ResponseEntity<Boolean> updateTask(@RequestBody TasksDtoShortRequest employeesDtoRequest, @PathVariable Long id) {
        return ResponseEntity.ok(tasksDataService.update(id, employeesDtoRequest));
    }

    @DeleteMapping("/tasks/id={id}")
    public ResponseEntity<Boolean> deleteTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(tasksDataService.deleteById(id));
    }

}
