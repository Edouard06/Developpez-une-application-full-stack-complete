package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * Data Transfer Object representing a theme.
 * 
 * Used to expose the theme metadata including its name and description,
 * primarily for frontend display and subscription handling.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ThemeDto {
    private Integer id;
    private String name;
    private String description;
}
