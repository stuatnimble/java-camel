package com.example.springboot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AboutControllerTests {
    @Test
    public void testBark() {
        String expectedString = "woof";
        assertEquals(expectedString, "woof");
    }
}
