package com.openclassrooms.mddapi.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.mapper.UserResponseMapper;
import com.openclassrooms.mddapi.models.UserEntity;
import com.openclassrooms.mddapi.payload.request.UserRequest;
import com.openclassrooms.mddapi.payload.response.UserResponse;
import com.openclassrooms.mddapi.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(
        UserRepository userRepository,
        UserResponseMapper userResponseMapper,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserResponse> getUserById(final Integer id) {
        return userRepository.findById(id).map(userResponseMapper::toUserResponse);
    }

    public Optional<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }

    public UserResponse getSafeCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                UserEntity user = userRepository.findByEmail(username).orElse(null);
                return userResponseMapper.toUserResponse(user);
            }
        }
        return null;
    }

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                return userRepository.findByEmail(username).orElse(null);
            }
        }
        return null;
    }

    public UserEntity updateUser(UserRequest request) {
        UserEntity user = userRepository.findById(request.getId()).orElse(null);
        if (user == null) return null;

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return userRepository.save(user);
    }
}
