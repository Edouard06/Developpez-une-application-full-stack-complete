package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * Data Transfer Object representing an article.
 * 
 * This DTO is used to transfer article-related data between the backend and frontend,
 * including metadata such as creation and update timestamps, author and theme references.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ArticleDto {

    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer themeId;
    private Integer authorId;
}