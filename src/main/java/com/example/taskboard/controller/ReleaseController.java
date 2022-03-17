package com.example.taskboard.controller;

import com.example.taskboard.entity.release.dto.ReleaseDtoRequest;
import com.example.taskboard.entity.release.dto.ReleaseDtoResponse;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.service.dataservice.release.IReleaseDataService;
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
@Tag(name = "Release controller")
@RestController
@RequestMapping("/api/v1")
public class ReleaseController {

    private final IReleaseDataService releaseDataService;
    private final CustomUriComponentsBuilder customUriComponentsBuilder;
    private final String pathParameterPrefix = "/release/id=";

    public ReleaseController(IReleaseDataService releaseDataService, CustomUriComponentsBuilder customUriComponentsBuilder) {
        this.releaseDataService = releaseDataService;
        this.customUriComponentsBuilder = customUriComponentsBuilder;
    }

    @GetMapping("/release")
    public ResponseEntity<List<ReleaseDtoResponse>> findAll() {
        return ResponseEntity.ok(releaseDataService.findAll());
    }

    @GetMapping("/release/page={page}&size={size}")
    public ResponseEntity<DtoPage<ReleaseDtoResponse>> findAllPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(releaseDataService.findAllPageable(PageRequest.of(page, size)));
    }

    @GetMapping("/release/id={id}")
    public ResponseEntity<ReleaseDtoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(releaseDataService.findById(id));
    }

    @PostMapping("/release")
    public ResponseEntity<ReleaseDtoResponse> createRelease(@RequestBody ReleaseDtoRequest releaseDtoRequest) {
        ReleaseDtoResponse release = releaseDataService.create(releaseDtoRequest);
        return ResponseEntity
                .created(customUriComponentsBuilder.buildUriRedirectByEntityId(pathParameterPrefix, release.getRelId()))
                .body(releaseDataService.findById(release.getRelId()));
    }

    @PutMapping("/release/id={id}")
    public ResponseEntity<Boolean> updateRelease(@RequestBody ReleaseDtoRequest releaseDtoRequest, @PathVariable Long id) {
        return ResponseEntity.ok(releaseDataService.update(id, releaseDtoRequest));
    }

    @DeleteMapping("/release/id={id}")
    public ResponseEntity<Boolean> deleteReleaseById(@PathVariable Long id) {
        return ResponseEntity.ok(releaseDataService.deleteById(id));
    }






}
