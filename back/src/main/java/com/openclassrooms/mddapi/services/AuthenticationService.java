package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.repository.UserRepository;

/**
 * Service class for handling authentication and registration of {@link User} entities.
 * Provides methods to authenticate users and register new users.
 */
@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Authenticates a user based on the provided login credentials.
     * 
     * @param input The {@link LoginRequest} containing the user's email and password.
     * @return The authenticated {@link User} if authentication is successful.
     * @throws org.springframework.security.core.AuthenticationException if authentication fails.
     */
    public User authenticate(LoginRequest input) {
        // Perform authentication using the provided email and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        // Retrieve the user by email and return it
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found")); // Adjust error handling as needed
    }

    /**
     * Registers a new user with the provided registration details.
     * 
     * @param input The {@link RegisterRequest} containing the user's registration details.
     * @return The newly created {@link User} entity.
     */
    public User register(RegisterRequest input) {
        // Create a new user with the provided details and encoded password
        User user = new User()
            .setUsername(input.getUsername())
            .setEmail(input.getEmail())
            .setPassword(passwordEncoder.encode(input.getPassword()));

        // Save the user to the repository and return the created user
        return userRepository.save(user);
    }
}