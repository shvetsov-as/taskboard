package com.example.taskboard.controller;


import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.entity.users.dto.UsersDtoResponse;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.service.dataservice.users.IUsersDataService;
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

    @GetMapping("/users")
    public ResponseEntity<List<UsersDtoResponse>> findAll() {
        return ResponseEntity.ok(usersDataService.findAll());
    }

    @GetMapping("/users/page={page}&size={size}")
    public ResponseEntity<DtoPage<UsersDtoResponse>> findAllPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(usersDataService.findAllPageable(PageRequest.of(page, size)));
    }

    @GetMapping("/users/login={login}")
    public ResponseEntity<UsersDtoResponse> findUserByLogin(@PathVariable String login) {
        return ResponseEntity.ok(usersDataService.findUsersByUserLogin(login));
    }

    @GetMapping("/users/id={id}")
    public ResponseEntity<UsersDtoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usersDataService.findById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<UsersDtoResponse> createUser(@RequestBody UsersDtoRequest usersDtoRequest) {
        usersDataService.create(usersDtoRequest);
        UsersDtoResponse user = usersDataService.findUsersByUserLogin(usersDtoRequest.getUserLogin());

        return ResponseEntity
                .created(customUriComponentsBuilder.buildUriRedirectByEntityId(pathParameterPrefix, user.getUserId()))
                .body(usersDataService.findById(user.getUserId()));
    }

    @PutMapping("/users/id={id}")
    public ResponseEntity<Boolean> updateUser(@RequestBody UsersDtoRequest usersDtoRequest, @PathVariable Long id) {
        return ResponseEntity.ok(usersDataService.update(id, usersDtoRequest));

    }

    @DeleteMapping("/users/id={id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.ok(usersDataService.deleteById(id));
    }

}
