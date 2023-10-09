package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
public class AboutControllerTests {
    @Test
    public void testBark() {
        String expectedString = "woof";
        assertEquals(expectedString, "woof");

        when().get("/about").then().statusCode(200);
    }
}

