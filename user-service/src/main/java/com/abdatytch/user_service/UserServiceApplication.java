/**
 * Classe principale de démarrage du microservice User-Service
 * 
 * Cette application Spring Boot gère l'ensemble des opérations liées aux utilisateurs
 * dans le système de communication gouvernementale sécurisée.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Point d'entrée principal du microservice User-Service
 * 
 * <p>
 * Cette classe configure et démarre l'application Spring Boot avec les caractéristiques suivantes :
 * <ul>
 *     <li>Configuration automatique via Spring Boot</li>
 *     <li>Découverte de services via Eureka</li>
 *     <li>Initialisation du contexte Spring</li>
 * </ul>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

    /**
     * Point d'entrée principal de l'application
     * 
     * @param args Arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
