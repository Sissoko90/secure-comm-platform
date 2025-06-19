package com.abdatytch.user_service.constant;

public class Message {
    // Messages de succès
    public static final String USER_CREATED_SUCCESS = "Utilisateur créé avec succès";
    public static final String USER_UPDATED_SUCCESS = "Utilisateur mis à jour avec succès";
    public static final String USER_DELETED_SUCCESS = "Utilisateur supprimé avec succès";
    public static final String USER_CREDENTIALS_UPDATED_SUCCESS = "Identifiants mis à jour avec succès";
    public static final String USER_FOUND = "Utilisateur trouvé";
    public static final String USERS_FOUND = "Liste des utilisateurs récupérée avec succès";
    public static final String ADMINISTRATION_CREATED_SUCCESS = "Administration créée avec succès";
    public static final String ADMINISTRATION_UPDATED_SUCCESS = "Administration mise à jour avec succès";
    public static final String ADMINISTRATION_DELETED_SUCCESS = "Administration supprimée avec succès";
    public static final String ADMINISTRATION_FOUND = "Administration trouvée";
    public static final String ADMINISTRATIONS_FOUND = "Liste des administrations récupérée avec succès";
    public static final String DEPARTMENT_CREATED_SUCCESS = "Département créé avec succès";
    public static final String DEPARTMENT_UPDATED_SUCCESS = "Département mis à jour avec succès";
    public static final String DEPARTMENT_DELETED_SUCCESS = "Département supprimé avec succès";
    public static final String DEPARTMENT_FOUND = "Département trouvé";
    public static final String DEPARTMENTS_FOUND = "Liste des départements récupérée avec succès";
    
    // Messages de validation pour les identifiants
    public static final String USERNAME_REQUIRED = "Le nom d'utilisateur est requis";
    public static final String USERNAME_TOO_SHORT = "Le nom d'utilisateur doit contenir au moins 3 caractères";
    public static final String USERNAME_TOO_LONG = "Le nom d'utilisateur ne doit pas dépasser 50 caractères";
    public static final String PASSWORD_REQUIRED = "Le mot de passe est requis";
    public static final String PASSWORD_TOO_SHORT = "Le mot de passe doit contenir au moins 8 caractères";
    public static final String PASSWORD_TOO_LONG = "Le mot de passe ne doit pas dépasser 100 caractères";

    // Messages de validation pour le prénom et le nom
    public static final String FIRSTNAME_REQUIRED = "Le prénom est requis";
    public static final String FIRSTNAME_TOO_SHORT = "Le prénom doit contenir au moins 2 caractères";
    public static final String FIRSTNAME_TOO_LONG = "Le prénom ne doit pas dépasser 50 caractères";
    public static final String LASTNAME_REQUIRED = "Le nom est requis";
    public static final String LASTNAME_TOO_SHORT = "Le nom doit contenir au moins 2 caractères";
    public static final String LASTNAME_TOO_LONG = "Le nom ne doit pas dépasser 100 caractères";
    
    // Messages d'erreur
    public static final String USER_NOT_FOUND = "Utilisateur non trouvé";
    public static final String USER_ALREADY_EXISTS = "Un utilisateur avec ce nom existe déjà";
    public static final String USER_UPDATE_FAILED = "Échec de la mise à jour de l'utilisateur";
    public static final String USER_DELETE_FAILED = "Échec de la suppression de l'utilisateur";
    public static final String INVALID_USER_DATA = "Données utilisateur invalides";
    public static final String ADMINISTRATION_NOT_FOUND = "Administration non trouvée";
    public static final String ADMINISTRATION_ALREADY_EXISTS = "Une administration avec ce nom existe déjà";
    public static final String ADMINISTRATION_UPDATE_FAILED = "Échec de la mise à jour de l'administration";
    public static final String ADMINISTRATION_DELETE_FAILED = "Échec de la suppression de l'administration";
    public static final String INVALID_ADMINISTRATION_DATA = "Données d'administration invalides";
    public static final String DEPARTMENT_NOT_FOUND = "Département non trouvé";
    public static final String DEPARTMENT_ALREADY_EXISTS = "Un département avec ce nom existe déjà";
    public static final String DEPARTMENT_UPDATE_FAILED = "Échec de la mise à jour du département";
    public static final String DEPARTMENT_DELETE_FAILED = "Échec de la suppression du département";
    public static final String INVALID_DEPARTMENT_DATA = "Données de département invalides";
    
    // Messages de validation pour l'administration
    public static final String ADMINISTRATION_NAME_INVALID = "Le nom de l'administration ne peut pas être null";
    public static final String ADMINISTRATION_NAME_REQUIRED = "Le nom de l'administration est requis";
    public static final String ADMINISTRATION_NAME_TOO_SHORT = "Le nom de l'administration doit contenir au moins 2 caractères";
    public static final String ADMINISTRATION_NAME_TOO_LONG = "Le nom de l'administration ne doit pas dépasser 100 caractères";
    
