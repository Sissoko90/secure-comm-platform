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

/**
 * DTO de requête pour la création et mise à jour des départements
 * 
 * <p>
 * Ce DTO contient les champs suivants :
 * <ul>
 *     <li>name : Nom du département (obligatoire)</li>
 *     <li>administrationId : Identifiant de l'administration parente (obligatoire)</li>
 * </ul>
 */
public class DepartmentRequestDTO {
    /**
     * Nom du département (obligatoire)
     */
    private String name;

    /**
     * Identifiant de l'administration parente (obligatoire)
     */
    private Long administrationId;

    /**
     * Constructeur par défaut
     */
    public DepartmentRequestDTO() {}

    /**
     * Obtient le nom du département
     * @return Nom du département
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du département
     * @param name Nouveau nom du département
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtient l'identifiant de l'administration parente
     * @return Identifiant de l'administration
     */
    public Long getAdministrationId() {
        return administrationId;
    }

    /**
     * Définit l'identifiant de l'administration parente
     * @param administrationId Nouvel identifiant de l'administration
     */
    public void setAdministrationId(Long administrationId) {
        this.administrationId = administrationId;
    }
}
