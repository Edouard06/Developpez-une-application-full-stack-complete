package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Article;

/**
 * Repository interface for managing {@link Article} entities.
 * Provides methods to perform CRUD operations and custom queries related to articles.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    /**
     * Retrieves a list of {@link Article} entities based on the provided theme IDs and sorting order.
     * 
     * @param themeIds A list of theme IDs to filter the articles.
     * @param sort The {@link Sort} object specifying the sort order.
     * @return A list of {@link Article} entities matching the provided theme IDs and sorting order.
     */
    List<Article> findByThemeIdIn(List<Integer> themeIds, Sort sort);
}