    // Messages de validation pour le département
    public static final String DEPARTMENT_NAME_REQUIRED = "Le nom du département est requis";
    public static final String DEPARTMENT_NAME_TOO_SHORT = "Le nom du département doit contenir au moins 2 caractères";
    public static final String DEPARTMENT_NAME_TOO_LONG = "Le nom du département ne doit pas dépasser 100 caractères";
    public static final String ADMINISTRATION_ID_REQUIRED = "L'identifiant de l'administration est requis";
    


    // Messages d'erreur pour les identifiants
public static final String INVALID_OLD_PASSWORD = "L'ancien mot de passe ne correspond pas";
public static final String USERNAME_ALREADY_EXISTS = "Ce username est déjà utilisé";

    // Messages de validation pour le mot de passe

    public static final String PASSWORD_INVALID = "Le mot de passe doit contenir au moins une majuscule, une minuscule et un chiffre";
    
    // Messages de validation pour la mise à jour des identifiants
    public static final String OLD_PASSWORD_REQUIRED = "L'ancien mot de passe est requis";
    public static final String OLD_PASSWORD_INCORRECT = "L'ancien mot de passe est incorrect";
    public static final String USERNAME_UPDATED_SUCCESS = "Le username a été mis à jour avec succès";
    public static final String PASSWORD_UPDATED_SUCCESS = "Le mot de passe a été mis à jour avec succès";
    public static final String CREDENTIALS_UPDATE_FAILED = "La mise à jour des identifiants a échoué";

    // Messages pour les champs utilisateur
    public static final String PHONE_NUMBER_REQUIRED = "Le numéro de téléphone est requis";
    public static final String PHONE_NUMBER_INVALID = "Le numéro de téléphone doit contenir entre 8 et 9 chiffres";
    public static final String EMAIL_REQUIRED = "L'email est requis";
    public static final String EMAIL_INVALID = "L'email n'est pas valide";
    public static final String ADDRESS_REQUIRED = "L'adresse est requise";
    public static final String ADDRESS_TOO_SHORT = "L'adresse doit contenir au moins 10 caractères";
    public static final String ADDRESS_TOO_LONG = "L'adresse ne doit pas dépasser 200 caractères";
    public static final String BIRTH_DATE_REQUIRED = "La date de naissance est requise";
    public static final String BIRTH_DATE_INVALID = "La date de naissance doit être une date passée";
    public static final String BIRTH_PLACE_REQUIRED = "Le lieu de naissance est requis";
    public static final String BIRTH_PLACE_TOO_SHORT = "Le lieu de naissance doit contenir au moins 3 caractères";
    public static final String BIRTH_PLACE_TOO_LONG = "Le lieu de naissance ne doit pas dépasser 100 caractères";
    public static final String POSITION_REQUIRED = "Le poste est requis";
    public static final String POSITION_TOO_SHORT = "Le poste doit contenir au moins 2 caractères";
    public static final String POSITION_TOO_LONG = "Le poste ne doit pas dépasser 100 caractères";
    public static final String MARITAL_STATUS_REQUIRED = "La situation matrimoniale est requise";
    public static final String MARITAL_STATUS_TOO_SHORT = "La situation matrimoniale doit contenir au moins 2 caractères";
    public static final String MARITAL_STATUS_TOO_LONG = "La situation matrimoniale ne doit pas dépasser 50 caractères";
    public static final String MATRICULE_NUMBER_REQUIRED = "Le numéro matricule est requis";
    public static final String MATRICULE_NUMBER_TOO_SHORT = "Le numéro matricule doit contenir au moins 5 caractères";
    public static final String MATRICULE_NUMBER_TOO_LONG = "Le numéro matricule ne doit pas dépasser 20 caractères";
    public static final String DUPLICATE_EMAIL = "L'email est déjà utilisé";
    public static final String DUPLICATE_PHONE_NUMBER = "Le numéro de téléphone est déjà utilisé";
    public static final String DUPLICATE_MATRICULE = "Le numéro matricule est déjà utilisé";
    
    // Messages de recherche
    public static final String USERS_BY_ADMINISTRATION_FOUND = "Liste des utilisateurs de l'administration récupérée avec succès";
    public static final String USERS_BY_DEPARTMENT_FOUND = "Liste des utilisateurs du département récupérée avec succès";
    public static final String USERS_SEARCH_RESULTS = "Résultats de la recherche d'utilisateurs";
    
    // Messages de validation

    public static final String ROLE_REQUIRED = "Le rôle est requis";
    public static final String DEPARTMENT_ID_REQUIRED = "L'identifiant du département est requis";
    
    private Message() {
        throw new IllegalStateException("Cette classe est une classe utilitaire et ne peut pas être instanciée");
    }
}
