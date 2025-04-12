package com.openclassrooms.mddapi.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
 * Represents a theme in the system. A theme can be associated with multiple articles
 * and subscriptions. It includes metadata about the theme such as its name, description,
 * and timestamps for creation and last update.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "THEMES")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Theme {

    /**
     * Unique identifier for the theme.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name of the theme. This field must not be blank and can be up to 100 characters long.
     */
    @NotBlank
    @Size(max = 100)
    private String name;

    /**
     * Description of the theme. This field must not be null and can be up to 2500 characters long.
     */
    @NotNull
    @Size(max = 2500)
    private String description;

    /**
     * Date and time when the theme was created. This field is automatically set by the system
     * and is not updatable.
     */
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Date and time when the theme was last updated. This field is automatically managed by the system.
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * List of articles associated with this theme. The relationship is bidirectional with the Article entity.
     * Articles are fetched lazily and are automatically removed if the theme is deleted.
     */
    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference
    private Set<Article> articles = new HashSet<>();

    /**
     * List of subscriptions related to this theme. The relationship is bidirectional with the Subscription entity.
     * Subscriptions are fetched lazily and are automatically removed if the theme is deleted.
     */
    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference
    private Set<Subscription> subscriptions = new HashSet<>();
}