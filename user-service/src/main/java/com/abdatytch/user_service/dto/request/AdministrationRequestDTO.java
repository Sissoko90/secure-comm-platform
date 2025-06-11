/**
 * DTO de requête pour la création et mise à jour des administrations
 * 
 * Ce DTO contient les informations nécessaires pour créer ou mettre à jour
 * une administration dans le système.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.dto.request;

/**
 * DTO de requête pour la création et mise à jour des administrations
 * 
 * <p>
 * Ce DTO contient le champ suivant :
 * <ul>
 *     <li>name : Nom de l'administration (obligatoire)</li>
 * </ul>
 */
public class AdministrationRequestDTO {
    /**
     * Nom de l'administration (obligatoire)
     */
    private String name;

    /**
     * Constructeur par défaut
     */
    public AdministrationRequestDTO() {}

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
