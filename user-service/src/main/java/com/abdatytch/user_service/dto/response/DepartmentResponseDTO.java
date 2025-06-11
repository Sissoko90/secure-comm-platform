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

/**
 * DTO de réponse pour les départements
 * 
 * <p>
 * Ce DTO contient les champs suivants :
 * <ul>
 *     <li>id : Identifiant unique du département</li>
 *     <li>name : Nom du département</li>
 *     <li>administrationId : Identifiant de l'administration parente</li>
 *     <li>administrationName : Nom de l'administration parente</li>
 * </ul>
 */
public class DepartmentResponseDTO {
    /**
     * Identifiant unique du département
     */
    private Long id;

    /**
     * Nom du département
     */
    private String name;

    /**
     * Identifiant de l'administration parente
     */
    private Long administrationId;

    /**
     * Nom de l'administration parente
     */
    private String administrationName;

    /**
     * Constructeur par défaut
     */
    public DepartmentResponseDTO() {}

    /**
     * Obtient l'identifiant du département
     * @return Identifiant du département
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du département
     * @param id Nouvel identifiant du département
     */
    public void setId(Long id) {
        this.id = id;
    }

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

    /**
     * Obtient le nom de l'administration parente
     * @return Nom de l'administration
     */
    public String getAdministrationName() {
        return administrationName;
    }

    /**
     * Définit le nom de l'administration parente
     * @param administrationName Nouveau nom de l'administration
     */
    public void setAdministrationName(String administrationName) {
        this.administrationName = administrationName;
    }
}
