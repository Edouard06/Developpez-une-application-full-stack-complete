package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.ArticleEntity;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IArticleService {
    ArticleDto createArticleFromRequest(ArticleEntity article);
    ArticleDto findById(Integer id);
    List<ArticleDto> findAllArticlesByUserSubscriptions(Sort sort);
}
