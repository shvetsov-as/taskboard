package com.example.taskboard.controller;


import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.entity.users.dto.UsersDtoResponse;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.service.dataservice.users.IUsersDataService;
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
@Tag(name = "User controller")
@RestController
@RequestMapping("/api/v1")
public class UsersController {

    private final IUsersDataService usersDataService;
    private final CustomUriComponentsBuilder customUriComponentsBuilder;
    private final String pathParameterPrefix = "/users/id=";

    public UsersController(IUsersDataService usersDataService, CustomUriComponentsBuilder customUriComponentsBuilder) {
        this.usersDataService = usersDataService;
        this.customUriComponentsBuilder = customUriComponentsBuilder;
    }

    @Operation(summary = "find all users")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/users")
    public ResponseEntity<List<UsersDtoResponse>> findAll() {
        return ResponseEntity.ok(usersDataService.findAll());
    }

    @Operation(summary = "find user with pagination")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/users/param")
    public ResponseEntity<DtoPage<UsersDtoResponse>> findAllPageable(@RequestParam(name = "page") Integer page,
                                                                     @RequestParam(name = "size") Integer size) {
        return ResponseEntity.ok(usersDataService.findAllPageable(PageRequest.of(page, size)));
    }

    @Operation(summary = "find user by login")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/users/find-by-login")
    public ResponseEntity<UsersDtoResponse> findUserByLogin(@RequestParam(name = "login") String login) {
        return ResponseEntity.ok(usersDataService.findUsersByUserLogin(login));
    }

    @Operation(summary = "find user by id")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/users/{id}")
    public ResponseEntity<UsersDtoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(usersDataService.findById(id));
    }

    @Operation(summary = "create user")
    //@PreAuthorize("hasAuthority('user:write')")
    @PostMapping("/users")
    public ResponseEntity<UsersDtoResponse> createUser(@RequestBody UsersDtoRequest usersDtoRequest) {
        usersDataService.create(usersDtoRequest);
        UsersDtoResponse user = usersDataService.findUsersByUserLogin(usersDtoRequest.getUserLogin());

        return ResponseEntity
                .created(customUriComponentsBuilder.buildUriRedirectByEntityId(pathParameterPrefix, user.getUserId()))
                .body(usersDataService.findById(user.getUserId()));
    }

    @Operation(summary = "update user by id")
    @PreAuthorize("hasAuthority('user:modify')")
    @PutMapping("/users/{id}")
    public ResponseEntity<Boolean> updateUser(@RequestBody UsersDtoRequest usersDtoRequest, @PathVariable UUID id) {
        return ResponseEntity.ok(usersDataService.update(id, usersDtoRequest));

    }

    @Operation(summary = "delete user by id")
    @PreAuthorize("hasAuthority('user:delete')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(usersDataService.deleteById(id));
    }

}
