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

import java.util.UUID;
import com.abdatytch.user_service.dto.request.UserRequestDTO;
import com.abdatytch.user_service.dto.response.UserResponseDTO;
import com.abdatytch.user_service.dto.CredentialsUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;


public interface UserService {
    /**
     * Crée un nouvel utilisateur
     * 
     * @param userRequestDTO Détails du nouvel utilisateur
     * @return Représentation du nouvel utilisateur créé
     * @throws ResponseStatusException si l'utilisateur existe déjà ou si les données sont invalides
     */
    UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws ResponseStatusException;

    /**
     * Met à jour un utilisateur existant
     * 
     * @param userId Identifiant de l'utilisateur à modifier
     * @param userRequestDTO Nouveaux détails de l'utilisateur
     * @return Représentation de l'utilisateur mis à jour
     * @throws ResponseStatusException si l'utilisateur n'existe pas ou si les données sont invalides
     */
    UserResponseDTO updateUser(UUID userId, UserRequestDTO userRequestDTO) throws ResponseStatusException;

    /**
     * Met à jour les identifiants d'un utilisateur
     * 
     * @param userId Identifiant de l'utilisateur
     * @param credentialsDTO Nouveaux identifiants
     * @return Utilisateur mis à jour
     * @throws ResponseStatusException si l'utilisateur n'existe pas ou si les identifiants sont invalides
     */
    UserResponseDTO updateCredentials(UUID userId, CredentialsUpdateDTO credentialsDTO) throws ResponseStatusException;

    /**
     * Supprime un utilisateur
     * 
     * @param userId Identifiant de l'utilisateur à supprimer
     */
    void deleteUser(UUID userId);

    /**
     * Récupère la liste des utilisateurs d'une administration
     * 
     * @param administrationId Identifiant de l'administration
     * @param pageable Paramètres de pagination
     * @return Page d'utilisateurs
     */
    Page<UserResponseDTO> getUsersByAdministration(UUID administrationId, Pageable pageable);

    /**
     * Récupère la liste des utilisateurs d'un département
     * 
     * @param departmentId Identifiant du département
     * @param pageable Paramètres de pagination
     * @return Page d'utilisateurs
     */
    Page<UserResponseDTO> getUsersByDepartment(UUID departmentId, Pageable pageable);

    /**
     * Récupère un utilisateur par son identifiant
     * 
     * @param id Identifiant de l'utilisateur
     * @return Représentation de l'utilisateur
     */
    UserResponseDTO getUserById(UUID userId);

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
    Page<UserResponseDTO> searchUsers(String username, UUID administrationId, UUID departmentId, String role, Pageable pageable);
}
