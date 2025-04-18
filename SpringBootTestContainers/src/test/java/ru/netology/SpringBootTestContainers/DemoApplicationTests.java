package ru.netology.SpringBootTestContainers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Container
    private static final GenericContainer<?> myApp1 = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> myApp2 = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @Autowired
    TestRestTemplate restTemplate;



    @Test
    void contextLoadsDev() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + myApp1.getMappedPort(8080) + "/profile", String.class);
        assertEquals("Current profile is dev", forEntity.getBody());
    }

    @Test
    void contextLoadsProd() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + myApp2.getMappedPort(8081) + "/profile", String.class);
        assertEquals("Current profile is production", forEntity.getBody());
    }
}
