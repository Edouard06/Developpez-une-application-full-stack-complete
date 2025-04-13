package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.ArticleRequest;
import com.openclassrooms.mddapi.payload.response.UserResponse;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.ThemeService;
import com.openclassrooms.mddapi.services.UserService;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ThemeService themeService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleMapper articleMapper;

    @GetMapping()
    public ResponseEntity<?> findAllArticleFromSubscribedThemes(
        @RequestParam(defaultValue = "desc") String sort
    ) {
        UserResponse currentUser = this.userService.getSafeCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        Sort sortOrder = Sort.by(Sort.Order.by("createdAt").with(Sort.Direction.fromString(sort)));

        List<ArticleDto> articles = this.articleService.findAllArticlesByUserSubscriptions(currentUser.getId(), sortOrder);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        try {
            ArticleDto article = this.articleService.findById(Integer.parseInt(id));

            return ResponseEntity.ok().body(article);
        }catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public Article createArticle(@RequestBody ArticleRequest request){
        System.out.println(request);
        
        User currentUser = this.userService.getCurrentUser();
        System.out.println(request.getTheme_id());
        Theme relatedTheme = this.themeService.findById(request.getTheme_id())
        .orElse(null);
        System.out.println(request.getTitle());
        
        Article article = new Article()
        .setAuthor(currentUser)
        .setTheme(relatedTheme)
        .setTitle(request.getTitle())
        .setContent(request.getContent());
        
        
        System.out.println(request.getContent());
        return this.articleService.create(article);
    }
}