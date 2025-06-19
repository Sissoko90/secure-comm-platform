/**
 * Entité représentant un département.
 * Unité organisationnelle appartenant à une administration et contenant des utilisateurs.
 * 
 * @author Makan Sissoko
 * @version 1.0
 * @since 2025-06-11
 */
package com.abdatytch.user_service.model;

import java.util.UUID;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

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


    // Constructeur avec tous les arguments
    public Department(UUID id, String name, Administration administration) {
        this.id = id;
        this.name = name;
        this.administration = administration;
    }
    // Constructeur par défaut
    public Department() {}

    // Getters et Setters
    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Department(String name) {this.name = name;}

    public Administration getAdministration() {return administration;}

    public void setAdministration(Administration administration) {this.administration = administration;}

    public Set<User> getUsers() {return users;}

    public void setUsers(Set<User> users) {this.users = users;}

    
}
