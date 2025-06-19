# Documentation Technique - User-Service

## 1. Vue d'ensemble

Le User-Service est un microservice Spring Boot qui gère la gestion des utilisateurs dans le système de communication gouvernementale sécurisée. Il utilise Spring Boot 3.5.0 et Java 17.

## 2. Architecture

### 2.1 Composants Principaux

#### 2.1.1 Modèles de Données

1. **User**
   - Identifiant unique (ID)
   - Nom d'utilisateur (username)

## 3. Endpoints API

### 3.1 Gestion des Utilisateurs (`/api/users`)

#### 3.1.1 Création d'un utilisateur
- **Méthode**: POST
- **Endpoint**: `/api/users`
- **Description**: Crée un nouvel utilisateur
- **Requête**: `UserRequestDTO`
- **Réponse**: `UserResponseDTO`
- **Code**: 201 (Created)

#### 3.1.2 Mise à jour d'un utilisateur
- **Méthode**: PUT
- **Endpoint**: `/api/users/{id}`
- **Description**: Met à jour les informations d'un utilisateur existant
- **Requête**: `UserRequestDTO`
- **Réponse**: `UserResponseDTO`
- **Code**: 200 (OK)

#### 3.1.3 Suppression d'un utilisateur
- **Méthode**: DELETE
- **Endpoint**: `/api/users/{id}`
- **Description**: Supprime un utilisateur
- **Code**: 204 (No Content)

#### 3.1.4 Liste des utilisateurs (paginée)
- **Méthode**: GET
- **Endpoint**: `/api/users`
- **Description**: Récupère la liste des utilisateurs avec pagination
- **Paramètres**: 
  - `page`: Numéro de page (0-indexé, défaut: 0)
  - `size`: Taille de page (défaut: 10)
- **Réponse**: `Page<UserResponseDTO>`
- **Code**: 200 (OK)

#### 3.1.5 Détails d'un utilisateur
- **Méthode**: GET
- **Endpoint**: `/api/users/{id}`
- **Description**: Récupère les détails d'un utilisateur spécifique
- **Réponse**: `UserResponseDTO`
- **Code**: 200 (OK) ou 404 (Not Found)

#### 3.1.6 Liste des utilisateurs d'une administration
- **Méthode**: GET
- **Endpoint**: `/api/users/administration/{administrationId}`
- **Description**: Liste les utilisateurs appartenant à une administration
- **Paramètres**: 
  - `page`: Numéro de page (0-indexé, défaut: 0)
  - `size`: Taille de page (défaut: 10)
- **Réponse**: `Page<UserResponseDTO>`
- **Code**: 200 (OK)

#### 3.1.7 Liste des utilisateurs d'un département
- **Méthode**: GET
- **Endpoint**: `/api/users/department/{departmentId}`
- **Description**: Liste les utilisateurs appartenant à un département
- **Paramètres**: 
  - `page`: Numéro de page (0-indexé, défaut: 0)
  - `size`: Taille de page (défaut: 10)
- **Réponse**: `Page<UserResponseDTO>`
- **Code**: 200 (OK)

#### 3.1.8 Recherche avancée d'utilisateurs
- **Méthode**: GET
- **Endpoint**: `/api/users/search`
- **Description**: Recherche d'utilisateurs avec filtres multiples
- **Paramètres**: 
  - `username`: Nom d'utilisateur (optionnel)
  - `administrationId`: ID d'administration (optionnel)
  - `departmentId`: ID de département (optionnel)
  - `role`: Rôle de l'utilisateur (optionnel)
  - `page`: Numéro de page (0-indexé, défaut: 0)
  - `size`: Taille de page (défaut: 10)
- **Réponse**: `Page<UserResponseDTO>`
- **Code**: 200 (OK)

### 3.2 Gestion des Administrations (`/api/administrations`)

#### 3.2.1 Création d'une administration
- **Méthode**: POST
- **Endpoint**: `/api/administrations`
- **Description**: Crée une nouvelle administration
- **Requête**: `AdministrationRequestDTO`
- **Réponse**: `AdministrationResponseDTO`
- **Code**: 201 (Created)

#### 3.2.2 Mise à jour d'une administration
- **Méthode**: PUT
- **Endpoint**: `/api/administrations/{id}`
- **Description**: Met à jour les informations d'une administration
- **Requête**: `AdministrationRequestDTO`
- **Réponse**: `AdministrationResponseDTO`
- **Code**: 200 (OK)

#### 3.2.3 Suppression d'une administration
- **Méthode**: DELETE
- **Endpoint**: `/api/administrations/{id}`
- **Description**: Supprime une administration
- **Code**: 204 (No Content)

#### 3.2.4 Liste des administrations
- **Méthode**: GET
- **Endpoint**: `/api/administrations`
- **Description**: Récupère la liste de toutes les administrations
- **Réponse**: `List<AdministrationResponseDTO>`
- **Code**: 200 (OK)

