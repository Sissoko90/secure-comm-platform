/**
 * DTO de réponse pour les utilisateurs
 * 
 * Ce DTO contient les informations détaillées d'un utilisateur, y compris
 * ses rôles et les informations de son administration et département.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.dto.response;

import com.abdatytch.user_service.model.UserRole;

/**
 * DTO de réponse pour les utilisateurs
 * 
 * <p>
 * Ce DTO contient les champs suivants :
 * <ul>
 *     <li>id : Identifiant unique de l'utilisateur</li>
 *     <li>username : Nom d'utilisateur (unique)</li>
 *     <li>fullName : Nom complet de l'utilisateur</li>
 *     <li>role : Rôle de l'utilisateur dans le système</li>
 *     <li>administrationId : Identifiant de l'administration</li>
 *     <li>administrationName : Nom de l'administration</li>
 *     <li>departmentId : Identifiant du département</li>
 *     <li>departmentName : Nom du département</li>
 * </ul>
 */
public class UserResponseDTO {
    /**
     * Identifiant unique de l'utilisateur
     */
    private Long id;

    /**
     * Nom d'utilisateur (unique)
     */
    private String username;

    /**
     * Nom complet de l'utilisateur
     */
    private String fullName;

    /**
     * Rôle de l'utilisateur dans le système
     */
    private UserRole role;

    /**
     * Identifiant de l'administration
     */
    private Long administrationId;

    /**
     * Nom de l'administration
     */
    private String administrationName;

    /**
     * Identifiant du département
     */
    private Long departmentId;

    /**
     * Nom du département
     */
    private String departmentName;

    /**
     * Constructeur par défaut
     */
    public UserResponseDTO() {}

    /**
     * Obtient l'identifiant de l'utilisateur
     * @return Identifiant de l'utilisateur
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'utilisateur
     * @param id Nouvel identifiant de l'utilisateur
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtient le nom d'utilisateur
     * @return Nom d'utilisateur
     */
    public String getUsername() {
        return username;
    }

    /**
     * Définit le nom d'utilisateur
     * @param username Nouveau nom d'utilisateur
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtient le nom complet de l'utilisateur
     * @return Nom complet de l'utilisateur
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Définit le nom complet de l'utilisateur
     * @param fullName Nouveau nom complet de l'utilisateur
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Obtient le rôle de l'utilisateur
     * @return Rôle de l'utilisateur
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Définit le rôle de l'utilisateur
     * @param role Nouveau rôle de l'utilisateur
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Obtient l'identifiant de l'administration
     * @return Identifiant de l'administration
     */
    public Long getAdministrationId() {
        return administrationId;
    }

    /**
     * Définit l'identifiant de l'administration
     * @param administrationId Nouvel identifiant de l'administration
     */
    public void setAdministrationId(Long administrationId) {
        this.administrationId = administrationId;
    }

    /**
     * Obtient le nom de l'administration
     * @return Nom de l'administration
     */
    public String getAdministrationName() {
        return administrationName;
    }

    /**
     * Définit le nom de l'administration
     * @param administrationName Nouveau nom de l'administration
     */
    public void setAdministrationName(String administrationName) {
        this.administrationName = administrationName;
    }

    /**
     * Obtient l'identifiant du département
     * @return Identifiant du département
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * Définit l'identifiant du département
     * @param departmentId Nouvel identifiant du département
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Obtient le nom du département
     * @return Nom du département
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Définit le nom du département
     * @param departmentName Nouveau nom du département
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
