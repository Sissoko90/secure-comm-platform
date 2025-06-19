/**
 * Entité représentant une administration.
 * Entité organisationnelle contenant des départements et utilisateurs.
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
@Table(name = "administrations")
public class Administration {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;


    @Column(nullable = false, unique = true)
    private String name;


    @OneToMany(mappedBy = "administration", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Department> departments = new HashSet<>();

    @OneToMany(mappedBy = "administration")
    private Set<User> users = new HashSet<>();

    //Constructor
    public Administration() {}

    //Getters and Setters
    public Administration(String name) {this.name = name;}

    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Set<Department> getDepartments() {return departments;}

    public void setDepartments(Set<Department> departments) {this.departments = departments;}

    public Set<User> getUsers() {return users;}

    public void setUsers(Set<User> users) {this.users = users;}

    public void addDepartment(Department department) {
        departments.add(department);
        department.setAdministration(this);
    }


    public void removeDepartment(Department department) {
        departments.remove(department);
        department.setAdministration(null);
    }
}
