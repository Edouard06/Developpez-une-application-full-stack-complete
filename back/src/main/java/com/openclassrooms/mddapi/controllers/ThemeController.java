package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.services.interfaces.IThemeService;


@RestController
@RequestMapping("/api/theme")
public class ThemeController {

    private final IThemeService themeService;

    public ThemeController(IThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    public ResponseEntity<List<ThemeDto>> findAll() {
        return ResponseEntity.ok(themeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeDto> findById(@PathVariable("id") String id) {
        try {
            return themeService.findById(Integer.parseInt(id))
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

