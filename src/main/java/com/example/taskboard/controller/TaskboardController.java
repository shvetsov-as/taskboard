package com.example.taskboard.controller;

import com.example.taskboard.entity.taskboard.dto.TaskboardDtoRequest;
import com.example.taskboard.entity.taskboard.dto.TaskboardDtoShortResponse;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.service.dataservice.taskboard.ITaskboardDataService;
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

    @GetMapping("/taskboard")
    public ResponseEntity<List<TaskboardDtoShortResponse>> findAll(){
        return ResponseEntity.ok(taskboardDataService.findAll());
    }

    @GetMapping("/taskboard/page={page}&size={size}")
    public ResponseEntity<DtoPage<TaskboardDtoShortResponse>> findAllPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(taskboardDataService.findAllPageable(PageRequest.of(page, size)));
    }

    @GetMapping("/taskboard/id={id}")
    public ResponseEntity<TaskboardDtoShortResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(taskboardDataService.findById(id));
    }

    @PostMapping("/taskboard")
    public ResponseEntity<TaskboardDtoShortResponse> createTaskboard(@RequestBody TaskboardDtoRequest taskboardDtoRequest) {
        TaskboardDtoShortResponse taskboard = taskboardDataService.create(taskboardDtoRequest);
        return ResponseEntity
                .created(customUriComponentsBuilder.buildUriRedirectByEntityId(pathParameterPrefix, taskboard.getTaskboardId()))
                .body(taskboardDataService.findById(taskboard.getTaskboardId()));
    }

    @PutMapping("/taskboard/id={id}")
    public ResponseEntity<Boolean> updateTaskboard(@RequestBody TaskboardDtoRequest taskboardDtoRequest, @PathVariable Long id) {
        return ResponseEntity.ok(taskboardDataService.update(id, taskboardDtoRequest));
    }

    @DeleteMapping("/taskboard/id={id}")
    public ResponseEntity<Boolean> deleteTaskboardById(@PathVariable Long id) {
        return ResponseEntity.ok(taskboardDataService.deleteById(id));
    }
}
