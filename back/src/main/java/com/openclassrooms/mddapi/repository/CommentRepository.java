package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Comment;

/**
 * Repository interface for managing {@link Comment} entities.
 * Provides methods to perform CRUD operations and custom queries related to comments.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * Retrieves a list of {@link Comment} entities associated with a specific article and sorts them
     * based on the provided sorting order.
     * 
     * @param articleId The ID of the article for which comments are to be retrieved.
     * @param sort The {@link Sort} object specifying the sort order.
     * @return A list of {@link Comment} entities associated with the specified article ID,
     *         sorted according to the provided sort order.
     */
    List<Comment> findByArticleId(Integer articleId, Sort sort);
}