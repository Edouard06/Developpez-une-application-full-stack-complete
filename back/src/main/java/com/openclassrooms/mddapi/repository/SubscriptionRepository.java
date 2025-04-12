package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Subscription;

/**
 * Repository interface for managing {@link Subscription} entities.
 * Provides methods to perform CRUD operations and custom queries related to subscriptions.
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    /**
     * Retrieves a list of {@link Subscription} entities associated with a specific user.
     * 
     * @param userId The ID of the user for whom subscriptions are to be retrieved.
     * @return A list of {@link Subscription} entities associated with the specified user ID.
     */
    List<Subscription> findByUserId(Integer userId);
}