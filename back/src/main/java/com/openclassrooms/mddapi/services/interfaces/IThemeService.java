package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.dto.ThemeDto;

import java.util.List;
import java.util.Optional;

public interface IThemeService {
    List<ThemeDto> findAll();
    Optional<ThemeDto> findById(Integer id);
}
