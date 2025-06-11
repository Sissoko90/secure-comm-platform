/**
 * Interface du service métier pour la gestion des utilisateurs
 * 
 * Cette interface définit les opérations de base pour la gestion des utilisateurs,
 * y compris la recherche et le filtrage par administration et département.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.service;

import com.abdatytch.user_service.dto.request.UserRequestDTO;
import com.abdatytch.user_service.dto.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface du service métier pour la gestion des utilisateurs
 * 
 * <p>
 * Cette interface définit les opérations suivantes :
 * <ul>
 *     <li>CRUD complet des utilisateurs</li>
 *     <li>Recherche et filtrage</li>
 *     <li>Organisation par administration et département</li>
 * </ul>
 */
public interface UserService {
    /**
     * Crée un nouvel utilisateur
     * 
     * @param userRequestDTO Détails du nouvel utilisateur
     * @return Représentation du nouvel utilisateur créé
     */
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    /**
     * Met à jour un utilisateur existant
     * 
     * @param userId Identifiant de l'utilisateur à modifier
     * @param userRequestDTO Nouveaux détails de l'utilisateur
     * @return Représentation de l'utilisateur mis à jour
     */
    UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO);

    /**
     * Supprime un utilisateur
     * 
     * @param userId Identifiant de l'utilisateur à supprimer
     */
    void deleteUser(Long userId);

    /**
     * Récupère la liste des utilisateurs d'une administration
     * 
     * @param administrationId Identifiant de l'administration
     * @param pageable Paramètres de pagination
     * @return Page d'utilisateurs
     */
    Page<UserResponseDTO> getUsersByAdministration(Long administrationId, Pageable pageable);

    /**
     * Récupère la liste des utilisateurs d'un département
     * 
     * @param departmentId Identifiant du département
     * @param pageable Paramètres de pagination
     * @return Page d'utilisateurs
     */
    Page<UserResponseDTO> getUsersByDepartment(Long departmentId, Pageable pageable);

    /**
     * Récupère un utilisateur par son identifiant
     * 
     * @param id Identifiant de l'utilisateur
     * @return Représentation de l'utilisateur
     */
    UserResponseDTO getUserById(Long id);

    /**
     * Récupère tous les utilisateurs avec pagination
     * 
     * @param pageable Paramètres de pagination
     * @return Page d'utilisateurs
     */
    Page<UserResponseDTO> getAllUsers(Pageable pageable);

    /**
     * Recherche avancée d'utilisateurs avec filtres combinés
     * 
     * @param username Nom d'utilisateur (optionnel)
     * @param administrationId Identifiant d'administration (optionnel)
     * @param departmentId Identifiant de département (optionnel)
     * @param role Rôle de l'utilisateur (optionnel)
     * @param pageable Paramètres de pagination
     * @return Page d'utilisateurs correspondant aux critères
     */
    Page<UserResponseDTO> searchUsers(String username, Long administrationId, Long departmentId, String role, Pageable pageable);
}
