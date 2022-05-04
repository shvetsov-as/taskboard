package com.example.taskboard.entity.users.mapper;

import com.example.taskboard.entity.classifier.UserStatus;
import com.example.taskboard.entity.users.Users;
import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(componentModel = "spring")
public abstract class UsersSecurityMapper {

    public UserDetails usersToUserDetails(Users user) {
        return new User(user.getUserLogin(),
                        user.getUserPasswd() + ":" + user.getUserMark(),
                        user.getUserStatus().equals(UserStatus.ACTIVE),
                        user.getUserStatus().equals(UserStatus.ACTIVE),
                        user.getUserStatus().equals(UserStatus.ACTIVE),
                        user.getUserStatus().equals(UserStatus.ACTIVE),
                        user.getUserRole().getAuthorities());
    }
}
