/**
 * Énumération des rôles utilisateurs
 * 
 * Cette énumération définit les différents rôles possibles pour les utilisateurs
 * dans le système.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.model;
import io.swagger.v3.oas.annotations.media.Schema;



@Schema(description = "Rôles disponibles pour un utilisateur")
public enum UserRole {

    //Rôle administrateur avec accès complet au système
    ADMIN,

    // Rôle gestionnaire avec droits de gestion des utilisateurs et départements
    MANAGER,

    USER
}
