package com.openclassrooms.mddapi.services;
/**
 * Service class responsible for business logic related to articles.
 * Handles creation, retrieval and mapping to DTOs.
 */


import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.ArticleEntity;
import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.models.UserEntity;
import com.openclassrooms.mddapi.payload.request.ArticleRequest;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.services.interfaces.IArticleService;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final SubscriptionService subscriptionService;

    public ArticleService(
        ArticleRepository articleRepository,
        ArticleMapper articleMapper,
        SubscriptionService subscriptionService
    ) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.subscriptionService = subscriptionService;
    }

   @Override
public ArticleDto createArticleFromRequest(ArticleRequest request, UserEntity author, ThemeEntity theme) {
    ArticleEntity article = new ArticleEntity()
            .setAuthor(author)
            .setTheme(theme)
            .setTitle(request.getTitle())
            .setContent(request.getContent());

    ArticleEntity saved = this.articleRepository.save(article);
    return articleMapper.toDto(saved);
}


    @Override
    public ArticleDto findById(Integer id) {
        return this.articleRepository.findById(id)
                .map(articleMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<ArticleDto> findAllArticlesByUserSubscriptions(Sort sort) {
        List<Integer> themeIds = subscriptionService.getCurrentUserSubscriptions()
                .stream()
                .map(subscription -> subscription.getThemeId()) 
                .toList();
    
        return this.articleRepository.findByThemeIdIn(themeIds, sort)
                .stream()
                .map(articleMapper::toDto)
                .toList();
    }
    

    public Optional<ArticleEntity> getEntityById(Integer id) {
        return this.articleRepository.findById(id);
    }
    
}
