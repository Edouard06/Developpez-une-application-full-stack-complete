package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.payload.request.SubscriptionRequest;
import com.openclassrooms.mddapi.payload.request.UnsubscriptionRequest;
import com.openclassrooms.mddapi.payload.response.GenericResponse;
import com.openclassrooms.mddapi.payload.response.SubscriptionResponse;
import com.openclassrooms.mddapi.services.SubscriptionService;

/**
 * Controller for managing user subscriptions.
 * Provides endpoints for retrieving current user subscriptions, subscribing to new themes, and unsubscribing from existing themes.
 */
@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/")
    public ResponseEntity<List<SubscriptionResponse>> getCurrentUserSubscriptions() {
        List<SubscriptionResponse> subscriptions = subscriptionService.getCurrentUserSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<GenericResponse> subscribe(@RequestBody SubscriptionRequest request) {
        try {
            subscriptionService.subscribe(request);
            return ResponseEntity.ok(new GenericResponse("Vous vous êtes abonné"));
        } catch (Exception e) {
            return ResponseEntity.ok(new GenericResponse("Erreur: " + e.getMessage()));
        }
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<GenericResponse> unsubscribe(@RequestBody UnsubscriptionRequest request) {
        try {
            subscriptionService.unsubscribe(request);
            return ResponseEntity.ok(new GenericResponse("Vous vous êtes désabonné"));
        } catch (Exception e) {
            return ResponseEntity.ok(new GenericResponse("Erreur: " + e.getMessage()));
        }
    }
}
