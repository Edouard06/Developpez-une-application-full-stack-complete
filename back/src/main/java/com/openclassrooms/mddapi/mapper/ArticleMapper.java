package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
/**
 * Mapper interface for converting between ArticleEntity and ArticleDto.
 * 
 * Used to transform Article entities into DTOs and vice versa, ensuring a clean separation
 * between the persistence layer and data exposed to the frontend.
 */

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(source = "theme.id", target = "themeId")
    @Mapping(source = "author.id", target = "authorId")
    ArticleDto toDto(ArticleEntity article);
}