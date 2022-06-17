package com.example.taskboard.repo;

import com.example.taskboard.entity.classifier.UserRole;
import com.example.taskboard.entity.classifier.UserStatus;
import com.example.taskboard.entity.users.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository testRepo;

    @Test
    void shouldFindUsersByUserLogin() {
        //give
        UUID id = UUID.fromString("8426d9f2-b439-42e1-af97-9170005ad425");
        String login = "Login123";
        Users user = new Users(
                UserRole.USER,
                UserStatus.ACTIVE,
                login,
                "Password1234",
                "dJUfbyAplq"
        );
        user.setUserId(id);

        testRepo.save(user);

        //when
        Optional<Users> expectedEntity = testRepo.findUsersByUserLogin(login);

        //then
        assertThat(expectedEntity.isPresent()).isTrue();
    }

    @Test
    void shouldFindAll() {
        //give
        Users user1 = new Users(
                UserRole.USER,
                UserStatus.ACTIVE,
                "Login123456",
                "Password123",
                "dJUfbyAplq"
        );
        UUID id1 = UUID.fromString("8426d9f2-b439-42e1-af97-9170005ad434");

        Users user2 = new Users(
                UserRole.USER,
                UserStatus.ACTIVE,
                "Login12345",
                "Password1234",
                "dJUfbyhplq"
        );
        UUID id2 = UUID.fromString("8426d9f2-b439-42e1-af97-9170005ad489");

        user1.setUserId(id1);
        testRepo.save(user1);

        user2.setUserId(id2);
        testRepo.save(user2);

        //when
        List<Users> expectedUsers = testRepo.findAll();

        //then
        assertThat(expectedUsers.size()).isEqualTo(11);
    }
}
