package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.models.SubscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
/**
 * Mapper interface for converting between SubscriptionEntity and SubscriptionDto.
 * 
 * Used to abstract the internal subscription model from external data consumers, 
 * typically in API responses.
 */
@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "theme.id", target = "themeId")
    SubscriptionDto toDto(SubscriptionEntity entity);
}
