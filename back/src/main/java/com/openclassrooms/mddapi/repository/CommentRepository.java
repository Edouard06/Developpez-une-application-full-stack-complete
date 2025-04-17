package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.CommentEntity;

/**
 * Repository interface for managing {@link CommentEntity} entities.
 * Provides methods to perform CRUD operations and custom queries related to comments.
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByArticleId(Integer articleId, Sort sort);
}