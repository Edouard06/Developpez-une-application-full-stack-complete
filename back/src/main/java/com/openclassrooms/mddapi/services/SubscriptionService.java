package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.SubscriptionEntity;
import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.models.UserEntity;
import com.openclassrooms.mddapi.payload.request.SubscriptionRequest;
import com.openclassrooms.mddapi.payload.request.UnsubscriptionRequest;
import com.openclassrooms.mddapi.payload.response.SubscriptionResponse;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private final ThemeService themeService;

    public SubscriptionService(
        SubscriptionRepository subscriptionRepository,
        UserService userService,
        ThemeService themeService
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
        this.themeService = themeService;
    }

    public SubscriptionResponse convertToResponse(SubscriptionEntity subscription) {
        SubscriptionResponse response = new SubscriptionResponse();
        response.setId(subscription.getId());
        response.setTheme_id(subscription.getTheme().getId());
        response.setUser_id(subscription.getUser().getId());
        response.setCreatedAt(subscription.getCreatedAt());
        return response;
    }

    public List<SubscriptionResponse> getCurrentUserSubscriptions() {
        UserEntity currentUser = this.userService.getCurrentUser();
        List<SubscriptionEntity> subscriptions = this.subscriptionRepository.findByUserId(currentUser.getId());
        return subscriptions.stream()
                            .map(this::convertToResponse)
                            .collect(Collectors.toList());
    }

    public SubscriptionEntity subscribe(SubscriptionRequest request) {
        UserEntity currentUser = this.userService.getCurrentUser();
        ThemeEntity theme = this.themeService.findById(request.getTheme_id()).orElse(null);
        SubscriptionEntity subscription = new SubscriptionEntity()
            .setUser(currentUser)
            .setTheme(theme);
        return this.subscriptionRepository.save(subscription);
    }

    public void unsubscribe(UnsubscriptionRequest request) {
        SubscriptionEntity subscription = this.subscriptionRepository.findById(request.getId()).orElse(null);
        if (subscription != null) {
            this.subscriptionRepository.delete(subscription);
        }
    }
}