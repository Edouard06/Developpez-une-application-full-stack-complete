package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.ArticleEntity;
import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.models.UserEntity;
import com.openclassrooms.mddapi.payload.request.ArticleRequest;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.ThemeService;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;
    private final ThemeService themeService;
    private final UserService userService;
    private final ArticleMapper articleMapper;

    public ArticleController(
        ArticleService articleService,
        ThemeService themeService,
        UserService userService,
        ArticleMapper articleMapper
    ) {
        this.articleService = articleService;
        this.themeService = themeService;
        this.userService = userService;
        this.articleMapper = articleMapper;
    }

    @PostMapping("/create")
    public ArticleEntity createArticle(@RequestBody ArticleRequest request) {
        UserEntity currentUser = this.userService.getCurrentUser();
        ThemeEntity relatedTheme = this.themeService.findById(request.getTheme_id())
                .orElse(null);

        ArticleEntity article = new ArticleEntity()
                .setAuthor(currentUser)
                .setTheme(relatedTheme)
                .setTitle(request.getTitle())
                .setContent(request.getContent());

        return this.articleService.create(article);
    }

    @GetMapping
    public ResponseEntity<?> findAllArticleFromSubscribedThemes(
            @RequestParam(defaultValue = "desc") String sort
    ) {
        Sort sortOrder = Sort.by(Sort.Order.by("createdAt").with(Sort.Direction.fromString(sort)));

        List<ArticleDto> articles = this.articleService.findAllArticlesByUserSubscriptions(sortOrder);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            ArticleEntity article = this.articleService.findById(Integer.parseInt(id));
            return ResponseEntity.ok().body(articleMapper.toDto(article));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
