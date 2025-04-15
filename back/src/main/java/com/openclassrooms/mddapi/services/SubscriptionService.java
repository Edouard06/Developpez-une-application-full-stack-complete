package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Subscription;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.SubscriptionRequest;
import com.openclassrooms.mddapi.payload.request.UnsubscriptionRequest;
import com.openclassrooms.mddapi.payload.response.SubscriptionResponse;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ThemeService themeService;

    private User currentUser;

    public SubscriptionResponse convertToResponse(Subscription subscription) {
        SubscriptionResponse response = new SubscriptionResponse();
        response.setId(subscription.getId());
        response.setTheme_id(subscription.getTheme().getId());
        response.setUser_id(subscription.getUser().getId());
        response.setCreatedAt(subscription.getCreatedAt());
        return response;
    }

    public List<SubscriptionResponse> getCurrentUserSubscriptions() {
        currentUser = this.userService.getCurrentUser();
        List<Subscription> subscriptions = this.subscriptionRepository.findByUserId(currentUser.getId());
        return subscriptions.stream()
                            .map(this::convertToResponse)
                            .collect(Collectors.toList());
    }

    public Subscription subscribe(SubscriptionRequest request) {
        currentUser = this.userService.getCurrentUser();
        Theme theme = this.themeService.findById(request.getTheme_id()).orElse(null);
        Subscription subscription = new Subscription()
            .setUser(currentUser)
            .setTheme(theme);
        return this.subscriptionRepository.save(subscription);
    }

    public void unsubscribe(UnsubscriptionRequest request) {
        Subscription subscription = this.subscriptionRepository.findById(request.getId()).orElse(null);
        if (subscription != null) {
            this.subscriptionRepository.delete(subscription);
        }
    }
}