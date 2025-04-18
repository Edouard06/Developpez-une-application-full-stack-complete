package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
/**
 * Mapper interface for converting a UserEntity into a UserDto.
 * 
 * Transforms user entities into a safe representation (UserDto) for responses,
 * excluding sensitive fields such as passwords or internal metadata.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "displayUsername", target = "username")
    UserDto toDto(UserEntity user);
}
