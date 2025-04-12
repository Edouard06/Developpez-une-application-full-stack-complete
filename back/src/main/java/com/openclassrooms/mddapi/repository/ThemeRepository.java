package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Theme;

/**
 * Repository interface for managing {@link Theme} entities.
 * Provides methods to perform CRUD operations related to themes.
 */
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {}