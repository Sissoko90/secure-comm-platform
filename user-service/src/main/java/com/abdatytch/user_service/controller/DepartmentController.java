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

import com.abdatytch.user_service.dto.request.DepartmentRequestDTO;
import com.abdatytch.user_service.dto.response.DepartmentResponseDTO;
import com.abdatytch.user_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des départements
 * 
 * <p>
 * Ce contrôleur expose les endpoints suivants :
 * <ul>
 *     <li>CRUD complet des départements</li>
 *     <li>Recherche par nom</li>
 *     <li>Liste de tous les départements</li>
 * </ul>
 */
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
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Crée un nouveau département
     * 
     * @param departmentRequestDTO Détails du nouveau département
     * @return Représentation du département créé
     */
    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO) {
        DepartmentResponseDTO created = departmentService.createDepartment(departmentRequestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Met à jour un département existant
     * 
     * @param id Identifiant du département à modifier
     * @param departmentRequestDTO Nouveaux détails du département
     * @return Représentation du département mis à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequestDTO departmentRequestDTO) {
        DepartmentResponseDTO updated = departmentService.updateDepartment(id, departmentRequestDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * Supprime un département
     * 
     * @param id Identifiant du département à supprimer
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupère la liste de tous les départements
     * 
     * @return Liste des départements
     */
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
    @GetMapping("/searchByName")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentByName(@RequestParam String name) {
        DepartmentResponseDTO department = departmentService.getDepartmentByName(name);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }
}
