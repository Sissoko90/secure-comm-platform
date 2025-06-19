package com.abdatytch.user_service.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception de base pour toutes les exceptions de l'API
 * 
 * Cette classe permet de standardiser la gestion des erreurs
 * en fournissant un code HTTP et des détails de l'erreur.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-14
 */
public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
    private final String[] details;

    /**
     * Constructeur avec message et code HTTP
     * 
     * @param status Code HTTP de l'erreur
     * @param message Message d'erreur
     * @param details Détails de l'erreur
     */
    public ApiException(HttpStatus status, String message, String... details) {
        super(message);
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public HttpStatus getStatus() {return status;}

    public String getMessage() {return message;}

    public String[] getDetails() {return details;}
}
