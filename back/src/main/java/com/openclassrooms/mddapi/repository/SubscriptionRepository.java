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

    
    List<Subscription> findByUserId(Integer userId);
}