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

import com.openclassrooms.mddapi.models.UserEntity;
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
    
       
        @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
            try {
                UserEntity registeredUser = authenticationService.register(request);
    
                String jwtToken = jwtUtils.generateToken(registeredUser);
                AuthResponse response = new AuthResponse(jwtToken);
    
                return ResponseEntity.ok(response);
            }
            catch (AuthenticationException ex) {
                GenericResponse errorResponse = new GenericResponse("User already exists.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
            }
        }

 
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest request) {
        try {
            UserEntity authenticatedUser = authenticationService.authenticate(request);

            String jwtToken = jwtUtils.generateToken(authenticatedUser);
            AuthResponse response = new AuthResponse(jwtToken);

            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException ex) {
            GenericResponse errorResponse = new GenericResponse("Wrong credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

   
    @GetMapping("/me")
    public ResponseEntity<?> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof UserEntity)) {
            GenericResponse errorResponse = new GenericResponse("Forbidden");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }

        UserEntity currentUser = (UserEntity) authentication.getPrincipal();

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