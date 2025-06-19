/**
 * DTO de requête pour la création et mise à jour d'une administration.
 * Contient les informations nécessaires pour gérer une administration dans le système.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */

package com.abdatytch.user_service.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import com.abdatytch.user_service.constant.Message;

public class AdministrationRequestDTO {
    /**
     * Nom de l'administration (obligatoire)
     */
    @NotNull(message = Message.ADMINISTRATION_NAME_INVALID)
    @NotBlank(message = Message.ADMINISTRATION_NAME_REQUIRED)
    @Size(min = 2, max = 100, message = Message.ADMINISTRATION_NAME_TOO_SHORT + " et " + Message.ADMINISTRATION_NAME_TOO_LONG)
    @Pattern(regexp = "^[\\p{L}\\s-]+$", message = Message.ADMINISTRATION_NAME_INVALID)
    private String name;

    // Constructeur sans arguments
    public AdministrationRequestDTO() {}

    // Getters et Setters
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
