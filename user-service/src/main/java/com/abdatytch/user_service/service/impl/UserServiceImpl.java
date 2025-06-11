/**
 * Implémentation du service métier pour la gestion des utilisateurs
 * 
 * Cette classe implémente l'interface UserService et fournit la logique métier
 * pour la gestion complète des utilisateurs, y compris la validation et la persistance.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.service.impl;

import com.abdatytch.user_service.dto.request.UserRequestDTO;
import com.abdatytch.user_service.dto.response.UserResponseDTO;
import com.abdatytch.user_service.model.Administration;
import com.abdatytch.user_service.model.Department;
import com.abdatytch.user_service.model.User;
import com.abdatytch.user_service.model.UserRole;
import com.abdatytch.user_service.repository.AdministrationRepository;
import com.abdatytch.user_service.repository.DepartmentRepository;
import com.abdatytch.user_service.repository.UserRepository;
import com.abdatytch.user_service.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation du service métier pour la gestion des utilisateurs
 * 
 * <p>
 * Cette classe gère les opérations suivantes :
 * <ul>
 *     <li>CRUD complet des utilisateurs</li>
 *     <li>Validation des données</li>
 *     <li>Gestion des relations avec les entités associées</li>
 *     <li>Conversion DTO/Entity</li>
 * </ul>
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Repository pour l'accès aux données des utilisateurs
     */
    private final UserRepository userRepository;

    /**
     * Repository pour l'accès aux données des administrations
     */
    private final AdministrationRepository administrationRepository;

    /**
     * Repository pour l'accès aux données des départements
     */
    private final DepartmentRepository departmentRepository;

    /**
     * Constructeur avec injection de dépendances
     * 
     * @param userRepository Repository des utilisateurs
     * @param administrationRepository Repository des administrations
     * @param departmentRepository Repository des départements
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, AdministrationRepository administrationRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.administrationRepository = administrationRepository;
        this.departmentRepository = departmentRepository;
    }

    /**
     * Récupère tous les utilisateurs avec pagination
     * 
     * @param pageable Informations de pagination
     * @return Liste paginée des utilisateurs
     */
    @Override
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(this::convertToDTO);
    }

    /**
     * Crée un nouvel utilisateur
     * 
     * @param userRequestDTO Détails du nouvel utilisateur
     * @return Représentation du nouvel utilisateur créé
     * @throws EntityNotFoundException Si l'administration ou le département n'est pas trouvé
     */
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setFullName(userRequestDTO.getFullName());
        user.setRole(userRequestDTO.getRole());

        if (userRequestDTO.getAdministrationId() != null) {
            Administration administration = administrationRepository.findById(userRequestDTO.getAdministrationId())
                    .orElseThrow(() -> new EntityNotFoundException("Administration not found"));
            user.setAdministration(administration);
        }

        if (userRequestDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(userRequestDTO.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found"));
            user.setDepartment(department);
        }

        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    /**
     * Met à jour un utilisateur existant
     * 
     * @param userId Identifiant de l'utilisateur à modifier
     * @param userRequestDTO Nouveaux détails de l'utilisateur
     * @return Représentation de l'utilisateur mis à jour
     * @throws EntityNotFoundException Si l'utilisateur, l'administration ou le département n'est pas trouvé
     */
    @Override
    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setUsername(userRequestDTO.getUsername());
        user.setFullName(userRequestDTO.getFullName());
        try {
            user.setRole(userRequestDTO.getRole());
        } catch (IllegalArgumentException e) {
            // Ignore invalid role filter
        }

        if (userRequestDTO.getAdministrationId() != null) {
            Administration administration = administrationRepository.findById(userRequestDTO.getAdministrationId())
                    .orElseThrow(() -> new EntityNotFoundException("Administration not found"));
            user.setAdministration(administration);
        } else {
            user.setAdministration(null);
        }

        if (userRequestDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(userRequestDTO.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found"));
            user.setDepartment(department);
        } else {
            user.setDepartment(null);
        }

        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    /**
     * Supprime un utilisateur
     * 
     * @param userId Identifiant de l'utilisateur à supprimer
     * @throws EntityNotFoundException Si l'utilisateur n'est pas trouvé
     */
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }

    /**
     * Récupère les utilisateurs d'une administration spécifique
     * 
     * @param administrationId Identifiant de l'administration
     * @param pageable Informations de pagination
     * @return Page d'utilisateurs appartenant à l'administration
     */
    @Override
    public Page<UserResponseDTO> getUsersByAdministration(Long administrationId, Pageable pageable) {
        Page<User> users = userRepository.findByAdministrationId(administrationId, pageable);
        return users.map(this::convertToDTO);
    }

    /**
     * Récupère les utilisateurs d'un département spécifique
     * 
     * @param departmentId Identifiant du département
     * @param pageable Informations de pagination
     * @return Page d'utilisateurs appartenant au département
     */
    @Override
    public Page<UserResponseDTO> getUsersByDepartment(Long departmentId, Pageable pageable) {
        Page<User> users = userRepository.findByDepartmentId(departmentId, pageable);
        return users.map(this::convertToDTO);
    }

    /**
     * Recherche des utilisateurs avec des filtres multiples
     * 
     * @param username Nom complet à rechercher (contient)
     * @param administrationId Identifiant de l'administration (optionnel)
     * @param departmentId Identifiant du département (optionnel)
     * @param roleStr Rôle de l'utilisateur (optionnel)
     * @param pageable Informations de pagination
     * @return Page d'utilisateurs correspondant aux critères
     */
    @Override
    public Page<UserResponseDTO> searchUsers(String username, Long administrationId, Long departmentId, String roleStr, Pageable pageable) {
        Specification<User> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtre par nom complet
            if (username != null && !username.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("fullName")), "%" + username.toLowerCase() + "%"));
            }

            // Filtre par administration
            if (administrationId != null) {
                predicates.add(cb.equal(root.get("administration").get("id"), administrationId));
            }

            // Filtre par département
            if (departmentId != null) {
                predicates.add(cb.equal(root.get("department").get("id"), departmentId));
            }

            // Filtre par rôle
            if (roleStr != null && !roleStr.isEmpty()) {
                try {
                    predicates.add(cb.equal(cb.lower(root.get("role").as(String.class)), roleStr.toLowerCase()));
                } catch (IllegalArgumentException e) {
                    // Ignore invalid role filter
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<User> users = userRepository.findAll(spec, pageable);
        return users.map(this::convertToDTO);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setRole(user.getRole());
        if (user.getAdministration() != null) {
            dto.setAdministrationId(user.getAdministration().getId());
            dto.setAdministrationName(user.getAdministration().getName());
        }
        if (user.getDepartment() != null) {
            dto.setDepartmentId(user.getDepartment().getId());
            dto.setDepartmentName(user.getDepartment().getName());
        }
        return dto;
    }
}
