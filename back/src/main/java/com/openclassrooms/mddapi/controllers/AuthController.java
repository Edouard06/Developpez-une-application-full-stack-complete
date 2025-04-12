package com.openclassrooms.mddapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.payload.response.AuthResponse;
import com.openclassrooms.mddapi.payload.response.GenericResponse;
import com.openclassrooms.mddapi.payload.response.UserResponse;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.services.AuthenticationService;


@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationService authenticationService;
    
        /**
         * Registers a new user and generates a JWT token upon successful registration.
         * 
         * @param request The {@link RegisterRequest} object containing the user's registration details.
         * @return ResponseEntity containing an {@link AuthResponse} with the JWT token if registration is successful,
         *         or a {@link GenericResponse} with an error message if registration fails.
         */
        @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
            try {
                User registeredUser = authenticationService.register(request);
    
                String jwtToken = jwtUtils.generateToken(registeredUser);
                AuthResponse response = new AuthResponse(jwtToken);
    
                return ResponseEntity.ok(response);
            }
            catch (AuthenticationException ex) {
                GenericResponse errorResponse = new GenericResponse("User already exists.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
            }
        }

    /**
     * Authenticates a user and generates a JWT token upon successful login.
     * 
     * @param request The {@link LoginRequest} object containing the user's login credentials.
     * @return ResponseEntity containing an {@link AuthResponse} with the JWT token if authentication is successful,
     *         or a {@link GenericResponse} with an error message if authentication fails.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest request) {
        try {
            User authenticatedUser = authenticationService.authenticate(request);

            String jwtToken = jwtUtils.generateToken(authenticatedUser);
            AuthResponse response = new AuthResponse(jwtToken);

            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException ex) {
            GenericResponse errorResponse = new GenericResponse("Wrong credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    /**
     * Retrieves the details of the currently authenticated user.
     * 
     * @return ResponseEntity containing a {@link UserResponse} with the user's details if authenticated,
     *         or a {@link GenericResponse} with an error message if the user is not authenticated.
     */
    @GetMapping("/me")
    public ResponseEntity<?> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        /*
            Handle case where user tries to access /me without being authenticated 
            (Error in terminal when casting `authentication.getPrincipal()` to User class)
        */ 
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof User)) {
            GenericResponse errorResponse = new GenericResponse("Forbidden");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }

        User currentUser = (User) authentication.getPrincipal();

        UserResponse userDetails = new UserResponse(
            currentUser.getId(),
            currentUser.getDisplayUsername(),
            currentUser.getEmail(),
            currentUser.getCreatedAt(),
            currentUser.getUpdatedAt()
        );

        return ResponseEntity.ok(userDetails);
    }
}