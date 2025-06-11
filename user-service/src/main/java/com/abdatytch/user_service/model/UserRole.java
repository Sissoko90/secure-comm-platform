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

/**
 * Énumération des rôles utilisateurs
 * 
 * <p>
 * Les rôles disponibles sont :
 * <ul>
 *     <li>ADMIN : Accès complet au système</li>
 *     <li>MANAGER : Gestion des utilisateurs et des départements</li>
 *     <li>USER : Accès standard au système</li>
 * </ul>
 */
public enum UserRole {
    /**
     * Rôle administrateur avec accès complet au système
     */
    ADMIN,

    /**
     * Rôle gestionnaire avec droits de gestion des utilisateurs et départements
     */
    MANAGER,

    /**
     * Rôle utilisateur standard
     */
    USER
}
