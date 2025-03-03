package com.example.greetingApp.controller;

import com.example.greetingApp.service.GreetingService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/greet")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // UC1
    @GetMapping
    public Map<String, String> getGreeting() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, this is a GET request!");
        return response;
    }

    @PostMapping
    public Map<String, String> postGreeting(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, this is a POST request!");
        response.put("received", request.get("name"));
        return response;
    }

    @PutMapping
    public Map<String, String> putGreeting(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, this is a PUT request!");
        response.put("updated", request.get("name"));
        return response;
    }

    @DeleteMapping
    public Map<String, String> deleteGreeting() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, this is a DELETE request!");
        return response;
    }

    // ✅ UC2
    @GetMapping("/simple")
    public Map<String, String> getSimpleGreeting() {
        return Map.of("message", greetingService.getSimpleGreeting());
    }

    // UC3 - New Endpoint for Personalized Greeting
    @GetMapping("/personalized")
    public Map<String, String> getPersonalizedGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return Map.of("message", greetingService.getPersonalizedGreeting(firstName, lastName));
    }
}
