package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.mapper.UserResponseMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.UserRequest;
import com.openclassrooms.mddapi.payload.response.UserResponse;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;

    public UserService(UserRepository userRepository, UserResponseMapper userResponseMapper) {
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
    }

    public Optional<UserResponse> getUserById(final Integer id) {
        return userRepository.findById(id)
                .map(userResponseMapper::toUserResponse);
    }

    public UserResponse getSafeCurrentUser() {
        User user = getCurrentUser();
        return user != null ? userResponseMapper.toUserResponse(user) : null;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                return userRepository.findByEmail(userDetails.getUsername()).orElse(null);
            }
        }
        return null;
    }

    public User updateUser(UserRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }
}
