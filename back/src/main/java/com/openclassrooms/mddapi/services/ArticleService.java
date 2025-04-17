package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.ArticleEntity;
import com.openclassrooms.mddapi.payload.response.SubscriptionResponse;
import com.openclassrooms.mddapi.repository.ArticleRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

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
    public ArticleEntity create(ArticleEntity article) {
        return this.articleRepository.save(article);
    }

    public ArticleEntity findById(Integer id) {
        return this.articleRepository.findById(id).orElse(null);
    }

    public List<ArticleDto> findAllArticlesByUserSubscriptions(Sort sort) {
        List<SubscriptionResponse> subscriptions = this.subscriptionService.getCurrentUserSubscriptions();
        List<Integer> themeIds = subscriptions.stream()
                .map(SubscriptionResponse::getTheme_id)
                .toList();

        List<ArticleEntity> articles = this.articleRepository.findByThemeIdIn(themeIds, sort);
        return articles.stream()
                       .map(articleMapper::toDto)
                       .toList();
    }
}