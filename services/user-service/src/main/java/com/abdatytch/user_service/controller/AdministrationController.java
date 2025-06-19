/**
 * Contrôleur REST pour la gestion des administrations
 * 
 * Ce contrôleur gère les opérations CRUD sur les administrations et fournit
 * des endpoints pour la recherche et la gestion des organisations.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.controller;

import com.abdatytch.user_service.dto.request.AdministrationRequestDTO;
import com.abdatytch.user_service.dto.response.AdministrationResponseDTO;
import com.abdatytch.user_service.service.AdministrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import com.abdatytch.user_service.constant.Message;
import java.util.UUID;
import java.util.List;

/**
 * Contrôleur REST pour la gestion des administrations
 */
@Tag(name = "Administrations", description = "Gestion des administrations")
@RestController
@RequestMapping("/api/administrations")
public class AdministrationController {



    /**
     * Service métier pour la gestion des administrations
     */
    private final AdministrationService administrationService;

    /**
     * Constructeur avec injection de dépendance
     * 
     * @param administrationService Service métier pour la gestion des administrations
     */
    public AdministrationController(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    /**
     * Crée une nouvelle administration
     * 
     * @param administrationRequestDTO Détails de la nouvelle administration
     * @return Représentation de l'administration créée
     */
    @Operation(summary = "Créer une nouvelle administration", 
                description = "Crée une nouvelle administration",
                responses = {
                    @ApiResponse(responseCode = "201", 
                                description = "Administration créée avec succès",
                                content = @Content(schema = @Schema(implementation = AdministrationResponseDTO.class))),
                    @ApiResponse(responseCode = "400", 
                                description = "Données d'administration invalides ou administration déjà existante",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la création de l'administration",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @PostMapping
    public ResponseEntity<AdministrationResponseDTO> createAdministration(@RequestBody AdministrationRequestDTO administrationRequestDTO) {
        AdministrationResponseDTO created = administrationService.createAdministration(administrationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-Message", Message.ADMINISTRATION_CREATED_SUCCESS)
                .body(created);
    }

    /**
     * Met à jour une administration existante
     * 
     * @param id Identifiant de l'administration à modifier
     * @param administrationRequestDTO Nouveaux détails de l'administration
     * @return Représentation de l'administration mise à jour
     */
    @Operation(summary = "Mettre à jour une administration existante", 
                description = "Mettre à jour une administration existante",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Administration mise à jour avec succès",
                                content = @Content(schema = @Schema(implementation = AdministrationResponseDTO.class))),
                    @ApiResponse(responseCode = "400", 
                                description = "Données d'administration invalides",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "404", 
                                description = "Administration non trouvée",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la mise à jour de l'administration",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @PutMapping("/{id}")
    public ResponseEntity<AdministrationResponseDTO> updateAdministration(@PathVariable UUID id, @RequestBody AdministrationRequestDTO administrationRequestDTO) {
        AdministrationResponseDTO updated = administrationService.updateAdministration(id, administrationRequestDTO);
        return ResponseEntity.ok()
                .header("X-Message", Message.ADMINISTRATION_UPDATED_SUCCESS)
                .body(updated);
    }

    /**
     * Supprime une administration
     * 
     * @param id Identifiant de l'administration à supprimer
     */
    @Operation(summary = "Supprimer une administration", 
                description = "Supprime une administration",
                responses = {
                    @ApiResponse(responseCode = "204", 
                                description = "Administration supprimée avec succès"),
                    @ApiResponse(responseCode = "404", 
                                description = "Administration non trouvée",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la suppression de l'administration",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministration(@PathVariable UUID id) {
        administrationService.deleteAdministration(id);
        return ResponseEntity.noContent()
                .header("X-Message", Message.ADMINISTRATION_DELETED_SUCCESS)
                .build();
    }

    /**
     * Récupère la liste de toutes les administrations
     * 
     * @return Liste des administrations
     */
    @Operation(summary = "Récupérer toutes les administrations", 
                description = "Récupère toutes les administrations",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Liste des administrations récupérée avec succès",
                                content = @Content(schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la récupération des administrations",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @GetMapping
    public ResponseEntity<List<AdministrationResponseDTO>> getAllAdministrations() {
        List<AdministrationResponseDTO> administrations = administrationService.getAllAdministrations();
        return ResponseEntity.ok(administrations);
    }

    /**
     * Recherche une administration par nom
     * 
     * @param name Nom de l'administration
     * @return Administration correspondante ou 404 si non trouvée
     */
    @Operation(summary = "Rechercher une administration par nom", 
                description = "Recherche une administration par nom",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Administration trouvée",
                                content = @Content(schema = @Schema(implementation = AdministrationResponseDTO.class))),
                    @ApiResponse(responseCode = "404", 
                                description = "Administration non trouvée",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la recherche de l'administration",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @GetMapping("/searchByName")
    public ResponseEntity<AdministrationResponseDTO> getAdministrationByName(@RequestParam String name) {
        AdministrationResponseDTO administration = administrationService.getAdministrationByName(name);
        if (administration == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(administration);
    }
}
