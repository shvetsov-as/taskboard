package com.example.taskboard.model.service.dataservice.users;

import com.example.taskboard.entity.classifier.UserRole;
import com.example.taskboard.entity.classifier.UserStatus;
import com.example.taskboard.entity.users.Users;
import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.entity.users.dto.UsersDtoResponse;
import com.example.taskboard.entity.users.mapper.UsersMapper;
import com.example.taskboard.model.dataexeptions.PasswordsNotMatchException;
import com.example.taskboard.model.security.passwordManager.HashSaltCollector;
import com.example.taskboard.model.security.passwordManager.HashSaltParser;
import com.example.taskboard.model.security.passwordManager.passwdInputCheck.InputCheck;
import com.example.taskboard.repo.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class UsersDataServiceTest {

    private UsersDataService usersDataService;
    private UsersDtoRequest usersDtoRequestCorrect;
    private UsersDtoRequest usersDtoRequestIncorrect;
    private UsersDtoRequest usersDtoRequestNullParam;

    private final UUID id = UUID.fromString("8426d9f2-b439-42e1-af97-9170005ad425");
    private final UserRole role = UserRole.ADMIN;
    private final String login = "Login123";
    private final String password1 = "Password1234";
    private final String password2 = "Password1234";
    private final String passwordIncorrect = "passwordIncorrect";
    private final UserStatus userStatus = UserStatus.ACTIVE;

    @Mock
    private UsersRepository usersRepository;
    @Mock
    private InputCheck inputCheck;
    @Mock
    private UsersMapper usersMapper;
    @Mock
    private HashSaltCollector hashSaltCollector;
    @Mock
    private HashSaltParser hashSaltParser;

    @BeforeEach
    void init() {

        MockitoAnnotations.openMocks(this);

        usersDataService = new UsersDataService(usersRepository, inputCheck, usersMapper, hashSaltCollector, hashSaltParser);
        usersDtoRequestCorrect = new UsersDtoRequest(id, role, login, password1, password2, userStatus);
        usersDtoRequestIncorrect = new UsersDtoRequest(id, role, login, password1, passwordIncorrect, userStatus);
        usersDtoRequestNullParam = new UsersDtoRequest(id, role, null, password1, password2, userStatus);
    }

    @Test
    public void shouldCreateNewUser() {

        Users testUser = new Users(usersDtoRequestCorrect.getUserRole(), usersDtoRequestCorrect.getUserStatus(),
                usersDtoRequestCorrect.getUserLogin(), "mockedHash", "mockedSalt");
        testUser.setUserId(id);

        Mockito.when(inputCheck.fullCheck(usersDtoRequestCorrect)).thenReturn(true);
        assertThat(inputCheck.fullCheck(usersDtoRequestCorrect)).isTrue();

        Mockito.when(hashSaltCollector.collect(usersDtoRequestCorrect.getUserPasswd())).thenReturn("mockedHash:mockedSalt");
        assertThat(hashSaltCollector.collect(usersDtoRequestCorrect.getUserPasswd())).isEqualTo("mockedHash:mockedSalt");

        Mockito.when(hashSaltParser.parse("mockedHash:mockedSalt")).thenReturn(new String[]{"mockedHash", "mockedSalt"});
        assertThat(hashSaltParser.parse("mockedHash:mockedSalt")).isEqualTo(new String[]{"mockedHash", "mockedSalt"});

        Mockito.when(usersRepository.save(testUser)).thenReturn(testUser);
        assertThat(testUser).isEqualTo(usersRepository.save(testUser));

        Mockito.when(usersRepository.findById(testUser.getUserId())).thenReturn(Optional.of(testUser));
        assertThat(testUser).isEqualTo(Optional.of(testUser).get());

        Mockito.when(usersRepository.findUsersByUserLogin(testUser.getUserLogin())).thenReturn(Optional.of(testUser));
        assertThat(usersRepository.findUsersByUserLogin(testUser.getUserLogin()).get()).isEqualTo(testUser);

        Mockito.when(usersMapper.usersToUsersDtoResponse(testUser)).thenReturn(
                new UsersDtoResponse(testUser.getUserId(), testUser.getUserRole(), testUser.getUserLogin(), testUser.getUserStatus())
        );

        UsersDtoResponse response = usersDataService.create(usersDtoRequestCorrect, id);
        assertThat(response).isEqualTo(new UsersDtoResponse(
                testUser.getUserId(), testUser.getUserRole(), testUser.getUserLogin(), testUser.getUserStatus()
        ));
    }

    @Test
    public void shouldNotCreateNewUser() {

        Users testUser = new Users(usersDtoRequestNullParam.getUserRole(), usersDtoRequestNullParam.getUserStatus(),
                usersDtoRequestNullParam.getUserLogin(), "mockedHash", "mockedSalt");
        testUser.setUserId(id);

        Mockito.when(inputCheck.fullCheck(usersDtoRequestNullParam)).thenReturn(true);
        Mockito.when(hashSaltCollector.collect(usersDtoRequestNullParam.getUserPasswd())).thenReturn("mockedHash:mockedSalt");
        Mockito.when(hashSaltParser.parse("mockedHash:mockedSalt")).thenReturn(new String[]{"mockedHash", "mockedSalt"});
        Mockito.when(usersRepository.save(testUser)).thenReturn(testUser);
        Mockito.when(usersRepository.findById(testUser.getUserId())).thenReturn(Optional.of(testUser));
        Mockito.when(usersRepository.findUsersByUserLogin(testUser.getUserLogin())).thenReturn(Optional.of(testUser));
        Mockito.when(usersMapper.usersToUsersDtoResponse(testUser)).thenReturn(
                new UsersDtoResponse(testUser.getUserId(), testUser.getUserRole(), testUser.getUserLogin(), testUser.getUserStatus())
        );

        assertThatThrownBy(() -> {
            usersDataService.create(usersDtoRequestNullParam, id);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowCreateException() {

        Users testUser = new Users(usersDtoRequestIncorrect.getUserRole(), usersDtoRequestIncorrect.getUserStatus(),
                usersDtoRequestIncorrect.getUserLogin(), "mockedHash", "mockedSalt");
        testUser.setUserId(id);

        Mockito.when(inputCheck.fullCheck(usersDtoRequestIncorrect)).thenThrow(new PasswordsNotMatchException());
        Mockito.when(hashSaltCollector.collect(usersDtoRequestIncorrect.getUserPasswd())).thenReturn("mockedHash:mockedSalt");
        Mockito.when(hashSaltParser.parse("mockedHash:mockedSalt")).thenReturn(new String[]{"mockedHash", "mockedSalt"});
        Mockito.when(usersRepository.save(testUser)).thenReturn(testUser);
        Mockito.when(usersRepository.findById(testUser.getUserId())).thenReturn(Optional.of(testUser));
        Mockito.when(usersRepository.findUsersByUserLogin(testUser.getUserLogin())).thenReturn(Optional.of(testUser));
        Mockito.when(usersMapper.usersToUsersDtoResponse(testUser)).thenReturn(
                new UsersDtoResponse(testUser.getUserId(), testUser.getUserRole(), testUser.getUserLogin(), testUser.getUserStatus())
        );

        assertThatThrownBy(() -> {
            usersDataService.create(usersDtoRequestIncorrect, id);
        }).isInstanceOf(PasswordsNotMatchException.class).hasMessageContaining("Passwords not match in UsersDtoRequest");
    }

}
