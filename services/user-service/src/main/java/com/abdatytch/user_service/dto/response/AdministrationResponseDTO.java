/**
 * DTO de réponse pour les administrations
 * 
 * Ce DTO contient les informations détaillées d'une administration.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.dto.response;
import java.util.UUID;


public class AdministrationResponseDTO {

    // Identifiant unique de l'administration
    private UUID id;

    // Nom de l'administration
    private String name;

    // Constructeur sans arguments
    public AdministrationResponseDTO() {}

    // Getters et Setters
    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
