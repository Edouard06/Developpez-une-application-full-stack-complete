package com.openclassrooms.mddapi.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;


@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public List<Theme> findAll() {
        return this.themeRepository.findAll();
    }

    public Optional<Theme> findById(Integer id) {
        return this.themeRepository.findById(id);
    }
}