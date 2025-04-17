package com.openclassrooms.mddapi.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.payload.request.UserRequest;
import com.openclassrooms.mddapi.payload.response.GenericResponse;
import com.openclassrooms.mddapi.payload.response.UserResponse;
import com.openclassrooms.mddapi.services.UserService;
import com.openclassrooms.mddapi.models.UserEntity;

/**
 * Controller for managing user information.
 * Provides endpoints for retrieving user details by ID and updating user information.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            Optional<UserResponse> user = userService.getUserById(Integer.parseInt(id));
            return user.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest request) {
        try {
            Optional<UserEntity> optionalUser = userService.findById(request.getId());

            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(new GenericResponse("Utilisateur introuvable"));
            }

            UserEntity user = optionalUser.get();

            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());

            if (request.getPassword() != null && !request.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            }

            userService.updateUser(request);

            return ResponseEntity.ok(new GenericResponse("Utilisateur modifié"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new GenericResponse("Erreur lors de la modification"));
        }
    }
}
