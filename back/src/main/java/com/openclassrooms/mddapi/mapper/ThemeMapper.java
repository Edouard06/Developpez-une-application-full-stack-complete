package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.models.ThemeEntity;
import org.mapstruct.Mapper;
/**
 * Mapper interface for converting between ThemeEntity and ThemeDto.
 * 
 * Used to safely expose theme information (name, description) to the frontend,
 * hiding unnecessary or sensitive internal attributes.
 */
@Mapper(componentModel = "spring")
public interface ThemeMapper {
    ThemeDto toDto(ThemeEntity theme);
}
