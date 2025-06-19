/**
 * Repository pour l'entité Administration
 * 
 * Ce repository fournit les opérations de base pour la gestion des administrations
 * dans la base de données.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.repository;

import com.abdatytch.user_service.model.Administration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdministrationRepository extends JpaRepository<Administration, UUID> {
    /**
     * Recherche une administration par nom
     * 
     * @param name Nom de l'administration à rechercher
     * @return Administration correspondante ou Optional.empty() si non trouvée
     */
    Optional<Administration> findByName(String name);
}
