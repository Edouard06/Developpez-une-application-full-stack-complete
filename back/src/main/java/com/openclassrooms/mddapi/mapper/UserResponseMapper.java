package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.models.UserEntity;
import com.openclassrooms.mddapi.payload.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);

    @Mapping(source = "displayUsername", target = "username")
    UserResponse toUserResponse(UserEntity user);
}