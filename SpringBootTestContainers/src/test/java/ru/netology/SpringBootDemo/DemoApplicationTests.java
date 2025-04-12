package ru.netology.SpringBootDemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    private static final GenericContainer<?> myApp1 = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private static final GenericContainer<?> myApp2 = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8080);
    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        myApp1.start();
        myApp2.start();
    }

    @Test


    void contextLoads() {
        ResponseEntity<String> forEntity1 = restTemplate.getForEntity("http://localhost:" + myApp1.getMappedPort(8080), String.class);
        System.out.println(forEntity1.getBody());
        ResponseEntity<String> forEntity2 = restTemplate.getForEntity("http://localhost:" + myApp2.getMappedPort(8080), String.class);
        System.out.println(forEntity2.getBody());
    }


}