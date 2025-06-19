package com.abdatytch.user_service.exception;

import com.abdatytch.user_service.dto.response.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Gestionnaire global des exceptions pour l'API
 * 
 * Ce gestionnaire centralise la gestion des erreurs pour :
 * - Validation des DTOs (ConstraintViolationException)
 * - Validation des formulaires (MethodArgumentNotValidException)
 * - Exceptions personnalisées (ApiException)
 * - Exceptions non gérées
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-14
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gestion des erreurs de validation des DTOs
     * @param ex Exception de validation
     * @return Response avec message d'erreur formaté
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> details = ex.getConstraintViolations()
            .stream()
            .map(violation -> violation.getMessage())
            .collect(Collectors.toList());
        
        ErrorDTO errorDTO = new ErrorDTO(
            HttpStatus.BAD_REQUEST.value(),
            "Validation errors",
            details
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    /**
     * Gestion des erreurs de validation des formulaires
     * @param ex Exception de validation des formulaires
     * @return Response avec message d'erreur formaté
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.toList());
        
        ErrorDTO errorDTO = new ErrorDTO(
            HttpStatus.BAD_REQUEST.value(),
            "Validation errors",
            details
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    /**
     * Gestion des exceptions personnalisées
     * @param ex Exception personnalisée
     * @return Response avec message d'erreur formaté
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDTO> handleApiException(ApiException ex) {
        ErrorDTO errorDTO = new ErrorDTO(
            ex.getStatus().value(),
            ex.getMessage(),
            List.of(ex.getDetails())
        );
        
        return ResponseEntity.status(ex.getStatus()).body(errorDTO);
    }

    /**
     * Gestion des exceptions non gérées
     * @param ex Exception non gérée
     * @return Response avec message d'erreur formaté
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error",
            List.of(ex.getMessage())
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}
