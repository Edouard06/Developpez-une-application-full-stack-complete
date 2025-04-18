package com.openclassrooms.mddapi.services;
/**
 * Service class responsible for business logic related to articles.
 * Handles creation, retrieval and mapping to DTOs.
 */


import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.ArticleEntity;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.services.interfaces.IArticleService;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ArticleDto createArticleFromRequest(ArticleEntity article) {
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
    

    public ArticleEntity getEntityById(Integer id) {
        return this.articleRepository.findById(id).orElse(null);
    }
}
