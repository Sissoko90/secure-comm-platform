/**
 * Entité représentant une administration
 * 
 * Une administration est une entité organisationnelle qui peut contenir
 * plusieurs départements et utilisateurs.
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
 * Entité représentant une administration
 * 
 * <p>
 * Cette entité contient les informations suivantes :
 * <ul>
 *     <li>Informations de base (id, nom)</li>
 *     <li>Relations avec les départements (un à plusieurs)</li>
 *     <li>Relations avec les utilisateurs (un à plusieurs)</li>
 * </ul>
 */
@Entity
@Table(name = "administrations")
public class Administration {
    /**
     * Identifiant unique de l'administration
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom de l'administration (unique)
     * 
     * @constraint Non-null et unique dans le système
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Départements appartenant à l'administration
     * 
     * @constraint Cascade ALL avec suppression en cascade
     */
    @OneToMany(mappedBy = "administration", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Department> departments = new HashSet<>();

    /**
     * Utilisateurs appartenant à l'administration
     */
    @OneToMany(mappedBy = "administration")
    private Set<User> users = new HashSet<>();

    /**
     * Constructeur par défaut
     */
    public Administration() {}

    /**
     * Constructeur avec nom
     * 
     * @param name Nom de l'administration
     */
    public Administration(String name) {
        this.name = name;
    }

    /**
     * Obtient l'identifiant de l'administration
     * @return Identifiant
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'administration
     * @param id Nouvel identifiant
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtient le nom de l'administration
     * @return Nom
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de l'administration
     * @param name Nouveau nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtient la liste des départements
     * @return Départements
     */
    public Set<Department> getDepartments() {
        return departments;
    }

    /**
     * Définit la liste des départements
     * @param departments Nouvelle liste de départements
     */
    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
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

    /**
     * Ajoute un département à l'administration
     * 
     * @param department Département à ajouter
     */
    public void addDepartment(Department department) {
        departments.add(department);
        department.setAdministration(this);
    }

    /**
     * Supprime un département de l'administration
     * 
     * @param department Département à supprimer
     */
    public void removeDepartment(Department department) {
        departments.remove(department);
        department.setAdministration(null);
    }
}
