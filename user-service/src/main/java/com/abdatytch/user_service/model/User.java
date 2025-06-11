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

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entité JPA représentant un utilisateur
 * 
 * <p>
 * Cette classe est mappée à la table 'users' dans la base de données et contient
 * les informations essentielles d'un utilisateur, y compris :
 * <ul>
 *     <li>Identifiant unique</li>
 *     <li>Informations d'identification</li>
 *     <li>Informations personnelles</li>
 *     <li>Informations organisationnelles</li>
 * </ul>
 */
@Entity
@Table(name = "users")
@Data
public class User {

    /**
     * Identifiant unique de l'utilisateur (auto-généré)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom d'utilisateur unique pour l'authentification
     * 
     * @constraint Non-null et unique dans le système
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Nom complet de l'utilisateur
     * 
     * @constraint Non-null
     */
    @Column(nullable = false)
    private String fullName;

    /**
     * Rôle de l'utilisateur dans le système
     * 
     * @constraint Non-null
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    /**
     * Administration à laquelle l'utilisateur appartient
     * 
     * @constraint Relation ManyToOne avec Administration
     */
    @ManyToOne
    @JoinColumn(name = "administration_id")
    private Administration administration;

    /**
     * Département à laquelle l'utilisateur appartient
     * 
     * @constraint Relation ManyToOne avec Department
     */
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    /**
     * Constructeur par défaut requis par JPA
     */
    public User() {}

    /**
     * Constructeur avec paramètres essentiels
     * 
     * @param username Nom d'utilisateur
     * @param fullName Nom complet
     * @param role Rôle de l'utilisateur
     */
    public User(String username, String fullName, UserRole role) {
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }
}
