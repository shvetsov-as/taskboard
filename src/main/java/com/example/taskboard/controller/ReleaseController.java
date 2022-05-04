package com.example.taskboard.controller;

import com.example.taskboard.entity.release.dto.ReleaseDtoRequest;
import com.example.taskboard.entity.release.dto.ReleaseDtoResponse;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.service.dataservice.release.IReleaseDataService;
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

    @Operation(summary = "find all releases")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/release")
    public ResponseEntity<List<ReleaseDtoResponse>> findAll() {
        return ResponseEntity.ok(releaseDataService.findAll());
    }

    @Operation(summary = "find all releases with pagination")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/release/param")
    public ResponseEntity<DtoPage<ReleaseDtoResponse>> findAllPageable(@RequestParam(name = "page") Integer page,
                                                                       @RequestParam(name = "size") Integer size) {
        return ResponseEntity.ok(releaseDataService.findAllPageable(PageRequest.of(page, size)));
    }

    @Operation(summary = "find release by id")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/release/{id}")
    public ResponseEntity<ReleaseDtoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(releaseDataService.findById(id));
    }

    @Operation(summary = "create new release")
    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping("/release")
    public ResponseEntity<ReleaseDtoResponse> createRelease(@RequestBody ReleaseDtoRequest releaseDtoRequest) {
        ReleaseDtoResponse release = releaseDataService.create(releaseDtoRequest);
        return ResponseEntity
                .created(customUriComponentsBuilder.buildUriRedirectByEntityId(pathParameterPrefix, release.getRelId()))
                .body(releaseDataService.findById(release.getRelId()));
    }

    @Operation(summary = "update release by id")
    @PreAuthorize("hasAuthority('user:modify')")
    @PutMapping("/release/{id}")
    public ResponseEntity<Boolean> updateRelease(@RequestBody ReleaseDtoRequest releaseDtoRequest, @PathVariable UUID id) {
        return ResponseEntity.ok(releaseDataService.update(id, releaseDtoRequest));
    }

    @Operation(summary = "delete release by id")
    @PreAuthorize("hasAuthority('user:delete')")
    @DeleteMapping("/release/{id}")
    public ResponseEntity<Boolean> deleteReleaseById(@PathVariable UUID id) {
        return ResponseEntity.ok(releaseDataService.deleteById(id));
    }
}
