package com.example.taskboard.model.service.dataservice.users;

import com.example.taskboard.entity.users.Users;
import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.entity.users.dto.UsersDtoResponse;
import com.example.taskboard.model.dataexeptions.DataNotFoundException;
import com.example.taskboard.model.dataexeptions.ElementNotFoundException;
import com.example.taskboard.model.dataexeptions.UserNotCreatedException;
import com.example.taskboard.model.dataexeptions.UserNotFoundException;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.dtoPageBuilder.DtoPageBuilder;
import com.example.taskboard.model.service.converter.request.DtoUsersConverter;
import com.example.taskboard.model.service.converter.request.DtoUsersPreConverterCheck;
import com.example.taskboard.model.service.converter.response.UsersConverter;
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
    private final UsersConverter usersConverter;
    private final DtoUsersConverter dtoUsersConverter;
    private final DtoUsersPreConverterCheck dtoUsersPreConverterCheck;

    public UsersDataService(UsersRepository usersRepository,
                            UsersConverter usersConverter,
                            DtoUsersConverter dtoUsersConverter,
                            DtoUsersPreConverterCheck dtoUsersPreConverterCheck) {
        this.usersRepository = usersRepository;
        this.usersConverter = usersConverter;
        this.dtoUsersConverter = dtoUsersConverter;
        this.dtoUsersPreConverterCheck = dtoUsersPreConverterCheck;
    }

    @Override
    public List<UsersDtoResponse> findAll() {
        List<Users> usersList = usersRepository.findAll();
        if (usersList.size() == 0) throw new DataNotFoundException();
        return usersConverter.convertToDto(usersList);
    }

    @Override
    public DtoPage<UsersDtoResponse> findAllPageable(Pageable pageable) {
        Page<Users> usersPage = usersRepository.findAll(pageable);
        if (usersPage.getContent().size() == 0) throw new DataNotFoundException();
        List<UsersDtoResponse> usersDtoResponseList = usersConverter.convertToDto(usersPage.getContent());
        return new DtoPageBuilder<UsersDtoResponse>()
                .setContent(usersDtoResponseList)
                .setTotalPages(usersPage.getTotalPages())
                .setTotalElements(usersPage.getTotalElements())
                .build();
    }

    @Override
    public UsersDtoResponse findById(Long id) {
        Optional<Users> user = usersRepository.findById(id);
        return usersConverter.convertToDto(user.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return true;
        } else throw new ElementNotFoundException(id);
    }

    @Override
    public UsersDtoResponse findUsersByUserLogin(String login) {
        Optional<Users> user = usersRepository.findUsersByUserLogin(login);
        return usersConverter.convertToDto(user.orElseThrow(() -> new UserNotFoundException(login)));
    }

    @Override
    public UsersDtoResponse create(UsersDtoRequest usersDtoRequest) {
        Users user = dtoUsersConverter.convertToEntityCreate(usersDtoRequest);
        user = usersRepository.save(user);
        if (findUsersByUserLogin(user.getUserLogin()).toString().isBlank()) //
            throw new UserNotCreatedException(usersDtoRequest.getUserLogin());
        return findById(user.getUserId());
    }

    @Override
    public Boolean update(Long id, UsersDtoRequest usersDtoRequest) {
        Users user = findByIdNoConvert(id);

        if (usersDtoRequest.getUserLogin() != null)
            user.setUserLogin(usersDtoRequest.getUserLogin());

        if (usersDtoRequest.getUserRole() != null)
            user.setUserRole(usersDtoRequest.getUserRole());

        if (usersDtoRequest.getUserPasswd() != null && usersDtoRequest.getUserPasswd2() != null) {
            if (dtoUsersPreConverterCheck.userPreCreatePasswordCheck(usersDtoRequest)) {
                Map<String, String> salt_hashMap = dtoUsersPreConverterCheck.userPreCreateGetPasswd(usersDtoRequest);
                user.setUserPasswd(salt_hashMap.get("hash"));
                user.setUserMark(salt_hashMap.get("salt"));
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
