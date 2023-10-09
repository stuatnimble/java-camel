package com.example.springboot;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
@SpringBootTest
@AutoConfigureMockMvc
public class AboutControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testBark() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/about").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("<html>\n<head>\n<title>$name</title>\n</head>\n<body>\n<h1>Name: $name</h1>\n<p>ID: $id</p>\n</body>\n</html>\n")))
                .andExpect(content().string(containsString("Name: $name")));
    }
}

