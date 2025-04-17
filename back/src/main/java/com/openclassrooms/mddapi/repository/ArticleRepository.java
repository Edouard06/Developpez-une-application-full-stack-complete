package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.ArticleEntity;

/**
 * Repository interface for managing {@link ArticleEntity} entities.
 * Provides methods to perform CRUD operations and custom queries related to articles.
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    List<ArticleEntity> findByThemeIdIn(List<Integer> themeIds, Sort sort);
}