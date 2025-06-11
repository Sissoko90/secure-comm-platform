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

/**
 * DTO de réponse pour les administrations
 * 
 * <p>
 * Ce DTO contient les champs suivants :
 * <ul>
 *     <li>id : Identifiant unique de l'administration</li>
 *     <li>name : Nom de l'administration</li>
 * </ul>
 */
public class AdministrationResponseDTO {
    /**
     * Identifiant unique de l'administration
     */
    private Long id;

    /**
     * Nom de l'administration
     */
    private String name;

    /**
     * Constructeur par défaut
     */
    public AdministrationResponseDTO() {}

    /**
     * Obtient l'identifiant de l'administration
     * @return Identifiant de l'administration
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'administration
     * @param id Nouvel identifiant de l'administration
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtient le nom de l'administration
     * @return Nom de l'administration
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de l'administration
     * @param name Nouveau nom de l'administration
     */
    public void setName(String name) {
        this.name = name;
    }
}
