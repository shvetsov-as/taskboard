package com.example.taskboard.controller;

import com.example.taskboard.entity.tasks.dto.TasksDtoResponse;
import com.example.taskboard.entity.tasks.dto.TasksDtoShortRequest;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.service.dataservice.tasks.ITasksDataService;
import com.example.taskboard.model.service.uriBuilder.CustomUriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @Operation(summary = "find all tasks")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/tasks")
    public ResponseEntity<List<TasksDtoResponse>> findAll(){
        return ResponseEntity.ok(tasksDataService.findAll());
    }

    @Operation(summary = "find all tasks with pagination")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/tasks/param")
    public ResponseEntity<DtoPage<TasksDtoResponse>> findAllPageable (@RequestParam(name = "page") Integer page,
                                                                      @RequestParam(name = "size") Integer size) {
        return ResponseEntity.ok(tasksDataService.findAllPageable(PageRequest.of(page, size)));
    }

    @Operation(summary = "find task by id")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TasksDtoResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(tasksDataService.findById(id));
    }

    @Operation(summary = "create new task")
    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping("/tasks/create")
    public ResponseEntity<TasksDtoResponse> createTask(@RequestBody TasksDtoShortRequest tasksDtoShortRequest,
                                                       @RequestParam(name = "boardId") UUID taskboardId,
                                                       @RequestParam(name = "execId") UUID empExecId,
                                                       @RequestParam(name = "authorId") UUID empAuthorId) {
        TasksDtoResponse task = tasksDataService.create(tasksDtoShortRequest,taskboardId,empExecId,empAuthorId);
        return ResponseEntity
                .created(customUriComponentsBuilder.buildUriRedirectByEntityId(pathParameterPrefix, task.getTaskId()))
                .body(tasksDataService.findById(task.getTaskId()));
    }

    @Operation(summary = "update task by id")
    @PreAuthorize("hasAuthority('user:modify')")
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Boolean> updateTask(@RequestBody TasksDtoShortRequest employeesDtoRequest, @PathVariable UUID id) {
        return ResponseEntity.ok(tasksDataService.update(id, employeesDtoRequest));
    }

    @Operation(summary = "delete task by id")
    @PreAuthorize("hasAuthority('user:delete')")
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Boolean> deleteTaskById(@PathVariable UUID id) {
        return ResponseEntity.ok(tasksDataService.deleteById(id));
    }

}
