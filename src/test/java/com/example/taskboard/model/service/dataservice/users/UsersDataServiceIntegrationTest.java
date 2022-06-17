package com.example.taskboard.model.service.dataservice.users;

import com.example.taskboard.entity.classifier.UserRole;
import com.example.taskboard.entity.classifier.UserStatus;
import com.example.taskboard.entity.users.dto.UsersDtoRequest;
import com.example.taskboard.entity.users.dto.UsersDtoResponse;
import com.example.taskboard.entity.users.mapper.UsersMapper;
import com.example.taskboard.model.security.passwordManager.HashSaltCollector;
import com.example.taskboard.model.security.passwordManager.HashSaltParser;
import com.example.taskboard.model.security.passwordManager.passwdInputCheck.InputCheck;
import com.example.taskboard.repo.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class) for JUnit4
//@ContextConfiguration(initializers = {UsersDataServiceIntegrationTest.Initializer.class})
//@SpringBootTest
public class UsersDataServiceIntegrationTest {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private InputCheck inputCheck;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private HashSaltCollector hashSaltCollector;
    @Autowired
    private HashSaltParser hashSaltParser;

//    private static PostgreSQLContainer sqlContainer;
//    private static @Value("${spring.datasource.password}") String password;
//    private static @Value("${spring.datasource.username}") String username;
//
//    static {
//        sqlContainer = new PostgreSQLContainer("postgres:13.3")//:10.7
//                .withDatabaseName("integration-tests-db")
//                .withUsername("postgres")
//                .withPassword("postgres");
//        sqlContainer.start();
//    }

//    static class Initializer
//            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//            TestPropertyValues.of(
//                    "spring.datasource.url=" + sqlContainer.getJdbcUrl(),
//                    "spring.datasource.username=" + sqlContainer.getUsername(),
//                    "spring.datasource.password=" + sqlContainer.getPassword()
//            ).applyTo(configurableApplicationContext.getEnvironment());
//        }
//    }

    private UsersDataService usersDataService;
    private UsersDtoRequest usersDtoRequestCorrect;
    private UsersDtoRequest usersDtoRequestIncorrect;
    private UsersDtoRequest usersDtoRequestNullParam;

    //private final UUID id = UUID.fromString("8426d9f2-b439-42e1-af97-9170005ad425");
    private final UserRole role = UserRole.ADMIN;
    private final String login = "Login123";
    private final String password1 = "Password1234";
    private final String password2 = "Password1234";
    private final String passwordIncorrect = "passwordIncorrect";
    private final UserStatus userStatus = UserStatus.ACTIVE;


    @BeforeEach
    void init() {

        usersDataService = new UsersDataService(usersRepository, inputCheck, usersMapper, hashSaltCollector, hashSaltParser);
        usersDtoRequestCorrect = new UsersDtoRequest(role, login, password1, password2, userStatus);
        usersDtoRequestIncorrect = new UsersDtoRequest(role, login, password1, passwordIncorrect, userStatus);
        usersDtoRequestNullParam = new UsersDtoRequest(role, null, password1, password2, userStatus);
    }

    @Test
    public void shouldCreateNewUser() {

//        Users testUser = new Users(usersDtoRequestCorrect.getUserRole(), usersDtoRequestCorrect.getUserStatus(),
//                usersDtoRequestCorrect.getUserLogin(), "mockedHash", "mockedSalt");
//        testUser.setUserId(id);


        UsersDtoResponse response = usersDataService.create(usersDtoRequestCorrect);
        assertThat(usersRepository.findById(response.getUserId()).get().getUserId()).isEqualTo(response.getUserId());
    }

    @Test
    public void shouldNotCreateNewUser() {

//        assertThatThrownBy(() -> {
//            usersDataService.create(usersDtoRequestNullParam);
//        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowCreateException() {

//        Users testUser = new Users(usersDtoRequestIncorrect.getUserRole(), usersDtoRequestIncorrect.getUserStatus(),
//                usersDtoRequestIncorrect.getUserLogin(), "mockedHash", "mockedSalt");
//
//
//        assertThatThrownBy(() -> {
//            usersDataService.create(usersDtoRequestIncorrect);
//        }).isInstanceOf(PasswordsNotMatchException.class).hasMessageContaining("Passwords not match in UsersDtoRequest");
    }

}
