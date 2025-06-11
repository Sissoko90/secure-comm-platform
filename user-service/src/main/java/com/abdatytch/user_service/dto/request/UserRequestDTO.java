/**
 * DTO de requête pour la création et la mise à jour d'un utilisateur
 * 
 * Ce DTO encapsule les informations nécessaires pour créer ou mettre à jour un utilisateur,
 * y compris ses informations personnelles et son affiliation organisationnelle.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.dto.request;

import com.abdatytch.user_service.model.UserRole;
import lombok.Data;

/**
 * DTO de requête pour la création et la mise à jour d'un utilisateur
 * 
 * <p>
 * Ce DTO contient les champs suivants :
 * <ul>
 *     <li>Informations d'identification (username)</li>
 *     <li>Informations personnelles (fullName)</li>
 *     <li>Informations de rôle et d'organisation</li>
 * </ul>
 */
@Data
public class UserRequestDTO {
    /**
     * Nom d'utilisateur unique
     * 
     * @constraint Non-null et unique dans le système
     */
    private String username;

    /**
     * Nom complet de l'utilisateur
     * 
     * @constraint Non-null
     */
    private String fullName;

    /**
     * Rôle de l'utilisateur dans le système
     * 
     * @constraint Non-null
     */
    private UserRole role;

    /**
     * Identifiant de l'administration à laquelle l'utilisateur appartient
     * 
     * @constraint Non-null
     */
    private Long administrationId;

    /**
     * Identifiant du département à laquelle l'utilisateur appartient
     * 
     * @constraint Non-null
     */
    private Long departmentId;
}
