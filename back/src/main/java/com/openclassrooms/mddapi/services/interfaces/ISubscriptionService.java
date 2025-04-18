package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.payload.request.SubscriptionRequest;
import com.openclassrooms.mddapi.payload.request.UnsubscriptionRequest;

import java.util.List;

public interface ISubscriptionService {
    List<SubscriptionDto> getCurrentUserSubscriptions();
    boolean subscribe(SubscriptionRequest request);
    boolean unsubscribe(UnsubscriptionRequest request);
}
// This interface defines the contract for subscription-related operations.