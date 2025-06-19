package com.abdatytch.user_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server localServer = new Server()
            .url("http://localhost:8090")
            .description("Serveur de développement local");

        return new OpenAPI()
            .info(new Info()
                .title("User Service API")
                .version("1.0")
                .description("API de gestion des utilisateurs pour le système de communication gouvernementale sécurisée\n\n" +
                           "Cette API permet de :\n" +
                           "* Gérer les utilisateurs (CRUD)\n" +
                           "* Gérer les administrations\n" +
                           "* Gérer les départements\n" +
                           "* Authentifier les utilisateurs\n\n" +
                           "Toutes les requêtes nécessitent une authentification sauf /register et /login")
                .contact(new Contact()
                    .name("Makan Sissoko")
                    .email("makenzyks6@gmail.com"))
                .license(new License()
                    .name("Abdaty Technologie")
                    .url("http://www.abdatytch.com/License/Gouv-Abdaty/License.1.0")))
            .servers(List.of(localServer))
            .components(new Components()
                .addSecuritySchemes("bearer-jwt", new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .description("Utiliser le token JWT obtenu via /login")));
    }
}
