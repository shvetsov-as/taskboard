package com.example.taskboard.model.service.dataservice.users;

import com.example.taskboard.entity.users.Users;
import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.entity.users.dto.UsersDtoResponse;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IUsersDataService {
    List<UsersDtoResponse> findAll();
    DtoPage<UsersDtoResponse> findAllPageable(Pageable pageable);
    UsersDtoResponse findById(UUID id);
    Users findByIdNoConvert(UUID id);
    UsersDtoResponse findUsersByUserLogin(String login);
    Boolean deleteById(UUID id);
    UsersDtoResponse create(UsersDtoRequest usersDtoRequest);
    Boolean update(UUID id, UsersDtoRequest usersDtoRequest);
}
