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
import com.abdatytch.user_service.dto.CredentialsUpdateDTO;
import com.abdatytch.user_service.model.Administration;
import com.abdatytch.user_service.model.Department;
import com.abdatytch.user_service.model.User;
import com.abdatytch.user_service.repository.AdministrationRepository;
import com.abdatytch.user_service.repository.DepartmentRepository;
import com.abdatytch.user_service.repository.UserRepository;
import com.abdatytch.user_service.service.UserService;
import com.abdatytch.user_service.validation.UserValidator;
import com.abdatytch.user_service.constant.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.UUID;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;




@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AdministrationRepository administrationRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    // Constructeur avec injection de dépendances
    public UserServiceImpl(UserRepository userRepository, 
                       AdministrationRepository administrationRepository, 
                       DepartmentRepository departmentRepository,
                       PasswordEncoder passwordEncoder,
                       UserValidator userValidator) {
        this.userRepository = userRepository;
        this.administrationRepository = administrationRepository;
        this.departmentRepository = departmentRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;
    }

    @Override
    @Transactional
    public UserResponseDTO updateCredentials(UUID userId, CredentialsUpdateDTO credentialsDTO) throws ResponseStatusException {
        // Vérifier si l'utilisateur existe
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Message.USER_NOT_FOUND));

        // Vérifier si le nouveau nom d'utilisateur est déjà utilisé par un autre utilisateur
        if (!user.getUsername().equals(credentialsDTO.getUsername()) &&
            userRepository.existsByUsername(credentialsDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.USER_ALREADY_EXISTS);
        }

        // Mettre à jour les identifiants
        user.setUsername(credentialsDTO.getUsername());
        // Encoder le mot de passe avant de le sauvegarder
        user.setPassword(passwordEncoder.encode(credentialsDTO.getPassword()));

        // Sauvegarder les modifications
        user = userRepository.save(user);

        // Convertir et retourner l'utilisateur mis à jour
        return convertToDTO(user);
    }



    /**
     * @param administrationRepository Repository des administrations
     * @param departmentRepository Repository des départements
     */
    

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
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // Vérifier l'unicité des champs
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.DUPLICATE_EMAIL);
        }
        if (userRepository.existsByPhoneNumber(userRequestDTO.getPhoneNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.DUPLICATE_PHONE_NUMBER);
        }
        if (userRepository.existsByMatriculeNumber(userRequestDTO.getMatriculeNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.DUPLICATE_MATRICULE);
        }

        // Générer le username automatiquement si non fourni
        String username = userRequestDTO.getUsername();
        if (username == null || username.trim().isEmpty()) {
            username = generateUsername(userRequestDTO.getFirstName(), userRequestDTO.getLastName());
        }
        
        // Vérifier les données obligatoires
        if (userRequestDTO.getFirstName() == null || userRequestDTO.getFirstName().trim().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.FIRSTNAME_REQUIRED
            );
        }
        if (userRequestDTO.getLastName() == null || userRequestDTO.getLastName().trim().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.LASTNAME_REQUIRED
            );
        }
        if (userRequestDTO.getRole() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.ROLE_REQUIRED
            );
        }
        if (userRequestDTO.getAdministrationId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.ADMINISTRATION_ID_REQUIRED
            );
        }

        // Valider la longueur du nom d'utilisateur
        if (username == null || username.length() < 3) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.USERNAME_TOO_SHORT
            );
        }
        if (username == null || username.length() > 50) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.USERNAME_TOO_LONG
            );
        }

        try {
            // Valider le DTO
            userValidator.validate(userRequestDTO, false);

            // Vérifier si l'administration existe
            Administration administration = administrationRepository.findById(userRequestDTO.getAdministrationId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Message.ADMINISTRATION_NOT_FOUND));

            // Vérifier si le département existe
            Department department = departmentRepository.findById(userRequestDTO.getDepartmentId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Message.DEPARTMENT_NOT_FOUND));

            // Créer l'utilisateur
            User user = new User();
            user.setUsername(username);
            
            // Générer un mot de passe aléatoire de 12 caractères
            String generatedPassword = UUID.randomUUID().toString().substring(0, 12);
            user.setPassword(passwordEncoder.encode(generatedPassword));
            
            user.setFirstName(userRequestDTO.getFirstName());
            user.setLastName(userRequestDTO.getLastName());
            user.setRole(userRequestDTO.getRole());
            user.setAdministration(administration);
            user.setDepartment(department);
            user.setPhoneNumber(userRequestDTO.getPhoneNumber());
            user.setEmail(userRequestDTO.getEmail());
            user.setAddress(userRequestDTO.getAddress());
            user.setBirthDate(userRequestDTO.getBirthDate());
            user.setBirthPlace(userRequestDTO.getBirthPlace());
            user.setPosition(userRequestDTO.getPosition());
            user.setMaritalStatus(userRequestDTO.getMaritalStatus());
            user.setMatriculeNumber(userRequestDTO.getMatriculeNumber());

            // Valider l'entité avant sauvegarde
            userValidator.validate(user);

            // Sauvegarder l'utilisateur
            User savedUser = userRepository.save(user);
            return convertToDTO(savedUser);
        } catch (UserValidator.ValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessages()[0]);
        }
    }

    /**
     * Met à jour un utilisateur existant
     * 
     * @param id Identifiant de l'utilisateur à modifier
     * @param userRequestDTO Nouveaux détails de l'utilisateur
     * @return Représentation de l'utilisateur mis à jour
     * @throws EntityNotFoundException Si l'utilisateur, l'administration ou le département n'est pas trouvé
     */
    @Override
    @Transactional
    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO) {
        // Vérifier les données obligatoires
        if (userRequestDTO.getFirstName() == null || userRequestDTO.getFirstName().trim().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.FIRSTNAME_REQUIRED
            );
        }
        if (userRequestDTO.getLastName() == null || userRequestDTO.getLastName().trim().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.LASTNAME_REQUIRED
            );
        }
        if (userRequestDTO.getRole() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.ROLE_REQUIRED
            );
        }
        if (userRequestDTO.getAdministrationId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                Message.ADMINISTRATION_ID_REQUIRED
            );
        }

        // Vérifier si l'utilisateur existe
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Message.USER_NOT_FOUND));

        // Vérifier l'unicité des champs
        if (!user.getEmail().equals(userRequestDTO.getEmail()) && 
            userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.DUPLICATE_EMAIL);
        }
        if (!user.getPhoneNumber().equals(userRequestDTO.getPhoneNumber()) && 
            userRepository.existsByPhoneNumber(userRequestDTO.getPhoneNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.DUPLICATE_PHONE_NUMBER);
        }
        if (!user.getMatriculeNumber().equals(userRequestDTO.getMatriculeNumber()) && 
            userRepository.existsByMatriculeNumber(userRequestDTO.getMatriculeNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.DUPLICATE_MATRICULE);
        }

        // Mettre à jour les champs de l'utilisateur
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setRole(userRequestDTO.getRole());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setEmail(userRequestDTO.getEmail());
        user.setAddress(userRequestDTO.getAddress());
        user.setBirthDate(userRequestDTO.getBirthDate());
        user.setBirthPlace(userRequestDTO.getBirthPlace());
        user.setPosition(userRequestDTO.getPosition());
        user.setMaritalStatus(userRequestDTO.getMaritalStatus());
        user.setMatriculeNumber(userRequestDTO.getMatriculeNumber());

        // Mettre à jour l'administration si spécifiée
        if (userRequestDTO.getAdministrationId() != null) {
            Administration administration = administrationRepository.findById(userRequestDTO.getAdministrationId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Message.ADMINISTRATION_NOT_FOUND));
            user.setAdministration(administration);
        }

        // Mettre à jour le département si spécifié
        if (userRequestDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(userRequestDTO.getDepartmentId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Message.DEPARTMENT_NOT_FOUND));
            user.setDepartment(department);
        }

        // Sauvegarder et retourner l'utilisateur mis à jour
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }
    /**
     * Supprime un utilisateur
     * 
     * @param userId Identifiant de l'utilisateur à supprimer
     * @throws ResponseStatusException Si l'utilisateur n'est pas trouvé
     */
    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        // Vérifier si l'utilisateur existe
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Message.USER_NOT_FOUND);
        }

        // Supprimer l'utilisateur
        userRepository.deleteById(userId);
    }

    /**
     * Récupère les utilisateurs d'une administration spécifique
     * 
     * @param administrationId Identifiant de l'administration
     * @param pageable Informations de pagination
     * @return Page d'utilisateurs appartenant à l'administration
     */
    @Override
    public Page<UserResponseDTO> getUsersByAdministration(UUID administrationId, Pageable pageable) {
        Page<User> users = userRepository.findByAdministrationId(administrationId, pageable);
        return users.map(this::convertToDTO);
    }

    /**
     * Génère un username unique basé sur le prénom et le nom
     * @param firstName Prénom de l'utilisateur
     * @param lastName Nom de l'utilisateur
     * @return Username unique
     */
    private String generateUsername(String firstName, String lastName) {
        // Générer un username basé sur le prénom et le nom
        String baseUsername = (firstName.toLowerCase() + lastName.toLowerCase()).replaceAll("\\s+", "");
        String username = baseUsername;
        int counter = 1;
        
        // Ajouter un compteur si le username existe déjà
        while (userRepository.existsByUsername(username)) {
            username = baseUsername + counter++;
        }
        
        return username;
    }

    /**
     * Récupère les utilisateurs d'un département spécifique
     * 
     * @param departmentId Identifiant du département
     * @param pageable Informations de pagination
     * @return Page d'utilisateurs appartenant au département
     */
    @Override
    public Page<UserResponseDTO> getUsersByDepartment(UUID departmentId, Pageable pageable) {
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
    public Page<UserResponseDTO> searchUsers(String username, UUID administrationId, UUID departmentId, String roleStr, Pageable pageable) {
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
    public UserResponseDTO getUserById(UUID userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
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
