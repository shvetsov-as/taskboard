package com.example.taskboard.controller;

import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortRequest;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortResponse;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.service.dataservice.taskboard.ITaskboardDataService;
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
@Tag(name = "Taskboard controller")
@RestController
@RequestMapping("/api/v1")
public class TaskboardController {

    private final ITaskboardDataService taskboardDataService;
    private final CustomUriComponentsBuilder customUriComponentsBuilder;
    private final String pathParameterPrefix = "/taskboard/id=";

    public TaskboardController(ITaskboardDataService taskboardDataService, CustomUriComponentsBuilder customUriComponentsBuilder) {
        this.taskboardDataService = taskboardDataService;
        this.customUriComponentsBuilder = customUriComponentsBuilder;
    }

    @Operation(summary = "find all taskboards")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/taskboard")
    public ResponseEntity<List<TaskboardDtoShortResponse>> findAll(){
        return ResponseEntity.ok(taskboardDataService.findAll());
    }

    @Operation(summary = "find taskboard with pagination")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/taskboard/param")
    public ResponseEntity<DtoPage<TaskboardDtoShortResponse>> findAllPageable(@RequestParam(name = "page") Integer page,
                                                                              @RequestParam(name = "size") Integer size) {
        return ResponseEntity.ok(taskboardDataService.findAllPageable(PageRequest.of(page, size)));
    }

    @Operation(summary = "find taskboard by id")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/taskboard/{id}")
    public ResponseEntity<TaskboardDtoShortResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskboardDataService.findById(id));
    }

    @Operation(summary = "create taskboard")
    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping("/taskboard")
    public ResponseEntity<TaskboardDtoShortResponse> createTaskboard(@RequestBody TaskboardDtoShortRequest taskboardDtoShortRequest) {
        TaskboardDtoShortResponse taskboard = taskboardDataService.create(taskboardDtoShortRequest);
        return ResponseEntity
                .created(customUriComponentsBuilder.buildUriRedirectByEntityId(pathParameterPrefix, taskboard.getTaskboardId()))
                .body(taskboardDataService.findById(taskboard.getTaskboardId()));
    }

    @Operation(summary = "update taskboard by id")
    @PreAuthorize("hasAuthority('user:modify')")
    @PutMapping("/taskboard/{id}")
    public ResponseEntity<Boolean> updateTaskboard(@RequestBody TaskboardDtoShortRequest taskboardDtoShortRequest, @PathVariable UUID id) {
        return ResponseEntity.ok(taskboardDataService.update(id, taskboardDtoShortRequest));
    }

    @Operation(summary = "delete taskboard by id")
    @PreAuthorize("hasAuthority('user:delete')")
    @DeleteMapping("/taskboard/{id}")
    public ResponseEntity<Boolean> deleteTaskboardById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskboardDataService.deleteById(id));
    }
}
