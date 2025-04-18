package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.UserEntity;
import com.openclassrooms.mddapi.payload.request.UserRequest;

import java.util.Optional;

public interface IUserService {
    Optional<UserDto> getUserById(Integer id);
    boolean updateUser(UserRequest request);
    UserDto getSafeCurrentUser();
    UserEntity getCurrentUser();

}
