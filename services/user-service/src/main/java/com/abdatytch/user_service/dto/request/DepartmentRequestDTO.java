/**
 * DTO de requête pour la création et mise à jour des départements
 * 
 * Ce DTO contient les informations nécessaires pour créer ou mettre à jour
 * un département dans le système.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.dto.request;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.abdatytch.user_service.constant.Message;

public class DepartmentRequestDTO {
    /**
     * Nom du département (obligatoire)
     */
    @NotBlank(message = Message.DEPARTMENT_NAME_REQUIRED)
    @Size(min = 2, max = 100, message = Message.DEPARTMENT_NAME_TOO_SHORT + " " + Message.DEPARTMENT_NAME_TOO_LONG)
    private String name;


    @NotNull(message = Message.ADMINISTRATION_ID_REQUIRED)
    private UUID administrationId;

    // Constructeur sans arguments
    public DepartmentRequestDTO() {}


    // Getters et Setters
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public UUID getAdministrationId() {return administrationId;}

    public void setAdministrationId(UUID administrationId) {this.administrationId = administrationId;}

}
