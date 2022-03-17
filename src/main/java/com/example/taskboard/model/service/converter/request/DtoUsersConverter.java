package com.example.taskboard.model.service.converter.request;

import com.example.taskboard.entity.users.Users;
import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.model.dataexeptions.PasswordNotMatchRequirementsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DtoUsersConverter {

    private final DtoUsersPreConverterCheck dtoUsersPreConverterCheck;

    public DtoUsersConverter(DtoUsersPreConverterCheck dtoUsersPreConverterCheck) {
        this.dtoUsersPreConverterCheck = dtoUsersPreConverterCheck;
    }

    public Users convertToEntityCreate(UsersDtoRequest usersDtoRequest){
        if(usersDtoRequest.getUserPasswd().isBlank())throw new PasswordNotMatchRequirementsException();
        if(!dtoUsersPreConverterCheck.userPreCreatePasswordCheck(usersDtoRequest)) throw new PasswordNotMatchRequirementsException();
        Map<String, String> salt_hashMap = dtoUsersPreConverterCheck.userPreCreateGetPasswd(usersDtoRequest);

        return new Users(usersDtoRequest.getUserRole(), usersDtoRequest.getUserLogin(), salt_hashMap.get("hash"), salt_hashMap.get("salt"));
    }

    public List<Users> convertToEntityCreate (List<UsersDtoRequest> usersDtoRequest){
        return usersDtoRequest.stream().map(this::convertToEntityCreate).collect(Collectors.toList());
    }
}
