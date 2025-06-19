/**
 * DTO de requête pour la création et mise à jour d'un utilisateur.
 * Contient les informations nécessaires pour gérer un utilisateur, y compris ses informations personnelles et son affiliation organisationnelle.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import com.abdatytch.user_service.constant.Message;
import com.abdatytch.user_service.model.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Past;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class UserRequestDTO {

    @Schema(description = "Numéro matricule unique de l'utilisateur")
    @NotBlank(message = Message.MATRICULE_NUMBER_REQUIRED)
    @Size(min = 5, max = 20, message = Message.MATRICULE_NUMBER_TOO_SHORT + " et " + Message.MATRICULE_NUMBER_TOO_LONG)
    private String matriculeNumber;


    @Schema(description = "Identifiant unique de l'utilisateur", example = "1")
    private UUID id;

    @Schema(description = "Nom d'utilisateur unique", example = "msissoko")
    @Size(min = 3, max = 50, message = Message.USERNAME_TOO_SHORT + " et " + Message.USERNAME_TOO_LONG)
    private String username;



    @Schema(description = "Prénom de l'utilisateur", example = "Makan")
    @NotBlank(message = Message.FIRSTNAME_REQUIRED)
    @Size(min = 2, max = 50, message = Message.FIRSTNAME_TOO_SHORT + " et " + Message.FIRSTNAME_TOO_LONG)
    @JsonProperty("firstname")
    private String firstName;

    @Schema(description = "Nom de l'utilisateur", example = "Sissoko")
    @NotBlank(message = Message.LASTNAME_REQUIRED)
    @Size(min = 2, max = 100, message = Message.LASTNAME_TOO_SHORT + " et " + Message.LASTNAME_TOO_LONG)
    private String lastName;

    @Schema(description = "Rôle de l'utilisateur", example = "ADMIN")
    @NotNull(message = Message.ROLE_REQUIRED)
    private UserRole role;

    @Schema(description = "ID de l'administration de l'utilisateur", example = "1")
    @NotNull(message = Message.ADMINISTRATION_ID_REQUIRED)
    private UUID administrationId;

    @Schema(description = "ID du département de l'utilisateur", example = "2")
    @NotNull(message = Message.DEPARTMENT_ID_REQUIRED)
    private UUID departmentId;

    @Schema(description = "Numéro de téléphone", example = "77123456")
    @NotBlank(message = Message.PHONE_NUMBER_REQUIRED)
    @Pattern(regexp = "^[0-9]{8}$", message = Message.PHONE_NUMBER_INVALID)
    private String phoneNumber;

    @Schema(description = "Email de l'utilisateur", example = "user@example.com")
    @NotBlank(message = Message.EMAIL_REQUIRED)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$", message = Message.EMAIL_INVALID)
    private String email;

    @Schema(description = "Adresse de domicile", example = "123 Rue de l'Exemple")
    @NotBlank(message = Message.ADDRESS_REQUIRED)
    @Size(min = 10, max = 200, message = Message.ADDRESS_TOO_SHORT + " et " + Message.ADDRESS_TOO_LONG)
    private String address;

    @Schema(description = "Date de naissance", example = "1990-01-01")
    @NotNull(message = Message.BIRTH_DATE_REQUIRED)
    @Past(message = Message.BIRTH_DATE_INVALID)
    private LocalDate birthDate;

    @Schema(description = "Lieu de naissance", example = "Bamako")
    @NotBlank(message = Message.BIRTH_PLACE_REQUIRED)
    @Size(min = 3, max = 100, message = Message.BIRTH_PLACE_TOO_SHORT + " et " + Message.BIRTH_PLACE_TOO_LONG)
    private String birthPlace;

    @Schema(description = "Poste occupé", example = "Chef de département")
    @NotBlank(message = Message.POSITION_REQUIRED)
    @Size(min = 2, max = 100, message = Message.POSITION_TOO_SHORT + " et " + Message.POSITION_TOO_LONG)
    private String position;

    @Schema(description = "Situation matrimoniale", example = "Marié")
    @NotBlank(message = Message.MARITAL_STATUS_REQUIRED)
    private String maritalStatus;



     // Constructeur sans arguments
    public UserRequestDTO() {
    }

    // Constructeur avec tous les arguments
    public UserRequestDTO(UUID id, String username, String firstName, String lastName, UserRole role, UUID administrationId, UUID departmentId, String phoneNumber, String email, String address, LocalDate birthDate, String birthPlace, String position, String maritalStatus, String matriculeNumber) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.administrationId = administrationId;
        this.departmentId = departmentId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.position = position;
        this.maritalStatus = maritalStatus;
        this.matriculeNumber = matriculeNumber;
    }

    // Getters et Setters
    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public UserRole getRole() {return role;}

    public void setRole(UserRole role) {this.role = role;}

    public UUID getAdministrationId() {return administrationId;}

    public void setAdministrationId(UUID administrationId) {this.administrationId = administrationId;}

    public UUID getDepartmentId() {return departmentId;}

    public void setDepartmentId(UUID departmentId) {this.departmentId = departmentId;}

    public String getMatriculeNumber() {return matriculeNumber;}

    public void setMatriculeNumber(String matriculeNumber) {this.matriculeNumber = matriculeNumber;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public LocalDate getBirthDate() {return birthDate;}

    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}

    public String getBirthPlace() {return birthPlace;}

    public void setBirthPlace(String birthPlace) {this.birthPlace = birthPlace;}

    public String getPosition() {return position;}

    public void setPosition(String position) {this.position = position;}

    public String getMaritalStatus() {return maritalStatus;}

    public void setMaritalStatus(String maritalStatus) {this.maritalStatus = maritalStatus;}


}