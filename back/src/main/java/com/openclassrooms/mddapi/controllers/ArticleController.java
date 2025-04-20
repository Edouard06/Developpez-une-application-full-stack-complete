package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.models.UserEntity;
import com.openclassrooms.mddapi.payload.request.ArticleRequest;
import com.openclassrooms.mddapi.services.ThemeService;
import com.openclassrooms.mddapi.services.UserService;
import com.openclassrooms.mddapi.services.interfaces.IArticleService;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final IArticleService articleService;
    private final ThemeService themeService;
    private final UserService userService;

    public ArticleController(
        IArticleService articleService,
        ThemeService themeService,
        UserService userService
    ) {
        this.articleService = articleService;
        this.themeService = themeService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<ArticleDto> createArticle(@RequestBody ArticleRequest request) {
        UserEntity currentUser = this.userService.getCurrentUser();
        ThemeEntity relatedTheme = this.themeService.getEntityById(request.getTheme_id()).orElse(null);

        ArticleDto createdArticle = this.articleService.createArticleFromRequest(request, currentUser, relatedTheme);

        return ResponseEntity.ok(createdArticle);
    }

    @GetMapping
    public ResponseEntity<List<ArticleDto>> findAllArticleFromSubscribedThemes(
            @RequestParam(defaultValue = "desc") String sort
    ) {
        Sort sortOrder = Sort.by(Sort.Order.by("createdAt").with(Sort.Direction.fromString(sort)));
        List<ArticleDto> articles = this.articleService.findAllArticlesByUserSubscriptions(sortOrder);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> findById(@PathVariable("id") String id) {
        try {
            Integer articleId = Integer.parseInt(id);
            ArticleDto article = this.articleService.findById(articleId);
            return article != null ? ResponseEntity.ok(article) : ResponseEntity.notFound().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
