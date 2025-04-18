package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
/**
 * Data Transfer Object for representing a subscription relationship.
 * 
 * Used to map the link between a user and a theme they have subscribed to,
 * along with the timestamp of the subscription creation.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SubscriptionDto {

    private Integer id;
    private Integer userId;
    private Integer themeId;
    private LocalDateTime createdAt;
}
