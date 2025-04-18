package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
/**
 * Mapper interface for converting between CommentEntity and CommentDto.
 * 
 * Facilitates transformation of comment data between database models and frontend-friendly DTOs,
 * allowing for safe exposure and structured data manipulation.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "author.displayUsername", target = "author")
    CommentDto toDto(CommentEntity comment);
}