#### 3.2.5 Recherche d'une administration par nom
- **Méthode**: GET
- **Endpoint**: `/api/administrations/searchByName`
- **Description**: Recherche une administration par son nom
- **Paramètres**: 
  - `name`: Nom de l'administration
- **Réponse**: `AdministrationResponseDTO`
- **Code**: 200 (OK) ou 404 (Not Found)

### 3.3 Gestion des Départements (`/api/departments`)

#### 3.3.1 Création d'un département
- **Méthode**: POST
- **Endpoint**: `/api/departments`
- **Description**: Crée un nouveau département
- **Requête**: `DepartmentRequestDTO`
- **Réponse**: `DepartmentResponseDTO`
- **Code**: 201 (Created)

#### 3.3.2 Mise à jour d'un département
- **Méthode**: PUT
- **Endpoint**: `/api/departments/{id}`
- **Description**: Met à jour les informations d'un département
- **Requête**: `DepartmentRequestDTO`
- **Réponse**: `DepartmentResponseDTO`
- **Code**: 200 (OK)

#### 3.3.3 Suppression d'un département
- **Méthode**: DELETE
- **Endpoint**: `/api/departments/{id}`
- **Description**: Supprime un département
- **Code**: 204 (No Content)

#### 3.3.4 Liste des départements
- **Méthode**: GET
- **Endpoint**: `/api/departments`
- **Description**: Récupère la liste de tous les départements
- **Réponse**: `List<DepartmentResponseDTO>`
- **Code**: 200 (OK)

#### 3.3.5 Recherche d'un département par nom
- **Méthode**: GET
- **Endpoint**: `/api/departments/searchByName`
- **Description**: Recherche un département par son nom
- **Paramètres**: 
  - `name`: Nom du département
- **Réponse**: `DepartmentResponseDTO`
- **Code**: 200 (OK) ou 404 (Not Found)
   - Nom complet (fullName)
   - Rôle (UserRole)
   - Administration associée
   - Département associé

2. **Administration**
   - Structure hiérarchique de l'organisation
   - Gestion des utilisateurs administratifs

3. **Department**
   - Structure organisationnelle
   - Gestion des utilisateurs par département

### 2.2 Dépendances Principales

- Spring Boot 3.5.0
- Spring Cloud Config
- Spring Cloud Eureka Client
- Spring Data JPA
- Spring Data REST
- Spring Boot Actuator
- MySQL Connector
- Lombok
- OpenAPI/Swagger

## 3. Configuration

### 3.1 Base de Données

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/user_data?createDatabaseIfNotExist=true
    username: root
    password: ${}
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
```

### 3.2 Sécurité

- Authentification JWT
- Validation des permissions
- Audit des actions utilisateur
- Chiffrement des données sensibles

## 4. API Endpoints

L'API est documentée via Swagger UI accessible à l'adresse : `http://localhost:8080/swagger-ui.html`

## 5. Fonctionnalités

### 5.1 Gestion des Utilisateurs

- Création et mise à jour des profils
- Gestion des rôles et permissions
- Organisation hiérarchique (administrations et départements)
- Validation des accès

### 5.2 Sécurité

- Authentification JWT
- Validation des permissions
- Audit des actions
- Chiffrement des données sensibles

## 6. Monitoring et Métriques

- Health Check via Spring Boot Actuator
- Métriques d'utilisation
- Logs structurés
- Alertes en cas de problèmes

## 7. Déploiement

### 7.1 Prérequis

- Java 17+
- Maven 3.8.6+
- Base de données MySQL
- Docker (optionnel)

### 7.2 Build et Exécution

```bash
# Build
mvn clean install

# Exécution
java -jar target/user-service.jar
```

## 8. Structure du Projet

```
user-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/abdatytch/user_service/
│   │   │       ├── config/           # Configuration du service
│   │   │       ├── controller/       # Contrôleurs REST
│   │   │       ├── dto/              # DTOs pour les échanges
│   │   │       ├── model/            # Modèles de données
│   │   │       ├── repository/       # Repositories JPA
│   │   │       └── service/          # Services métier
│   │   └── resources/
│   │       ├── application.yml       # Configuration principale
│   │       └── messages.properties    # Messages internationalisés
└── pom.xml                           # Dépendances Maven
```

## 9. Bonnes Pratiques

1. **Security**
   - Toujours utiliser l'authentification JWT
   - Valider les permissions avant toute action
   - Chiffrer les données sensibles

2. **Code**
   - Utiliser Lombok pour réduire le boilerplate
   - Suivre les principes SOLID
   - Documenter les API avec Swagger

3. **Déploiement**
   - Utiliser des variables d'environnement pour les configurations sensibles
   - Suivre les principes 12-factor app
   - Documenter les changements importants

## 10. Contribuer

1. Fork le projet
2. Créez votre branche de fonctionnalité
3. Committez vos changements
4. Poussez la branche
5. Ouvrez une Pull Request

## 11. Support et Maintenance

- Documentation API via Swagger UI
- Logs structurés pour le débogage
- Métriques pour le monitoring
- Documentation technique complète
