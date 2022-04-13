package com.example.taskboard.model.service.dataservice.users;

import com.example.taskboard.entity.users.Users;
import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.entity.users.dto.UsersDtoResponse;
import com.example.taskboard.entity.users.mapper.UsersMapper;
import com.example.taskboard.model.dataexeptions.DataNotFoundException;
import com.example.taskboard.model.dataexeptions.ElementNotFoundException;
import com.example.taskboard.model.dataexeptions.PasswordNotMatchRequirementsException;
import com.example.taskboard.model.dataexeptions.UserNotCreatedException;
import com.example.taskboard.model.dataexeptions.UserNotFoundException;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.dtoPageBuilder.DtoPageBuilder;
import com.example.taskboard.model.service.premapCheck.UsersParameterCheck;
import com.example.taskboard.repo.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UsersDataService implements IUsersDataService {
    private final UsersRepository usersRepository;
    private final UsersParameterCheck usersParameterCheck;
    private final UsersMapper usersMapper;

    public UsersDataService(UsersRepository usersRepository,
                            UsersParameterCheck usersParameterCheck,
                            UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersParameterCheck = usersParameterCheck;
        this.usersMapper = usersMapper;
    }

    @Override
    public List<UsersDtoResponse> findAll() {
        List<Users> usersList = usersRepository.findAll();
        if (usersList.size() == 0) throw new DataNotFoundException();
        return usersMapper.usersListToUsersDtoResponseList(usersList);
    }

    @Override
    public DtoPage<UsersDtoResponse> findAllPageable(Pageable pageable) {
        Page<Users> usersPage = usersRepository.findAll(pageable);
        if (usersPage.getContent().size() == 0) throw new DataNotFoundException();
        List<UsersDtoResponse> usersDtoResponseList = usersMapper.usersListToUsersDtoResponseList(usersPage.getContent());
        return new DtoPageBuilder<UsersDtoResponse>()
                .setContent(usersDtoResponseList)
                .setTotalPages(usersPage.getTotalPages())
                .setTotalElements(usersPage.getTotalElements())
                .build();
    }

    @Override
    public UsersDtoResponse findById(Long id) {
        Optional<Users> user = usersRepository.findById(id);
        return usersMapper.usersToUsersDtoResponse(user.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (!usersRepository.existsById(id)) throw new ElementNotFoundException(id);
            usersRepository.deleteById(id);
            return true;
        }

    @Override
    public UsersDtoResponse findUsersByUserLogin(String login) {
        Optional<Users> user = usersRepository.findUsersByUserLogin(login);
        return usersMapper.usersToUsersDtoResponse(user.orElseThrow(() -> new UserNotFoundException(login)));
    }

    @Override
    public UsersDtoResponse create(UsersDtoRequest usersDtoRequest) {
        passwordCheck(usersDtoRequest);

        Users user = new Users(usersDtoRequest.getUserRole(),
                usersDtoRequest.getUserLogin(),
                getHashSalt(usersDtoRequest).get("hash"),
                getHashSalt(usersDtoRequest).get("salt"));

        usersRepository.save(user);

        if (findUsersByUserLogin(user.getUserLogin()).toString().isBlank())
            throw new UserNotCreatedException(usersDtoRequest.getUserLogin());
        return findById(user.getUserId());
    }

    private boolean passwordCheck(UsersDtoRequest usersDtoRequest){

        if (usersDtoRequest.getUserPasswd() == null) {
            throw new PasswordNotMatchRequirementsException();
        }

        if (!usersParameterCheck.userPreCreatePasswordCheck(usersDtoRequest)) {
            throw new PasswordNotMatchRequirementsException();
        }

        return true;
    }

    private Map<String, String> getHashSalt(UsersDtoRequest usersDtoRequest){
        return usersParameterCheck.userPreCreateGetPasswd(usersDtoRequest);
    }

    @Override
    public Boolean update(Long id, UsersDtoRequest usersDtoRequest) {
        Users user = findByIdNoConvert(id);

        if (usersDtoRequest.getUserLogin() != null)
            user.setUserLogin(usersDtoRequest.getUserLogin());

        if (usersDtoRequest.getUserRole() != null)
            user.setUserRole(usersDtoRequest.getUserRole());

        if (usersDtoRequest.getUserPasswd() != null && usersDtoRequest.getUserPasswd2() != null) {
            if (passwordCheck(usersDtoRequest)) {
                user.setUserPasswd(getHashSalt(usersDtoRequest).get("hash"));
                user.setUserMark(getHashSalt(usersDtoRequest).get("salt"));
            }
        }
        usersRepository.save(user);
        return true;
    }

    @Override
    public Users findByIdNoConvert(Long id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.orElseThrow(() -> new ElementNotFoundException(id));
    }
}
