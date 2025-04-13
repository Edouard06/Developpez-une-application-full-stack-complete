package com.openclassrooms.mddapi.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.repository.ArticleRepository;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;


    public ArticleDto findById(Integer id){
        Article article = this.articleRepository.findById(id).orElse(null);
        return article != null ? articleMapper.toDto(article): null;
    }

    public List<ArticleDto> findAllArticlesByUserSubscriptions(Integer userId, Sort sort){
        List<Integer> themeIds = Arrays.asList(1, 2); // TODO: for tests, implements a solution based on subscriptions later

        List<Article> articles = this.articleRepository.findByThemeIdIn(themeIds, sort);
        return articles.stream()
                       .map(articleMapper::toDto)
                       .collect(Collectors.toList());
    }


    public Article create(Article article){
        return this.articleRepository.save(article);
    }

}