package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.mapper.SubscriptionMapper;
import com.openclassrooms.mddapi.models.SubscriptionEntity;
import com.openclassrooms.mddapi.models.ThemeEntity;
import com.openclassrooms.mddapi.models.UserEntity;
import com.openclassrooms.mddapi.payload.request.SubscriptionRequest;
import com.openclassrooms.mddapi.payload.request.UnsubscriptionRequest;
import com.openclassrooms.mddapi.payload.response.SubscriptionResponse;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.services.interfaces.ISubscriptionService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService implements ISubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private final ThemeService themeService;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionService(
        SubscriptionRepository subscriptionRepository,
        UserService userService,
        ThemeService themeService,
        SubscriptionMapper subscriptionMapper
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
        this.themeService = themeService;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public List<SubscriptionDto> getCurrentUserSubscriptions() {
        UserEntity currentUser = this.userService.getCurrentUser();
        return subscriptionRepository.findByUserId(currentUser.getId())
                .stream()
                .map(subscriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean subscribe(SubscriptionRequest request) {
        UserEntity currentUser = userService.getCurrentUser();
        ThemeEntity theme = themeService.getEntityById(request.getTheme_id()).orElse(null);
    
        if (theme == null) return false;
    
        boolean alreadySubscribed = subscriptionRepository.findByUserId(currentUser.getId())
                .stream()
                .anyMatch(s -> s.getTheme().getId().equals(theme.getId()));
    
        if (alreadySubscribed) return false;
    
        SubscriptionEntity subscription = new SubscriptionEntity()
            .setUser(currentUser)
            .setTheme(theme);
    
        subscriptionRepository.save(subscription);
        return true;
    }

    @Override
    public boolean unsubscribe(UnsubscriptionRequest request) {
        SubscriptionEntity subscription = subscriptionRepository.findById(request.getId()).orElse(null);
        if (subscription == null) return false;

        subscriptionRepository.delete(subscription);
        return true;
    }
}
