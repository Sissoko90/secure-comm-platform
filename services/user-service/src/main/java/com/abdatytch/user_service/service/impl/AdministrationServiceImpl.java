/**
 * Implémentation du service Administration
 * 
 * Cette classe implémente la logique métier pour la gestion des administrations.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.service.impl;

import com.abdatytch.user_service.dto.request.AdministrationRequestDTO;
import com.abdatytch.user_service.dto.response.AdministrationResponseDTO;
import com.abdatytch.user_service.model.Administration;
import com.abdatytch.user_service.repository.AdministrationRepository;
import com.abdatytch.user_service.service.AdministrationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministrationServiceImpl implements AdministrationService {

    /**
     * Repository pour l'accès aux données des administrations
     */
    private final AdministrationRepository administrationRepository;

    /**
     * Validator pour la validation des DTOs
     */
    private final Validator validator;

    /**
     * Constructeur avec injection de dépendance
     * 
     * @param administrationRepository Repository pour les opérations CRUD
     */
    @Autowired
    public AdministrationServiceImpl(AdministrationRepository administrationRepository) {
        this.administrationRepository = administrationRepository;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Crée une nouvelle administration
     * 
     * @param administrationRequestDTO Détails de la nouvelle administration
     * @return Représentation de l'administration créée
     */
    @Override
    public AdministrationResponseDTO createAdministration(AdministrationRequestDTO administrationRequestDTO) {
        // Valider le DTO avant de sauvegarder
        var violations = validator.validate(administrationRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation errors", violations);
        }
        
        Administration administration = new Administration();
        administration.setName(administrationRequestDTO.getName());
        Administration saved = administrationRepository.save(administration);
        return convertToDTO(saved);
    }

    /**
     * Met à jour une administration existante
     * 
     * @param administrationId Identifiant de l'administration à modifier
     * @param administrationRequestDTO Nouveaux détails de l'administration
     * @return Représentation de l'administration mise à jour
     * @throws EntityNotFoundException Si l'administration n'est pas trouvée
     */
    @Override
    public AdministrationResponseDTO updateAdministration(UUID administrationId, AdministrationRequestDTO administrationRequestDTO) {
        // Valider le DTO avant de sauvegarder
        var violations = validator.validate(administrationRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation errors", violations);
        }
        
        Administration administration = administrationRepository.findById(administrationId)
                .orElseThrow(() -> new EntityNotFoundException("Administration not found"));
        administration.setName(administrationRequestDTO.getName());
        Administration updated = administrationRepository.save(administration);
        return convertToDTO(updated);
    }

    /**
     * Supprime une administration
     * 
     * @param administrationId Identifiant de l'administration à supprimer
     * @throws EntityNotFoundException Si l'administration n'est pas trouvée
     */
    @Override
    public void deleteAdministration(UUID administrationId) {
        Administration administration = administrationRepository.findById(administrationId)
                .orElseThrow(() -> new EntityNotFoundException("Administration not found"));
        administrationRepository.delete(administration);
    }

    /**
     * Récupère la liste de toutes les administrations
     * 
     * @return Liste des administrations
     */
    @Override
    public List<AdministrationResponseDTO> getAllAdministrations() {
        return administrationRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Recherche une administration par nom
     * 
     * @param name Nom de l'administration
     * @return Administration correspondante ou null si non trouvée
     */
    @Override
    public AdministrationResponseDTO getAdministrationByName(String name) {
        Administration administration = administrationRepository.findByName(name).orElse(null);
        if (administration == null) {
            return null;
        }
        return convertToDTO(administration);
    }

    /**
     * Convertit une entité Administration en DTO
     * 
     * @param administration Entité à convertir
     * @return DTO correspondant
     */
    private AdministrationResponseDTO convertToDTO(Administration administration) {
        AdministrationResponseDTO dto = new AdministrationResponseDTO();
        BeanUtils.copyProperties(administration, dto);
        return dto;
    }
}
