/**
 * Interface du service métier pour la gestion des départements
 * 
 * Cette interface définit les opérations de base pour la gestion des départements
 * dans le système.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.service;

import com.abdatytch.user_service.dto.request.DepartmentRequestDTO;
import com.abdatytch.user_service.dto.response.DepartmentResponseDTO;
import java.util.List;

/**
 * Interface du service métier pour la gestion des départements
 * 
 * <p>
 * Cette interface définit les opérations suivantes :
 * <ul>
 *     <li>CRUD complet des départements</li>
 *     <li>Recherche par nom</li>
 * </ul>
 */
public interface DepartmentService {
    /**
     * Crée un nouveau département
     * 
     * @param departmentRequestDTO Détails du nouveau département
     * @return Représentation du département créé
     */
    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);

    /**
     * Met à jour un département existant
     * 
     * @param departmentId Identifiant du département à modifier
     * @param departmentRequestDTO Nouveaux détails du département
     * @return Représentation du département mis à jour
     */
    DepartmentResponseDTO updateDepartment(Long departmentId, DepartmentRequestDTO departmentRequestDTO);

    /**
     * Supprime un département
     * 
     * @param departmentId Identifiant du département à supprimer
     */
    void deleteDepartment(Long departmentId);

    /**
     * Récupère la liste de tous les départements
     * 
     * @return Liste des départements
     */
    List<DepartmentResponseDTO> getAllDepartments();

    /**
     * Recherche un département par nom
     * 
     * @param name Nom du département
     * @return Département correspondant ou null si non trouvé
     */
    DepartmentResponseDTO getDepartmentByName(String name);
}
