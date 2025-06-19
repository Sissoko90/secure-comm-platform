/**
 * DTO de réponse pour les utilisateurs
 * 
 * Ce DTO contient les informations détaillées d'un utilisateur, y compris
 * ses rôles et les informations de son administration et département.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.dto.response;

import java.util.UUID;
import com.abdatytch.user_service.model.UserRole;

/**
 * DTO de réponse pour les utilisateurs
 */
public class UserResponseDTO {

    private UUID id;

    private String username;

    private String firstName;
    private String lastName;

    private UserRole role;

    private UUID administrationId;

    private String administrationName;

    private UUID departmentId;

    private String departmentName;

    //Constructor
    public UserResponseDTO() {}

    //Getters and Setters
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

    public String getAdministrationName() {return administrationName;}

    public void setAdministrationName(String administrationName) {this.administrationName = administrationName;}

    public UUID getDepartmentId() {return departmentId;}

    public void setDepartmentId(UUID departmentId) {this.departmentId = departmentId;}

    public String getDepartmentName() {return departmentName;}

    public void setDepartmentName(String departmentName) {this.departmentName = departmentName;}
}
