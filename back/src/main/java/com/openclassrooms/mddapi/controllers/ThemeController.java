package com.openclassrooms.mddapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.services.ThemeService;


@RestController
@RequestMapping("/api/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<ThemeEntity> themes = this.themeService.findAll();
        return ResponseEntity.ok().body(themes);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            Optional<ThemeEntity> theme = this.themeService.findById(Integer.parseInt(id));
            
            return theme.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}