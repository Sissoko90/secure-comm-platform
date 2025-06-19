/**
 * Modèle représentant un utilisateur dans le système
 * 
 * Cette classe contient toutes les informations essentielles d'un utilisateur,
 * y compris ses identifiants, ses informations personnelles, son rôle et
 * ses affiliations organisationnelles.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.model;

import java.util.UUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import com.abdatytch.user_service.constant.Message;



@Entity
@Table(name = "users")
@Schema(description = "Utilisateur du système")
public class User {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = Message.USERNAME_REQUIRED)
    @Size(min = 3, max = 50, message = Message.USERNAME_TOO_SHORT + " et " + Message.USERNAME_TOO_LONG)
    private String username;

    @Column(nullable = false)
    @Size(min = 8, max = 100, message = Message.PASSWORD_TOO_SHORT + " et " + Message.PASSWORD_TOO_LONG)
    private String password;


    @Column(nullable = false)
    @NotBlank(message = Message.FIRSTNAME_REQUIRED)
    @Size(min = 2, max = 50, message = Message.FIRSTNAME_TOO_SHORT + " et " + Message.FIRSTNAME_TOO_LONG)
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = Message.LASTNAME_REQUIRED)
    @Size(min = 2, max = 100, message = Message.LASTNAME_TOO_SHORT + " et " + Message.LASTNAME_TOO_LONG)
    private String lastName;

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getFullName() {return firstName + " " + lastName;}

    @Column(nullable = false, unique = true)
    @NotBlank(message = Message.PHONE_NUMBER_REQUIRED)
    @Pattern(regexp = "^[0-9]{8}$", message = Message.PHONE_NUMBER_INVALID)
    @Schema(description = "Numéro de téléphone unique de 8 chiffres")
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    @NotBlank(message = Message.EMAIL_REQUIRED)
    @Email(message = Message.EMAIL_INVALID)
    @Schema(description = "Email unique de l'utilisateur")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = Message.ADDRESS_REQUIRED)
    @Size(min = 10, max = 200, message = Message.ADDRESS_TOO_SHORT + " et " + Message.ADDRESS_TOO_LONG)
    @Schema(description = "Adresse de l'utilisateur (10-200 caractères)")
    private String address;

    @NotNull(message = Message.BIRTH_DATE_REQUIRED)
    @Past(message = Message.BIRTH_DATE_INVALID)
    @Schema(description = "Date de naissance de l'utilisateur")
    private LocalDate birthDate;

    @Column(nullable = false)
    @NotBlank(message = Message.BIRTH_PLACE_REQUIRED)
    @Size(min = 3, max = 100, message = Message.BIRTH_PLACE_TOO_SHORT + " et " + Message.BIRTH_PLACE_TOO_LONG)
    @Schema(description = "Lieu de naissance de l'utilisateur (3-100 caractères)")
    private String birthPlace;

    @Column(nullable = false)
    @NotBlank(message = Message.POSITION_REQUIRED)
    @Size(min = 2, max = 100, message = Message.POSITION_TOO_SHORT + " et " + Message.POSITION_TOO_LONG)
    @Schema(description = "Poste occupé par l'utilisateur (2-100 caractères)")
    private String position;

    @Column(nullable = false)
    @NotBlank(message = Message.MARITAL_STATUS_REQUIRED)
    @Size(min = 2, max = 50, message = Message.MARITAL_STATUS_TOO_SHORT + " et " + Message.MARITAL_STATUS_TOO_LONG)
    @Schema(description = "Statut matrimonial de l'utilisateur (2-50 caractères)")
    private String maritalStatus;

    @Column(nullable = false, unique = true)
    @NotBlank(message = Message.MATRICULE_NUMBER_REQUIRED)
    @Size(min = 5, max = 20, message = Message.MATRICULE_NUMBER_TOO_SHORT + " et " + Message.MATRICULE_NUMBER_TOO_LONG)
    @Schema(description = "Numéro matricule unique de l'utilisateur (5-20 caractères)")
    private String matriculeNumber;

    /**
     * Rôle de l'utilisateur dans le système
     * 
     * @constraint Non-null
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Rôle de l'utilisateur dans le système")
    private UserRole role;

    /**
     * Administration à laquelle l'utilisateur appartient
     * 
     * @constraint Relation ManyToOne avec Administration
     */
    @ManyToOne
    @JoinColumn(name = "administration_id", nullable = false)
    @NotNull(message = Message.ADMINISTRATION_ID_REQUIRED)
    @Schema(description = "Administration à laquelle l'utilisateur appartient")
    private Administration administration;

    /**
     * Département à laquelle l'utilisateur appartient
     * 
     * @constraint Relation ManyToOne avec Department
     */
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @NotNull(message = Message.DEPARTMENT_ID_REQUIRED)
    @Schema(description = "Département à laquelle l'utilisateur appartient")
    private Department department;

    /**
     * Constructeur avec paramètres essentiels
     * 
     * @param username Nom d'utilisateur
     * @param firstName Prénom de l'utilisateur
     * @param lastName Nom de famille de l'utilisateur
     * @param role Rôle de l'utilisateur
     */
    public User() {}

    public User(String username, String firstName, String lastName, UserRole role) {
        // Constructeur avec les champs essentiels
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    // Getters et Setters
    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

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

    public String getMatriculeNumber() {return matriculeNumber;}

    public void setMatriculeNumber(String matriculeNumber) {this.matriculeNumber = matriculeNumber;}

    public UserRole getRole() {return role;}

    public void setRole(UserRole role) {this.role = role;}

    public Administration getAdministration() {return administration;}

    public void setAdministration(Administration administration) {this.administration = administration;}

    public Department getDepartment() {return department;}

    public void setDepartment(Department department) {this.department = department;}
}
