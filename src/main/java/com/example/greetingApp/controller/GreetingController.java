package com.example.greetingApp.controller;
import com.example.greetingApp.model.Greeting;
import com.example.greetingApp.service.GreetingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return Map.of("message", "Hello, this is a GET request!");
    }

    @PostMapping
    public Map<String, String> postGreeting(@RequestBody Map<String, String> request) {
        return Map.of("message", "Hello, this is a POST request!", "received", request.get("name"));
    }

    @PutMapping
    public Map<String, String> putGreeting(@RequestBody Map<String, String> request) {
        return Map.of("message", "Hello, this is a PUT request!", "updated", request.get("name"));
    }

    @DeleteMapping
    public Map<String, String> deleteGreeting() {
        return Map.of("message", "Hello, this is a DELETE request!");
    }

    // UC2
    @GetMapping("/simple")
    public Map<String, String> getSimpleGreeting() {
        return Map.of("message", greetingService.getSimpleGreeting());
    }

    // UC3
    @GetMapping("/personalized")
    public Map<String, String> getPersonalizedGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return Map.of("message", greetingService.getPersonalizedGreeting(firstName, lastName));
    }

    //  UC4 - Save a greeting
    @PostMapping("/save")
    public Greeting saveGreeting(@RequestBody Map<String, String> request) {
        return greetingService.saveGreeting(request.get("message"));
    }

    // UC4 - Get all saved greetings
    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // UC4 - Get a specific greeting by ID
    @GetMapping("/{id}")
    public Optional<Greeting> getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }
}
