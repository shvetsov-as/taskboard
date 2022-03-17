package com.example.taskboard.model.service.converter.request;

import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.model.dataexeptions.PasswordNotMatchRequirementsException;
import com.example.taskboard.model.dataexeptions.PasswordsNotMatchException;
import com.example.taskboard.model.service.passwordManager.HashCheck;
import com.example.taskboard.model.service.passwordManager.HashGen;
import com.example.taskboard.model.service.passwordManager.PasswdCheck;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class DtoUsersPreConverterCheck {
    private final HashCheck hashCheck;
    private final HashGen hashGen;
    private final PasswdCheck passwdCheck;

    public DtoUsersPreConverterCheck(HashCheck hashCheck, HashGen hashGen, PasswdCheck passwdCheck) {
        this.hashCheck = hashCheck;
        this.hashGen = hashGen;
        this.passwdCheck = passwdCheck;
    }

    public Boolean userPreCreatePasswordCheck(UsersDtoRequest usersDtoRequest){
        if(!passwdCheck.passwdMatch(usersDtoRequest.getUserPasswd(),
                usersDtoRequest.getUserPasswd2())) throw new PasswordsNotMatchException();
        if(!passwdCheck.passwdCheck(usersDtoRequest.getUserPasswd())) throw new PasswordNotMatchRequirementsException();
        return true;
    }
    public Map<String, String> userPreCreateGetPasswd (UsersDtoRequest usersDtoRequest){
        Map<String, String> salt_hashMap = new HashMap<>();

        try {
            salt_hashMap = hashGen.hashGen(usersDtoRequest.getUserPasswd());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return salt_hashMap;
    }
}
