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
 * Represents an article in the system.
 * An article contains a title, content, creation and update timestamps, 
 * and is associated with a theme and an author. It also has a collection of comments.
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
public class Article {

    /**
     * Unique identifier for the article.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Title of the article.
     * Must not be blank and cannot exceed 100 characters.
     */
    @NotBlank
    @Size(max = 100)
    private String title;

    /**
     * Content of the article.
     * Must not be null and cannot exceed 2500 characters.
     */
    @NotNull
    @Size(max = 2500)
    private String content;

    /**
     * Date and time when the article was created.
     * This field is automatically set by the system and is not updatable.
     */
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Date and time when the article was last updated.
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * The theme associated with the article.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false)
    @JsonManagedReference
    private Theme theme;

    /**
     * The user who authored the article.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", nullable = false)
    @JsonManagedReference
    private User author;

    /**
     * Set of comments associated with the article.
     * Comments are automatically managed (added or removed) with the article.
     */
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference
    private Set<Comment> comments = new HashSet<>();
}