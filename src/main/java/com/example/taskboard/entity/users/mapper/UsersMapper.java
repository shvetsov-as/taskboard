package com.example.taskboard.entity.users.mapper;

import com.example.taskboard.entity.users.Users;
import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.entity.users.dto.UsersDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    Users usersDtoRequestToUsers(UsersDtoRequest usersDtoRequest);

    UsersDtoResponse usersToUsersDtoResponse(Users user);

    List<UsersDtoResponse> usersListToUsersDtoResponseList(List<Users> users);
}
