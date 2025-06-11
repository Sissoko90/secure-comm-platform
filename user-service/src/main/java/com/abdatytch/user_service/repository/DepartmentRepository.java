/**
 * Repository JPA pour la gestion des départements
 * 
 * Ce repository fournit les opérations de base CRUD sur les départements,
 * ainsi que des méthodes de recherche spécifiques.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.repository;

import com.abdatytch.user_service.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository JPA pour la gestion des départements
 * 
 * <p>
 * Ce repository étend JpaRepository et fournit les opérations suivantes :
 * <ul>
 *     <li>CRUD complet via JpaRepository</li>
 *     <li>Recherche par nom</li>
 * </ul>
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    /**
     * Recherche un département par nom
     * 
     * @param name Nom du département à rechercher
     * @return Département correspondant ou Optional.empty() si non trouvé
     */
    Optional<Department> findByName(String name);
}
