/**
 * Contrôleur REST pour la gestion des utilisateurs
 * 
 * Ce contrôleur gère l'ensemble des opérations CRUD sur les utilisateurs,
 * y compris la recherche et le filtrage par administration et département.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.controller;

import com.abdatytch.user_service.dto.request.UserRequestDTO;
import com.abdatytch.user_service.dto.response.UserResponseDTO;
import com.abdatytch.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour la gestion des utilisateurs
 * 
 * <p>
 * Ce contrôleur expose les endpoints suivants :
 * <ul>
 *     <li>CRUD complet des utilisateurs</li>
 *     <li>Recherche et filtrage</li>
 *     <li>Organisation par administration et département</li>
 * </ul>
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * Service métier pour la gestion des utilisateurs
     */
    private final UserService userService;

    /**
     * Constructeur avec injection de dépendance
     * 
     * @param userService Service métier pour la gestion des utilisateurs
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Création d'un nouvel utilisateur
     * 
     * @param userRequestDTO Détails du nouvel utilisateur
     * @return Représentation du nouvel utilisateur créé
     */
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Mise à jour d'un utilisateur existant
     * 
     * @param id Identifiant de l'utilisateur à modifier
     * @param userRequestDTO Nouveaux détails de l'utilisateur
     * @return Représentation de l'utilisateur mis à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Suppression d'un utilisateur
     * 
     * @param id Identifiant de l'utilisateur à supprimer
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupération d'un utilisateur par son identifiant
     * 
     * @param id Identifiant de l'utilisateur
     * @return Représentation de l'utilisateur
     */
    /**
     * Récupération de tous les utilisateurs avec pagination
     * 
     * @param page Numéro de page (0-indexé)
     * @param size Taille de la page
     * @return Liste paginée des utilisateurs
     */
    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResponseDTO> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    /**
     * Récupération d'un utilisateur par son identifiant
     * 
     * @param id Identifiant de l'utilisateur
     * @return Représentation de l'utilisateur
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Liste des utilisateurs d'une administration
     * 
     * @param administrationId Identifiant de l'administration
     * @param page Numéro de page
     * @param size Taille de la page
     * @return Page d'utilisateurs
     */
    @GetMapping("/administration/{administrationId}")
    public ResponseEntity<Page<UserResponseDTO>> getUsersByAdministration(
            @PathVariable Long administrationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResponseDTO> users = userService.getUsersByAdministration(administrationId, pageable);
        return ResponseEntity.ok(users);
    }

    /**
     * Liste des utilisateurs d'un département
     * 
     * @param departmentId Identifiant du département
     * @param page Numéro de page
     * @param size Taille de la page
     * @return Page d'utilisateurs
     */
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<Page<UserResponseDTO>> getUsersByDepartment(
            @PathVariable Long departmentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResponseDTO> users = userService.getUsersByDepartment(departmentId, pageable);
        return ResponseEntity.ok(users);
    }

    /**
     * Recherche avancée d'utilisateurs
     * 
     * @param username Nom d'utilisateur (optionnel)
     * @param administrationId Identifiant d'administration (optionnel)
     * @param departmentId Identifiant de département (optionnel)
     * @param role Rôle de l'utilisateur (optionnel)
     * @param page Numéro de page
     * @param size Taille de la page
     * @return Page d'utilisateurs correspondant aux critères
     */
    @GetMapping("/search")
    public ResponseEntity<Page<UserResponseDTO>> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Long administrationId,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResponseDTO> users = userService.searchUsers(username, administrationId, departmentId, role, pageable);
        return ResponseEntity.ok(users);
    }
}
