package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.payload.response.SubscriptionResponse;
import com.openclassrooms.mddapi.repository.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private SubscriptionService subscriptionService;

    public Article create(Article article) {
        return this.articleRepository.save(article);
    }

    public Article findById(Integer id) {
        return this.articleRepository.findById(id).orElse(null);
    }

    public List<ArticleDto> findAllArticlesByUserSubscriptions(Sort sort) {
        List<SubscriptionResponse> subscriptions = this.subscriptionService.getCurrentUserSubscriptions();
        List<Integer> themeIds = subscriptions.stream()
                .map(SubscriptionResponse::getTheme_id)
                .collect(Collectors.toList());

        List<Article> articles = this.articleRepository.findByThemeIdIn(themeIds, sort);
        return articles.stream()
                       .map(articleMapper::toDto)
                       .collect(Collectors.toList());
    }
}