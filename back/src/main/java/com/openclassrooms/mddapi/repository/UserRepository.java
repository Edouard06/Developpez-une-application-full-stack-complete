package com.openclassrooms.mddapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.User;

/**
 * Repository interface for managing {@link User} entities.
 * Provides methods to perform CRUD operations and custom queries related to users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Retrieves a {@link User} entity based on the provided email address.
     * 
     * @param email The email address associated with the user.
     * @return An {@link Optional} containing the {@link User} if found, or an empty {@link Optional} if not.
     */
    Optional<User> findByEmail(String email);
}