package com.example.Rideeasy.service;


import com.example.Rideeasy.entity.User;
import com.example.Rideeasy.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service // Capital "S"
public class UserService {

    @Autowired // "w" is lowercase in Java
    private UserRepository userRepository; // Corrected variable name

    @CircuitBreaker(name = "dbService", fallbackMethod = "dbFallback")
    public User getUser(Long id) {
        // Use the instance variable "userRepository", not the class name
        return userRepository.findById(id).orElseThrow();
    }

    // The fallback method signature must match the original method's parameters + the exception
    public User dbFallback(Long id, Throwable t) {
        return new User(id, "Guest User (System in Maintenance)");
    }
}