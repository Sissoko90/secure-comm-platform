/**
 * Entité représentant un département
 * 
 * Un département est une unité organisationnelle qui appartient à une administration
 * et peut contenir plusieurs utilisateurs.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entité représentant un département
 * 
 * <p>
 * Cette entité contient les informations suivantes :
 * <ul>
 *     <li>Informations de base (id, nom)</li>
 *     <li>Relation avec l'administration parente (un à un)</li>
 *     <li>Relations avec les utilisateurs (un à plusieurs)</li>
 * </ul>
 */
@Entity
@Table(name = "departments")
public class Department {
    /**
     * Identifiant unique du département
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom du département
     * 
     * @constraint Non-null
     */
    @Column(nullable = false)
    private String name;

    /**
     * Administration parente du département
     * 
     * @constraint Relation un à plusieurs avec Administration
     */
    @ManyToOne
    @JoinColumn(name = "administration_id")
    private Administration administration;

    /**
     * Utilisateurs appartenant au département
     */
    @OneToMany(mappedBy = "department")
    private Set<User> users = new HashSet<>();

    /**
     * Constructeur par défaut
     */
    public Department() {}

    /**
     * Constructeur avec nom
     * 
     * @param name Nom du département
     */
    public Department(String name) {
        this.name = name;
    }

    /**
     * Obtient l'identifiant du département
     * @return Identifiant
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du département
     * @param id Nouvel identifiant
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtient le nom du département
     * @return Nom
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du département
     * @param name Nouveau nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtient l'administration parente
     * @return Administration
     */
    public Administration getAdministration() {
        return administration;
    }

    /**
     * Définit l'administration parente
     * @param administration Nouvelle administration
     */
    public void setAdministration(Administration administration) {
        this.administration = administration;
    }

    /**
     * Obtient la liste des utilisateurs
     * @return Utilisateurs
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Définit la liste des utilisateurs
     * @param users Nouvelle liste d'utilisateurs
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
