package com.example.springboot;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AboutControllerTests {
    @Test
    public void testBark() {
        String expectedString = "woof";
        assertEquals(expectedString, "woof");

        when().get("/about").then().statusCode(200);
    }
}

