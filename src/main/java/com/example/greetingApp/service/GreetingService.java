package com.example.greetingApp.service;

import com.example.greetingApp.model.Greeting;
import com.example.greetingApp.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // UC2 - Simple Greeting
    public String getSimpleGreeting() {
        return "Hello World";
    }

    // UC3 - Personalized Greeting
    public String getPersonalizedGreeting(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            return "Hello, " + firstName + "!";
        } else if (lastName != null) {
            return "Hello, " + lastName + "!";
        } else {
            return getSimpleGreeting();
        }
    }

    // UC4 - Save a greeting
    public Greeting saveGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    // UC4 - Retrieve all stored greetings
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    // UC4 - Retrieve a specific greeting by ID
    public Optional<Greeting> getGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

    // UC5 - Retrieve only the message of a greeting by ID
    public String findGreetingMessageById(Long id) {
        return greetingRepository.findMessageById(id);
    }
}
