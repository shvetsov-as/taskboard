package com.example.taskboard.model.service.dataservice.users;

import com.example.taskboard.entity.users.Users;
import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.entity.users.dto.UsersDtoResponse;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsersDataService {
    List<UsersDtoResponse> findAll();
    DtoPage<UsersDtoResponse> findAllPageable(Pageable pageable);
    UsersDtoResponse findById(Long id);
    Users findByIdNoConvert(Long id);
    UsersDtoResponse findUsersByUserLogin(String login);
    Boolean deleteById(Long id);
    UsersDtoResponse create(UsersDtoRequest usersDtoRequest);
    Boolean update(Long id, UsersDtoRequest usersDtoRequest);
}
