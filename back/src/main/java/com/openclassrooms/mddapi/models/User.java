package com.openclassrooms.mddapi.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
 * Represents a user in the system. The User entity includes basic user information
 * such as username, email, password, and timestamps for creation and last update.
 * It also manages relationships with articles, comments, and subscriptions.
 * This class implements {@link UserDetails} for Spring Security integration.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USERS")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The username of the user. This field must not be null and can be up to 50 characters long.
     */
    @NotNull
    @Size(max = 50)
    private String username;

    /**
     * The email of the user. This field must be unique, not null, and can be up to 100 characters long.
     */
    @Column(unique = true, length = 100, nullable = false)
    private String email;

    /**
     * The password of the user. This field must not be null.
     */
    @NotNull
    private String password;

    /**
     * Date and time when the user was created. This field is automatically set by the system
     * and is not updatable.
     */
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Date and time when the user was last updated. This field is automatically managed by the system.
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * List of articles authored by this user. The relationship is bidirectional with the Article entity.
     * Articles are fetched lazily and are automatically removed if the user is deleted.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference
    private Set<Article> articles = new HashSet<>();

    /**
     * List of comments made by this user. The relationship is bidirectional with the Comment entity.
     * Comments are fetched lazily and are automatically removed if the user is deleted.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference
    private Set<Comment> comments = new HashSet<>();

    /**
     * List of subscriptions made by this user. The relationship is bidirectional with the Subscription entity.
     * Subscriptions are fetched lazily and are automatically removed if the user is deleted.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference
    private Set<Subscription> subscriptions = new HashSet<>();

    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     * Returns the display name of the user, which is the username.
     * 
     * @return the username of the user.
     */
    public String getDisplayUsername() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // No authorities are set for this user
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        // The account is always considered non-expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // The account is always considered non-locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // The credentials are always considered non-expired
        return true;
    }

    @Override
    public boolean isEnabled() {
        // The account is always considered enabled
        return true;
    }
}