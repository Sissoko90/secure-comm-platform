/**
 * Classe de validation pour les utilisateurs
 * 
 * Cette classe contient toutes les validations métier pour les utilisateurs,
 * y compris la validation des formats et l'unicité des champs.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-14
 */
package com.abdatytch.user_service.validation;

import com.abdatytch.user_service.dto.request.UserRequestDTO;
import com.abdatytch.user_service.model.User;
import com.abdatytch.user_service.repository.UserRepository;
import com.abdatytch.user_service.constant.Message;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class UserValidator {

    private final Validator validator;
    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        this.userRepository = userRepository;
    }

    /**
     * Valide un DTO de création ou mise à jour d'utilisateur
     * @param userRequestDTO DTO à valider
     * @param isUpdate true si c'est une mise à jour
     * @throws ValidationException si des erreurs de validation sont trouvées
     */
    public void validate(UserRequestDTO userRequestDTO, boolean isUpdate) throws ValidationException {
        // Validation des contraintes JPA
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations.stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toArray(String[]::new));
        }

        // Validation de l'unicité des champs
        if (!isUpdate || userRequestDTO.getId() == null) {
            if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
                throw new ValidationException(Message.DUPLICATE_EMAIL);
            }
            if (userRepository.existsByPhoneNumber(userRequestDTO.getPhoneNumber())) {
                throw new ValidationException(Message.DUPLICATE_PHONE_NUMBER);
            }
            if (userRepository.existsByMatriculeNumber(userRequestDTO.getMatriculeNumber())) {
                throw new ValidationException(Message.DUPLICATE_MATRICULE);
            }
        }
    }

    /**
     * Valide une entité User
     * @param user Entité à valider
     * @throws ValidationException si des erreurs de validation sont trouvées
     */
    public void validate(User user) throws ValidationException {
        // Validation des contraintes JPA
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations.stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toArray(String[]::new));
        }
    }

    /**
     * Exception personnalisée pour les erreurs de validation
     */
    public static class ValidationException extends RuntimeException {
        private final String[] messages;

        public ValidationException(String... messages) {
            this.messages = messages;
        }

        public String[] getMessages() {
            return messages;
        }
    }
}
