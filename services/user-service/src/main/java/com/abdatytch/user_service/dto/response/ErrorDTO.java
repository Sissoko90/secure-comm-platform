package com.abdatytch.user_service.dto.response;

import java.util.List;

/**
 * DTO pour les messages d'erreur en format JSON
 * 
 * Ce DTO standardise la structure des messages d'erreur retournés par l'API.
 * Il contient le code HTTP, le message d'erreur et une liste de détails.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-14
 */

public class ErrorDTO {
    private int status;
    private String message;
    private List<String> details;

    // Constructeur sans arguments
    public ErrorDTO() {
    }

    // Constructeur avec tous les arguments
    public ErrorDTO(int status, String message, List<String> details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    // Getters et Setters
    public int getStatus() {return status;}

    public void setStatus(int status) {this.status = status;}

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public List<String> getDetails() {return details;}

    public void setDetails(List<String> details) {this.details = details;}
}
