package com.example.taskboard.model.security.passwordManager.passwdInputCheck;

import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.model.dataexeptions.PasswordNotMatchRequirementsException;
import com.example.taskboard.model.dataexeptions.PasswordsNotMatchException;
import org.springframework.stereotype.Service;

@Service
public class InputCheck {
    private final PasswdCheck passwdCheck = new PasswdCheck();

    public Boolean fullCheck(UsersDtoRequest usersDtoRequest){
        passwordsMatch(usersDtoRequest);
        passwordRequirements(usersDtoRequest);
        return true;
    }

    public Boolean passwordsMatch(UsersDtoRequest usersDtoRequest){
        if(!passwdCheck.passwdMatch(usersDtoRequest.getUserPasswd(),
                usersDtoRequest.getUserPasswd2())) throw new PasswordsNotMatchException();
        return true;
    }

    public Boolean passwordRequirements(UsersDtoRequest usersDtoRequest){
        if(!passwdCheck.passwdCheck(usersDtoRequest.getUserPasswd())) throw new PasswordNotMatchRequirementsException();
        return true;
    }
}
