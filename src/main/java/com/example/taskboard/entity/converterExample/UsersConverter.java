package com.example.taskboard.entity.converterExample;

import com.example.taskboard.entity.users.Users;
import com.example.taskboard.entity.users.dto.UsersDtoResponse;

import java.util.List;
import java.util.stream.Collectors;

//@Service
public class UsersConverter {

    public UsersDtoResponse convertToDto (Users user){
        return new UsersDtoResponse(user.getUserId(), user.getUserRole(), user.getUserLogin(), user.getUserStatus());
    }

    public List<UsersDtoResponse> convertToDto (List<Users> users){
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
