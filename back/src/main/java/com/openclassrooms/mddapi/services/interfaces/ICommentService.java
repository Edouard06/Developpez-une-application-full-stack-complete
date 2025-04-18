package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.CommentEntity;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ICommentService {
    void addComment(CommentEntity comment);
    List<CommentDto> getCommentsByArticleId(Integer articleId, Sort sort);
}
