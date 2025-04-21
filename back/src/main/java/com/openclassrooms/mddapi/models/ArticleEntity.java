package com.openclassrooms.mddapi.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Represents an article published by a user on a specific theme.
 * 
 * Each article contains a title, content, author, and theme association.
 * It includes metadata for creation and update timestamps, and links to related comments.
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ARTICLES")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleEntity {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

  
    @NotBlank
    @Size(max = 100)
    private String title;

   
    @NotNull
    @Size(max = 2500)
    private String content;

    
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

   
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false)
    @JsonManagedReference
    private ThemeEntity theme;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", nullable = false)
    @JsonManagedReference
    private UserEntity author;

  
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference
    private Set<CommentEntity> comments = new HashSet<>();


    public ArticleEntity orElse(Object object) {
        throw new UnsupportedOperationException("Unimplemented method 'orElse'");
    }
}