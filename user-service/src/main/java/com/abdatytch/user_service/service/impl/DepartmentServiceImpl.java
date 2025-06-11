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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implémentation du service métier pour la gestion des départements
 * 
 * <p>
 * Cette classe fournit les opérations suivantes :
 * <ul>
 *     <li>CRUD complet des départements</li>
 *     <li>Recherche par nom</li>
 *     <li>Conversion entre entités et DTOs</li>
 * </ul>
 */
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
     * Constructeur avec injection de dépendances
     * 
     * @param departmentRepository Repository des départements
     * @param administrationRepository Repository des administrations
     */
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, AdministrationRepository administrationRepository) {
        this.departmentRepository = departmentRepository;
        this.administrationRepository = administrationRepository;
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
     * @param departmentId Identifiant du département à modifier
     * @param departmentRequestDTO Nouveaux détails du département
     * @return Représentation du département mis à jour
     * @throws EntityNotFoundException Si le département ou l'administration n'est pas trouvé
     */
    @Override
    public DepartmentResponseDTO updateDepartment(Long departmentId, DepartmentRequestDTO departmentRequestDTO) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        department.setName(departmentRequestDTO.getName());
        if (departmentRequestDTO.getAdministrationId() != null) {
            Administration administration = administrationRepository.findById(departmentRequestDTO.getAdministrationId())
                    .orElseThrow(() -> new EntityNotFoundException("Administration not found"));
            department.setAdministration(administration);
        } else {
            department.setAdministration(null);
        }
        Department updated = departmentRepository.save(department);
        return convertToDTO(updated);
    }

    /**
     * Supprime un département
     * 
     * @param departmentId Identifiant du département à supprimer
     * @throws EntityNotFoundException Si le département n'est pas trouvé
     */
    @Override
    public void deleteDepartment(Long departmentId) {
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
