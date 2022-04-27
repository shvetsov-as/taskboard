package com.example.taskboard.model.service.dataservice.users;

import com.example.taskboard.entity.users.Users;
import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.entity.users.dto.UsersDtoResponse;
import com.example.taskboard.entity.users.mapper.UsersMapper;
import com.example.taskboard.model.dataexeptions.DataNotFoundException;
import com.example.taskboard.model.dataexeptions.ElementNotFoundException;
import com.example.taskboard.model.dataexeptions.UserNotCreatedException;
import com.example.taskboard.model.dataexeptions.UserNotFoundException;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.dtoPageBuilder.DtoPageBuilder;
import com.example.taskboard.model.security.passwordManager.HashSaltCollector;
import com.example.taskboard.model.security.passwordManager.HashSaltParser;
import com.example.taskboard.model.security.passwordManager.passwdInputCheck.InputCheck;
import com.example.taskboard.repo.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UsersDataService implements IUsersDataService {
    private final UsersRepository usersRepository;
    private final InputCheck inputCheck;
    private final UsersMapper usersMapper;
    private final HashSaltCollector hashSaltCollector;
    private final HashSaltParser hashSaltParser;

    public UsersDataService(UsersRepository usersRepository,
                            InputCheck inputCheck,
                            UsersMapper usersMapper,
                            HashSaltCollector hashSaltCollector,
                            HashSaltParser hashSaltParser) {
        this.usersRepository = usersRepository;
        this.inputCheck = inputCheck;
        this.usersMapper = usersMapper;
        this.hashSaltCollector = hashSaltCollector;
        this.hashSaltParser = hashSaltParser;
    }

    @Override
    public List<UsersDtoResponse> findAll() {
        List<Users> usersList = usersRepository.findAll();
        if (usersList.isEmpty()) throw new DataNotFoundException();
        return usersMapper.usersListToUsersDtoResponseList(usersList);
    }

    @Override
    public DtoPage<UsersDtoResponse> findAllPageable(Pageable pageable) {
        Page<Users> usersPage = usersRepository.findAll(pageable);
        if (usersPage.getContent().isEmpty()) throw new DataNotFoundException();
        List<UsersDtoResponse> usersDtoResponseList = usersMapper.usersListToUsersDtoResponseList(usersPage.getContent());
        return new DtoPageBuilder<UsersDtoResponse>()
                .setContent(usersDtoResponseList)
                .setTotalPages(usersPage.getTotalPages())
                .setTotalElements(usersPage.getTotalElements())
                .build();
    }

    @Override
    public UsersDtoResponse findById(UUID id) {
        Optional<Users> user = usersRepository.findById(id);
        return usersMapper.usersToUsersDtoResponse(user.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    @Transactional
    public Boolean deleteById(UUID id) {
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

        inputCheck.fullCheck(usersDtoRequest);

        String[] hashSalt = getHashSalt(usersDtoRequest);
        String hash = hashSalt[0];
        String salt = hashSalt[1];

        Users user = new Users(usersDtoRequest.getUserRole(), usersDtoRequest.getUserStatus(),
                               usersDtoRequest.getUserLogin(), hash, salt);

        user.setUserId(UUID.randomUUID());
        usersRepository.save(user);

        usersRepository.findUsersByUserLogin(user.getUserLogin())
                .orElseThrow(() -> new UserNotCreatedException(user.getUserLogin()));

        return findById(user.getUserId());
    }

    private String[] getHashSalt(UsersDtoRequest usersDtoRequest) {
        String hashSalt = hashSaltCollector.collect(usersDtoRequest.getUserPasswd());
        return hashSaltParser.parse(hashSalt);
    }

    @Override
    @Transactional
    public Boolean update(UUID id, UsersDtoRequest usersDtoRequest) {
        Users user = findByIdNoConvert(id);

        if (usersDtoRequest.getUserLogin() != null) user.setUserLogin(usersDtoRequest.getUserLogin());
        if (usersDtoRequest.getUserRole() != null) user.setUserRole(usersDtoRequest.getUserRole());

        if (usersDtoRequest.getUserPasswd() != null && usersDtoRequest.getUserPasswd2() != null) {
            if (inputCheck.fullCheck(usersDtoRequest)) {

                String[] hashSalt = getHashSalt(usersDtoRequest);
                String hash = hashSalt[0];
                String salt = hashSalt[1];

                user.setUserPasswd(hash);
                user.setUserMark(salt);
            }
        }
        usersRepository.save(user);
        return true;
    }

    @Override
    public Users findByIdNoConvert(UUID id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.orElseThrow(() -> new ElementNotFoundException(id));
    }
}
