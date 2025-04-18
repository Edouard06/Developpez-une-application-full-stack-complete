package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.services.interfaces.IThemeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService implements IThemeService {

    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    public ThemeService(ThemeRepository themeRepository, ThemeMapper themeMapper) {
        this.themeRepository = themeRepository;
        this.themeMapper = themeMapper;
    }

    @Override
    public List<ThemeDto> findAll() {
        return themeRepository.findAll()
                .stream()
                .map(themeMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ThemeDto> findById(Integer id) {
        return themeRepository.findById(id)
                .map(themeMapper::toDto);
    }
    public Optional<ThemeEntity> getEntityById(Integer id) {
        return this.themeRepository.findById(id);
    }
    
}
