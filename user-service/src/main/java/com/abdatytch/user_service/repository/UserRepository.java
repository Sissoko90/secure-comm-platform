/**
 * Repository JPA pour la gestion des utilisateurs
 * 
 * Ce repository fournit les opérations de base CRUD sur les utilisateurs,
 * ainsi que des méthodes de recherche spécifiques par administration et département.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.repository;

import com.abdatytch.user_service.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository JPA pour la gestion des utilisateurs
 * 
 * <p>
 * Ce repository étend JpaRepository et JpaSpecificationExecutor pour fournir :
 * <ul>
 *     <li>Opérations CRUD standards</li>
 *     <li>Requêtes paginées</li>
 *     <li>Recherche par administration et département</li>
 * </ul>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * Recherche des utilisateurs par administration
     * 
     * @param administrationId Identifiant de l'administration
     * @param pageable Paramètres de pagination
     * @return Page d'utilisateurs
     */
    Page<User> findByAdministrationId(Long administrationId, Pageable pageable);

    /**
     * Recherche des utilisateurs par département
     * 
     * @param departmentId Identifiant du département
     * @param pageable Paramètres de pagination
     * @return Page d'utilisateurs
     */
    Page<User> findByDepartmentId(Long departmentId, Pageable pageable);
}
