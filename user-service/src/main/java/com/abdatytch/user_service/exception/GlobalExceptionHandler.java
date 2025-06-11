/**
 * Gestionnaire global des exceptions pour le microservice User-Service
 * 
 * Ce gestionnaire centralise la gestion des erreurs et fournit des réponses
 * standardisées aux clients en cas d'erreur.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Gestionnaire global des exceptions pour le microservice User-Service
 * 
 * <p>
 * Ce gestionnaire gère les types d'exceptions suivants :
 * <ul>
 *     <li>EntityNotFoundException : Ressource non trouvée</li>
 *     <li>Exception : Erreurs générales</li>
 * </ul>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère les exceptions EntityNotFoundException
     * 
     * @param ex Exception levée
     * @param request Détails de la requête
     * @return Réponse HTTP avec le code 404 et les détails de l'erreur
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            LocalDateTime.now(), 
            HttpStatus.NOT_FOUND.value(), 
            "Not Found", 
            ex.getMessage(), 
            request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Gère les exceptions générales
     * 
     * @param ex Exception levée
     * @param request Détails de la requête
     * @return Réponse HTTP avec le code 500 et les détails de l'erreur
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            LocalDateTime.now(), 
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Internal Server Error", 
            ex.getMessage(), 
            request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Classe représentant une réponse d'erreur standardisée
     */
    public static class ErrorResponse {
        /**
         * Timestamp de l'erreur
         */
        private LocalDateTime timestamp;

        /**
         * Code HTTP de l'erreur
         */
        private int status;

        /**
         * Type d'erreur
         */
        private String error;

        /**
         * Message d'erreur détaillé
         */
        private String message;

        /**
         * Chemin de la requête
         */
        private String path;

        /**
         * Constructeur de la réponse d'erreur
         * 
         * @param timestamp Date et heure de l'erreur
         * @param status Code HTTP
         * @param error Type d'erreur
         * @param message Message d'erreur
         * @param path Chemin de la requête
         */
        public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.message = message;
            this.path = path;
        }

        /**
         * Obtient le timestamp de l'erreur
         * @return Timestamp
         */
        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        /**
         * Obtient le code HTTP de l'erreur
         * @return Code HTTP
         */
        public int getStatus() {
            return status;
        }

        /**
         * Obtient le type d'erreur
         * @return Type d'erreur
         */
        public String getError() {
            return error;
        }

        /**
         * Obtient le message d'erreur
         * @return Message d'erreur
         */
        public String getMessage() {
            return message;
        }

        /**
         * Obtient le chemin de la requête
         * @return Chemin de la requête
         */
        public String getPath() {
            return path;
        }
    }
}
