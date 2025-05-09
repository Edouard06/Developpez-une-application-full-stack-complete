package com.openclassrooms.mddapi.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.payload.request.UserRequest;
import com.openclassrooms.mddapi.payload.response.GenericResponse;
import com.openclassrooms.mddapi.services.interfaces.IUserService;
import com.openclassrooms.mddapi.dto.UserDto;

/**
 * Controller for managing user information.
 * Provides endpoints for retrieving user details by ID and updating user information.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") String id) {
        try {
            Optional<UserDto> user = userService.getUserById(Integer.parseInt(id));
            return user.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<GenericResponse> updateUser(@RequestBody UserRequest request) {
        try {
            boolean updated = userService.updateUser(request);
            if (!updated) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new GenericResponse("Utilisateur introuvable"));
            }
            return ResponseEntity.ok(new GenericResponse("Utilisateur modifié"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GenericResponse("Erreur lors de la modification"));
        }
    }

    @GetMapping("/me")
public ResponseEntity<UserDto> getCurrentUserProfile() {
    return Optional.ofNullable(userService.getSafeCurrentUser())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
}

}
