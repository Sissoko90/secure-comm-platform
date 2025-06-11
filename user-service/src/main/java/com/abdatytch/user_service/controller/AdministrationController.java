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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des administrations
 * 
 * <p>
 * Ce contrôleur expose les endpoints suivants :
 * <ul>
 *     <li>CRUD complet des administrations</li>
 *     <li>Recherche par nom</li>
 *     <li>Liste de toutes les administrations</li>
 * </ul>
 */
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
    @Autowired
    public AdministrationController(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    /**
     * Crée une nouvelle administration
     * 
     * @param administrationRequestDTO Détails de la nouvelle administration
     * @return Représentation de l'administration créée
     */
    @PostMapping
    public ResponseEntity<AdministrationResponseDTO> createAdministration(@RequestBody AdministrationRequestDTO administrationRequestDTO) {
        AdministrationResponseDTO created = administrationService.createAdministration(administrationRequestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Met à jour une administration existante
     * 
     * @param id Identifiant de l'administration à modifier
     * @param administrationRequestDTO Nouveaux détails de l'administration
     * @return Représentation de l'administration mise à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<AdministrationResponseDTO> updateAdministration(@PathVariable Long id, @RequestBody AdministrationRequestDTO administrationRequestDTO) {
        AdministrationResponseDTO updated = administrationService.updateAdministration(id, administrationRequestDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * Supprime une administration
     * 
     * @param id Identifiant de l'administration à supprimer
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministration(@PathVariable Long id) {
        administrationService.deleteAdministration(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupère la liste de toutes les administrations
     * 
     * @return Liste des administrations
     */
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
    @GetMapping("/searchByName")
    public ResponseEntity<AdministrationResponseDTO> getAdministrationByName(@RequestParam String name) {
        AdministrationResponseDTO administration = administrationService.getAdministrationByName(name);
        if (administration == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(administration);
    }
}
