package com.openclassrooms.mddapi.services;
/**
 * Service for managing comment operations such as saving and fetching.
 */

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.models.CommentEntity;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.services.interfaces.ICommentService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public void addComment(CommentEntity comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> getCommentsByArticleId(Integer articleId, Sort sort) {
        return commentRepository.findByArticleId(articleId, sort)
                .stream()
                .map(commentMapper::toDto)
                .toList();
    }
}
