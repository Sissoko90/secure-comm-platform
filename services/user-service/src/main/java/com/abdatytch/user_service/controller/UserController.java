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

import java.util.UUID;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import com.abdatytch.user_service.dto.request.UserRequestDTO;
import com.abdatytch.user_service.dto.response.UserResponseDTO;
import com.abdatytch.user_service.dto.response.ErrorDTO;
import com.abdatytch.user_service.dto.CredentialsUpdateDTO;
import com.abdatytch.user_service.service.UserService;
import com.abdatytch.user_service.constant.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;



@Tag(name = "User Management", description = "CRUD et recherche d'utilisateurs")
@RestController
@Validated
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
    @Operation(summary = "Créer un utilisateur", 
                description = "Crée un nouvel utilisateur avec les informations fournies",
                responses = {
                    @ApiResponse(responseCode = "201", 
                                description = "Utilisateur créé avec succès",
                                content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "400", 
                                description = "Données utilisateur invalides ou utilisateur déjà existant",
                                content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la création de l'utilisateur",
                                content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
                })
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("X-Message", Message.USER_CREATED_SUCCESS)
                    .body(userResponseDTO);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    /**
     * Mise à jour d'un utilisateur existant
     * 
     * @param id Identifiant de l'utilisateur à modifier
     * @param userRequestDTO Nouveaux détails de l'utilisateur
     * @return Représentation de l'utilisateur mis à jour
     */
    @Operation(summary = "Mettre à jour un utilisateur", 
                description = "Mettre à jour un utilisateur avec les informations fournies",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Utilisateur mis à jour avec succès",
                                content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "400", 
                                description = "Données utilisateur invalides",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "404", 
                                description = "Utilisateur non trouvé",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la mise à jour de l'utilisateur",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
        return ResponseEntity.ok()
                .header("X-Message", Message.USER_UPDATED_SUCCESS)
                .body(updatedUser);
    }

    /**
     * Suppression d'un utilisateur
     * 
     * @param id Identifiant de l'utilisateur à supprimer
     */
    @Operation(summary = "Supprimer un utilisateur", 
                description = "Supprime un utilisateur avec l'identifiant donné",
                responses = {
                    @ApiResponse(responseCode = "204", 
                                description = "Utilisateur supprimé avec succès"),
                    @ApiResponse(responseCode = "404", 
                                description = "Utilisateur non trouvé",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la suppression de l'utilisateur",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent()
                .header("X-Message", Message.USER_DELETED_SUCCESS)
                .build();
    }

    /**
     * Récupération de tous les utilisateurs avec pagination
     * 
     * @param page Numéro de page (0-indexé)
     * @param size Taille de la page
     * @return Liste paginée des utilisateurs
     */
    @Operation(summary = "Récupérer tous les utilisateurs", 
                description = "Récupère tous les utilisateurs avec pagination",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Liste des utilisateurs récupérée avec succès",
                                content = @Content(schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la récupération des utilisateurs",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
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
    @Operation(summary = "Récupérer un utilisateur par son identifiant", 
                description = "Récupère un utilisateur par son identifiant",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Utilisateur trouvé",
                                content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "404", 
                                description = "Utilisateur non trouvé",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la récupération de l'utilisateur",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        UserResponseDTO user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Met à jour les identifiants d'un utilisateur
     *
     * @param id Identifiant de l'utilisateur
     * @param credentialsDTO Nouveaux identifiants
     * @return L'utilisateur mis à jour
     */
    @Operation(summary = "Mettre à jour les identifiants d'un utilisateur",
            description = "Met à jour le nom d'utilisateur et le mot de passe d'un utilisateur existant",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Identifiants mis à jour avec succès",
                        content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                @ApiResponse(responseCode = "400",
                        description = "Données invalides ou nom d'utilisateur déjà utilisé",
                        content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                @ApiResponse(responseCode = "404",
                        description = "Utilisateur non trouvé",
                        content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                @ApiResponse(responseCode = "500",
                        description = "Erreur serveur lors de la mise à jour des identifiants",
                        content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @PutMapping("/{id}/credentials")
    public ResponseEntity<UserResponseDTO> updateCredentials(
            @PathVariable UUID id,
            @Valid @RequestBody CredentialsUpdateDTO credentialsDTO) {
        UserResponseDTO updatedUser = userService.updateCredentials(id, credentialsDTO);
        return ResponseEntity.ok()
                .header("X-Message", Message.USER_CREDENTIALS_UPDATED_SUCCESS)
                .body(updatedUser);
    }

    /**
     * Liste des utilisateurs d'une administration
     * 
     * @param administrationId Identifiant de l'administration
     * @param page Numéro de page (0-indexé)
     * @param size Taille de la page
     * @return Liste paginée des utilisateurs de l'administration
     */
    @Operation(summary = "Liste des utilisateurs d'une administration", 
                description = "Récupère la liste des utilisateurs d'une administration avec pagination",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Liste des utilisateurs de l'administration récupérée avec succès",
                                content = @Content(schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "404", 
                                description = "Administration non trouvée",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la récupération des utilisateurs de l'administration",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @GetMapping("/administration/{administrationId}")
    public ResponseEntity<Page<UserResponseDTO>> getUsersByAdministration(
            @PathVariable UUID administrationId,
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
    @Operation(summary = "Liste des utilisateurs d'un département", 
                description = "Récupère la liste des utilisateurs d'un département avec pagination",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Liste des utilisateurs du département récupérée avec succès",
                                content = @Content(schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "404", 
                                description = "Département non trouvé",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la récupération des utilisateurs du département",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<Page<UserResponseDTO>> getUsersByDepartment(
            @PathVariable UUID departmentId,
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
    @Operation(summary = "Recherche avancée d'utilisateurs", 
                description = "Recherche avancée d'utilisateurs avec pagination",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Résultats de la recherche d'utilisateurs",
                                content = @Content(schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "400", 
                                description = "Paramètres de recherche invalides",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la recherche d'utilisateurs",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @GetMapping("/search")
    public ResponseEntity<Page<UserResponseDTO>> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) UUID administrationId,
            @RequestParam(required = false) UUID departmentId,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResponseDTO> users = userService.searchUsers(username, administrationId, departmentId, role, pageable);
        return ResponseEntity.ok(users);
    }
}
