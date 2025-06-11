/**
 * Interface du service Administration
 * 
 * Cette interface définit les opérations de gestion des administrations dans le système.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.service;

import com.abdatytch.user_service.dto.request.AdministrationRequestDTO;
import com.abdatytch.user_service.dto.response.AdministrationResponseDTO;
import java.util.List;

/**
 * Interface du service Administration
 * 
 * <p>
 * Cette interface définit les opérations suivantes :
 * <ul>
 *     <li>CRUD complet des administrations</li>
 *     <li>Recherche par nom</li>
 * </ul>
 */
public interface AdministrationService {
    /**
     * Crée une nouvelle administration
     * 
     * @param administrationRequestDTO Détails de la nouvelle administration
     * @return Représentation de l'administration créée
     */
    AdministrationResponseDTO createAdministration(AdministrationRequestDTO administrationRequestDTO);

    /**
     * Met à jour une administration existante
     * 
     * @param administrationId Identifiant de l'administration à modifier
     * @param administrationRequestDTO Nouveaux détails de l'administration
     * @return Représentation de l'administration mise à jour
     */
    AdministrationResponseDTO updateAdministration(Long administrationId, AdministrationRequestDTO administrationRequestDTO);

    /**
     * Supprime une administration
     * 
     * @param administrationId Identifiant de l'administration à supprimer
     */
    void deleteAdministration(Long administrationId);

    /**
     * Récupère la liste de toutes les administrations
     * 
     * @return Liste des administrations
     */
    List<AdministrationResponseDTO> getAllAdministrations();

    /**
     * Recherche une administration par nom
     * 
     * @param name Nom de l'administration
     * @return Administration correspondante ou null si non trouvée
     */
    AdministrationResponseDTO getAdministrationByName(String name);
}
