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
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    /**
     * Recherche des utilisateurs par administration
     * 
     * @param administrationId Identifiant de l'administration
     * @param pageable Paramètres de pagination
     * @return Page d'utilisateurs
     */
    Page<User> findByAdministrationId(UUID administrationId, Pageable pageable);

    /**
     * Recherche des utilisateurs par département
     * 
     * @param departmentId Identifiant du département
     * @param pageable Paramètres de pagination
     * @return Page d'utilisateurs
     */
    Page<User> findByDepartmentId(UUID departmentId, Pageable pageable);

    /**
     * Vérifie si un email existe déjà
     * @param email Email à vérifier
     * @return true si l'email existe déjà
     */
    boolean existsByEmail(String email);

    /**
     * Vérifie si un username existe déjà
     * @param username Username à vérifier
     * @return true si le username existe déjà
     */
    boolean existsByUsername(String username);

    /**
     * Vérifie si un numéro de téléphone existe déjà
     * @param phoneNumber Numéro de téléphone à vérifier
     * @return true si le numéro existe déjà
     */
    boolean existsByPhoneNumber(String phoneNumber);

    /**
     * Vérifie si un numéro matricule existe déjà
     * @param matriculeNumber Numéro matricule à vérifier
     * @return true si le numéro matricule existe déjà
     */
    boolean existsByMatriculeNumber(String matriculeNumber);
    Optional<User> findByUsername(String username);

}
