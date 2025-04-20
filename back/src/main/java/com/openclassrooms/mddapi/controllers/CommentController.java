package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.ArticleEntity;
import com.openclassrooms.mddapi.models.CommentEntity;
import com.openclassrooms.mddapi.models.UserEntity;
import com.openclassrooms.mddapi.payload.request.CommentRequest;
import com.openclassrooms.mddapi.payload.response.GenericResponse;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.UserService;
import com.openclassrooms.mddapi.services.interfaces.ICommentService;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final ICommentService commentService;
    private final UserService userService;
    private final ArticleService articleService;

    public CommentController(
        ICommentService commentService,
        UserService userService,
        ArticleService articleService
    ) {
        this.commentService = commentService;
        this.userService = userService;
        this.articleService = articleService;
    }

    @PostMapping("/add")
    public ResponseEntity<GenericResponse> addComment(@RequestBody CommentRequest request) {
        UserEntity currentUser = userService.getCurrentUser();
        ArticleEntity relatedArticle = this.articleService.getEntityById(request.getArticle_id()).orElse(null);

        CommentEntity commentToAdd = new CommentEntity()
            .setAuthor(currentUser)
            .setArticle(relatedArticle)
            .setContent(request.getContent());

        commentService.addComment(commentToAdd);
        return ResponseEntity.ok(new GenericResponse("Commentaire ajouté"));
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> findAllCommentByArticle(
            @RequestParam Integer articleId,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        Sort sortOrder = Sort.by(Sort.Order.by("createdAt").with(Sort.Direction.fromString(sort)));
        List<CommentDto> comments = commentService.getCommentsByArticleId(articleId, sortOrder);
        return ResponseEntity.ok(comments);
    }
}
