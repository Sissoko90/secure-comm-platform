/**
 * Implémentation du service métier pour la gestion des départements
 * 
 * Cette classe implémente la logique métier pour la gestion des départements,
 * y compris leur création, mise à jour, suppression et recherche.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.service.impl;

import com.abdatytch.user_service.dto.request.DepartmentRequestDTO;
import com.abdatytch.user_service.dto.response.DepartmentResponseDTO;
import com.abdatytch.user_service.model.Department;
import com.abdatytch.user_service.model.Administration;
import com.abdatytch.user_service.repository.DepartmentRepository;
import com.abdatytch.user_service.repository.AdministrationRepository;
import com.abdatytch.user_service.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abdatytch.user_service.constant.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolationException;
import java.util.UUID;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    /**
     * Repository pour l'accès aux données des départements
     */
    private final DepartmentRepository departmentRepository;

    /**
     * Repository pour l'accès aux données des administrations
     */
    private final AdministrationRepository administrationRepository;

    /**
     * Validator pour la validation des DTOs
     */
    private final Validator validator;

    /**
     * Constructeur avec injection de dépendances
     * 
     * @param departmentRepository Repository des départements
     * @param administrationRepository Repository des administrations
     */
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, AdministrationRepository administrationRepository) {
        this.departmentRepository = departmentRepository;
        this.administrationRepository = administrationRepository;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Crée un nouveau département
     * 
     * @param departmentRequestDTO Détails du nouveau département
     * @return Représentation du département créé
     * @throws EntityNotFoundException Si l'administration n'est pas trouvée
     */
    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        // Valider le DTO avant de sauvegarder
        var violations = validator.validate(departmentRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation errors", violations);
        }

        // Vérifier si le département existe déjà
        if (departmentRepository.existsByNameIgnoreCase(departmentRequestDTO.getName())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.DEPARTMENT_ALREADY_EXISTS
            );
        }
        Department department = new Department();
        department.setName(departmentRequestDTO.getName());
        if (departmentRequestDTO.getAdministrationId() != null) {
            Administration administration = administrationRepository.findById(departmentRequestDTO.getAdministrationId())
                    .orElseThrow(() -> new EntityNotFoundException("Administration not found"));
            department.setAdministration(administration);
        }
        Department saved = departmentRepository.save(department);
        return convertToDTO(saved);
    }

    /**
     * Met à jour un département existant
     * 
     * @param id Identifiant du département à modifier
     * @param departmentRequestDTO Nouveaux détails du département
     * @return Représentation du département mis à jour
     * @throws EntityNotFoundException Si le département ou l'administration n'est pas trouvé
     */
    @Override
    public DepartmentResponseDTO updateDepartment(UUID id, DepartmentRequestDTO departmentRequestDTO) {
        // Vérifier si le département existe
        Department existingDepartment = departmentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                Message.DEPARTMENT_NOT_FOUND
            ));

        // Vérifier les données obligatoires
        if (departmentRequestDTO.getName() == null || departmentRequestDTO.getName().trim().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.INVALID_DEPARTMENT_DATA
            );
        }
        if (departmentRequestDTO.getAdministrationId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.INVALID_DEPARTMENT_DATA
            );
        }
        existingDepartment.setName(departmentRequestDTO.getName());
        if (departmentRequestDTO.getAdministrationId() != null) {
            Administration administration = administrationRepository.findById(departmentRequestDTO.getAdministrationId())
                    .orElseThrow(() -> new EntityNotFoundException("Administration not found"));
            existingDepartment.setAdministration(administration);
        } else {
            existingDepartment.setAdministration(null);
        }
        Department updated = departmentRepository.save(existingDepartment);
        return convertToDTO(updated);
    }

    /**
     * Supprime un département
     * 
     * @param departmentId Identifiant du département à supprimer
     * @throws EntityNotFoundException Si le département n'est pas trouvé
     */
    @Override
    public void deleteDepartment(UUID departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        departmentRepository.delete(department);
    }

    /**
     * Récupère la liste de tous les départements
     * 
     * @return Liste des départements
     */
    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        return departmentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Recherche un département par nom
     * 
     * @param name Nom du département
     * @return Département correspondant ou null si non trouvé
     */
    @Override
    public DepartmentResponseDTO getDepartmentByName(String name) {
        return departmentRepository.findByName(name).map(this::convertToDTO).orElse(null);
    }

    /**
     * Convertit une entité Department en DTO
     * 
     * @param department Entité à convertir
     * @return DTO correspondant
     */
    private DepartmentResponseDTO convertToDTO(Department department) {
        DepartmentResponseDTO dto = new DepartmentResponseDTO();
        BeanUtils.copyProperties(department, dto);
        if (department.getAdministration() != null) {
            dto.setAdministrationId(department.getAdministration().getId());
            dto.setAdministrationName(department.getAdministration().getName());
        }
        return dto;
    }
}
