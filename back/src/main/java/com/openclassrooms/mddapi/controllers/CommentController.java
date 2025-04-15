package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.CommentRequest;
import com.openclassrooms.mddapi.payload.response.GenericResponse;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public GenericResponse addComment(@RequestBody CommentRequest request) {
        User currentUser = this.userService.getCurrentUser();
        Article relatedArticle = this.articleService.findById(request.getArticle_id());

        Comment commentToAdd = new Comment()
            .setAuthor(currentUser)
            .setArticle(relatedArticle)
            .setContent(request.getContent());

        this.commentService.addComment(commentToAdd);

        return new GenericResponse("Commentaire ajouté");
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