/**
 * Contrôleur REST pour la gestion des départements
 * 
 * Ce contrôleur gère les opérations CRUD sur les départements et fournit
 * des endpoints pour la recherche et la gestion des unités organisationnelles.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.controller;

import java.util.UUID;
import com.abdatytch.user_service.dto.request.DepartmentRequestDTO;
import com.abdatytch.user_service.dto.response.DepartmentResponseDTO;
import com.abdatytch.user_service.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import com.abdatytch.user_service.constant.Message;

import java.util.List;


@Tag(name = "Department Management", description = "CRUD et recherche de départements")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {



    /**
     * Service métier pour la gestion des départements
     */
    private final DepartmentService departmentService;

    /**
     * Constructeur avec injection de dépendance
     * 
     * @param departmentService Service métier pour la gestion des départements
     */

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Crée un nouveau département
     * 
     * @param departmentRequestDTO Détails du nouveau département
     * @return Représentation du département créé
     */
    @Operation(summary = "Créer un nouveau département", 
                description = "Crée un nouveau département",
                responses = {
                    @ApiResponse(responseCode = "201", 
                                description = "Département créé avec succès",
                                content = @Content(schema = @Schema(implementation = DepartmentResponseDTO.class))),
                    @ApiResponse(responseCode = "400", 
                                description = "Données de département invalides ou département déjà existant",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la création du département",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO) {
        DepartmentResponseDTO created = departmentService.createDepartment(departmentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-Message", Message.DEPARTMENT_CREATED_SUCCESS)
                .body(created);
    }

    /**
     * Met à jour un département existant
     * 
     * @param id Identifiant du département à modifier
     * @param departmentRequestDTO Nouveaux détails du département
     * @return Représentation du département mis à jour
     */
    @Operation(summary = "Mettre à jour un département existant", 
                description = "Mettre à jour un département existant",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Département mis à jour avec succès",
                                content = @Content(schema = @Schema(implementation = DepartmentResponseDTO.class))),
                    @ApiResponse(responseCode = "400", 
                                description = "Données de département invalides",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "404", 
                                description = "Département non trouvé",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la mise à jour du département",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@PathVariable UUID id, @RequestBody DepartmentRequestDTO departmentRequestDTO) {
        DepartmentResponseDTO updated = departmentService.updateDepartment(id, departmentRequestDTO);
        return ResponseEntity.ok()
                .header("X-Message", Message.DEPARTMENT_UPDATED_SUCCESS)
                .body(updated);
    }

    /**
     * Supprime un département
     * 
     * @param id Identifiant du département à supprimer
     */
    @Operation(summary = "Supprimer un département", 
                description = "Supprime un département",
                responses = {
                    @ApiResponse(responseCode = "204", 
                                description = "Département supprimé avec succès"),
                    @ApiResponse(responseCode = "404", 
                                description = "Département non trouvé",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la suppression du département",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable UUID id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent()
                .header("X-Message", Message.DEPARTMENT_DELETED_SUCCESS)
                .build();
    }

    /**
     * Récupère la liste de tous les départements
     * 
     * @return Liste des départements
     */
    @Operation(summary = "Récupérer tous les départements", 
                description = "Récupère tous les départements",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Liste des départements récupérée avec succès",
                                content = @Content(schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la récupération des départements",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        List<DepartmentResponseDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    /**
     * Recherche un département par nom
     * 
     * @param name Nom du département
     * @return Département correspondant ou 404 si non trouvé
     */
    @Operation(summary = "Rechercher un département par son nom", 
                description = "Recherche un département par son nom",
                responses = {
                    @ApiResponse(responseCode = "200", 
                                description = "Département trouvé",
                                content = @Content(schema = @Schema(implementation = DepartmentResponseDTO.class))),
                    @ApiResponse(responseCode = "404", 
                                description = "Département non trouvé",
                                content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", 
                                description = "Erreur serveur lors de la recherche du département",
                                content = @Content(schema = @Schema(implementation = String.class)))
                })
    @GetMapping("/searchByName")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentByName(@RequestParam String name) {
        DepartmentResponseDTO department = departmentService.getDepartmentByName(name);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }
}
