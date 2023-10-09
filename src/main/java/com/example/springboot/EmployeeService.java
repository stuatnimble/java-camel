package com.example.springboot;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeService {
    private WebClient client;
    public EmployeeResponse getEmployeeById(int id) {
        return client.get().uri("/address/" + id).retrieve().bodyToMono(EmployeeResponse.class).block();
    }
}
