/**
 * Configuration de Swagger/OpenAPI pour le microservice User-Service
 * 
 * Cette configuration fournit la documentation automatique de l'API via Swagger UI,
 * permettant aux développeurs d'explorer et de tester les endpoints de manière interactive.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration de Swagger/OpenAPI pour le microservice User-Service
 * 
 * <p>
 * Cette classe configure la documentation de l'API via Swagger/OpenAPI, permettant :
 * <ul>
 *     <li>Documentation interactive via Swagger UI</li>
 *     <li>Groupement des endpoints par domaine fonctionnel</li>
 *     <li>Versioning de l'API</li>
 * </ul>
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configuration de l'API OpenAPI principale
     * 
     * @return Configuration OpenAPI avec les métadonnées de l'API
     */
    @Bean
    public OpenAPI userServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("User Service API")
                .description("API pour la gestion des utilisateurs, administrations et départements")
                .version("v1.0"));
    }

    /**
     * Configuration du groupage des endpoints Swagger
     * 
     * @return Configuration du groupage des endpoints sous le chemin /api/**
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch("/api/**")
                .build();
    }
}
