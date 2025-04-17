package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<ThemeEntity> findAll() {
        return themeRepository.findAll();
    }

    public Optional<ThemeEntity> findById(Integer id) {
        return themeRepository.findById(id);
    }
}
