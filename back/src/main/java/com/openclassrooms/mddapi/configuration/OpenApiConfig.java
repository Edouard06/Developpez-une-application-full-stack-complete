package com.openclassrooms.mddapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * This configuration class sets up Swagger/OpenAPI for documenting
 * and testing the REST API endpoints.
 *
 * It defines the API metadata and configures security using JWT tokens.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configures the OpenAPI bean including API information and
     * JWT bearer authentication scheme.
     *
     * @return a configured {@link OpenAPI} instance.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
            .info(new Info()
                .title("Projet 6 - MDD API")
                .description("Backend API pour Projet 6 d'OpenClassrooms, sécurisée avec JWT")
                .version("1.0")
                .contact(new Contact()
                    .name("Edouard")
                    .email("edouard@example.com")
                ));
    }

    /**
     * Creates a security scheme for JWT bearer authentication to
     * be used in the Swagger UI.
     *
     * @return A configured {@link SecurityScheme}.
     */
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer");
    }
}
