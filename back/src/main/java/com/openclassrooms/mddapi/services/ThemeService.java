package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public Optional<Theme> findById(Integer id) {
        return this.themeRepository.findById(id);
    }

    public List<Theme> findAll() {
        return this.themeRepository.findAll();
    }
}