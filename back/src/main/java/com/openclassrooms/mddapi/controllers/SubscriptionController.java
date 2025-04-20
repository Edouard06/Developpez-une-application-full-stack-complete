package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.payload.request.SubscriptionRequest;
import com.openclassrooms.mddapi.payload.request.UnsubscriptionRequest;
import com.openclassrooms.mddapi.payload.response.GenericResponse;
import com.openclassrooms.mddapi.services.interfaces.ISubscriptionService;

/**
 * Controller for managing user subscriptions.
 * Provides endpoints for retrieving current user subscriptions, subscribing to new themes, and unsubscribing from existing themes.
 */
@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {

    private final ISubscriptionService subscriptionService;

    public SubscriptionController(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/")
    public ResponseEntity<List<SubscriptionDto>> getCurrentUserSubscriptions() {
        List<SubscriptionDto> subscriptions = subscriptionService.getCurrentUserSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<GenericResponse> subscribe(@RequestBody SubscriptionRequest request) {
        boolean success = subscriptionService.subscribe(request);
        if (success) {
            return ResponseEntity.ok(new GenericResponse("Vous vous êtes abonné"));
        } else {
            return ResponseEntity.badRequest().body(new GenericResponse("Erreur : Thème introuvable"));
        }
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<GenericResponse> unsubscribe(@RequestBody UnsubscriptionRequest request) {
        boolean success = subscriptionService.unsubscribe(request);
        if (success) {
            return ResponseEntity.ok(new GenericResponse("Vous vous êtes désabonné"));
        } else {
            return ResponseEntity.status(404).body(new GenericResponse("Erreur : Abonnement introuvable"));
        }
    }
}

