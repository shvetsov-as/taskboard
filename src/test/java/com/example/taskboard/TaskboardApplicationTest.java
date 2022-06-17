package com.example.taskboard;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
//@RunWith(SpringRunner.class) for JUnit4

@ContextConfiguration(initializers = {TaskboardApplicationTest.Initializer.class})
@ComponentScan("com.example.taskboard")
class TaskboardApplicationTest {

    public static PostgreSQLContainer container;

    static {
        container = new PostgreSQLContainer("postgres:13.3")
                .withDatabaseName("integration-tests-db")
                .withUsername("postgres")
                .withPassword("postgres");
        container.start();
    }

//    @DynamicPropertySource
//    static void properties (DynamicPropertyRegistry registry){
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//        registry.add("spring.datasource.username", container::getUsername);
//        registry.add("spring.datasource.password", container::getPassword);
//    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + container.getJdbcUrl(),
                    "spring.datasource.username=" + container.getUsername(),
                    "spring.datasource.password=" + container.getPassword(),
                    "spring.liquibase.enabled=true",
                    "jpa.hibernate.ddl-auto=validate"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    void test() {
        assertTrue(container.isRunning());
        System.out.println("******* Container is running *******");
    }

    @Test
    void contextLoads() {
        System.out.println("******* Context loaded *******");
    }
}