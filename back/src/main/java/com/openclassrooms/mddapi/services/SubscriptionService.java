package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Subscription;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
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

    public SubscriptionResponse convertToResponse(Subscription subscription) {
        SubscriptionResponse response = new SubscriptionResponse();
        response.setId(subscription.getId());
        response.setTheme_id(subscription.getTheme().getId());
        response.setUser_id(subscription.getUser().getId());
        response.setCreatedAt(subscription.getCreatedAt());
        return response;
    }

    public List<SubscriptionResponse> getCurrentUserSubscriptions() {
        User currentUser = userService.getCurrentUser();
        return subscriptionRepository.findByUserId(currentUser.getId()).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public Subscription subscribe(SubscriptionRequest request) {
        User currentUser = userService.getCurrentUser();
        Theme theme = themeService.findById(request.getTheme_id()).orElseThrow();
        Subscription subscription = new Subscription()
                .setUser(currentUser)
                .setTheme(theme);
        return subscriptionRepository.save(subscription);
    }

    public void unsubscribe(UnsubscriptionRequest request) {
        subscriptionRepository.findById(request.getId())
            .ifPresent(subscriptionRepository::delete);
    }
}
