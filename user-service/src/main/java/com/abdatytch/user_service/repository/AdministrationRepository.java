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

/**
 * Repository pour l'entité Administration
 * 
 * <p>
 * Ce repository étend JpaRepository et fournit les opérations suivantes :
 * <ul>
 *     <li>CRUD complet via JpaRepository</li>
 *     <li>Recherche par nom</li>
 * </ul>
 */
@Repository
public interface AdministrationRepository extends JpaRepository<Administration, Long> {
    /**
     * Recherche une administration par nom
     * 
     * @param name Nom de l'administration à rechercher
     * @return Administration correspondante ou Optional.empty() si non trouvée
     */
    Optional<Administration> findByName(String name);
}
