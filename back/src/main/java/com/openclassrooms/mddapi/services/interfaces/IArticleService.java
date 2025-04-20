package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.ArticleEntity;
import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.models.UserEntity;
import com.openclassrooms.mddapi.payload.request.ArticleRequest;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface IArticleService {
    ArticleDto createArticleFromRequest(ArticleRequest request, UserEntity author, ThemeEntity theme);
    ArticleDto findById(Integer id);
    List<ArticleDto> findAllArticlesByUserSubscriptions(Sort sort);
}
