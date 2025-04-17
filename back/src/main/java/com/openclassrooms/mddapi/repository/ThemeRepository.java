package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.ThemeEntity;

/**
 * Repository interface for managing {@link ThemeEntity} entities.
 * Provides methods to perform CRUD operations related to themes.
 */
@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, Integer> {}