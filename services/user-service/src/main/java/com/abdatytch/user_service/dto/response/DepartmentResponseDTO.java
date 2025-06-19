/**
 * DTO de réponse pour les départements
 * 
 * Ce DTO contient les informations détaillées d'un département, y compris
 * les informations de l'administration parente.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.dto.response;
import java.util.UUID;

public class DepartmentResponseDTO {

    // Identifiant unique du département
    private UUID id;

    private String name;

    private UUID administrationId;

    private String administrationName;

    // Constructeur sans arguments
    public DepartmentResponseDTO() {}

    // Getters et Setters
    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public UUID getAdministrationId() {return administrationId;}

    public void setAdministrationId(UUID administrationId) {this.administrationId = administrationId;}

    public String getAdministrationName() {return administrationName;}

    public void setAdministrationName(String administrationName) {this.administrationName = administrationName;}
}
