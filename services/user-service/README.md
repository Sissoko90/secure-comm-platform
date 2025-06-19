# User-Service

## Aperçu

Le User-Service est un microservice Spring Boot moderne qui gère la gestion des utilisateurs dans le système gouvernemental. Il fait partie d'un écosystème microservices et fournit une API REST complète pour la gestion des utilisateurs, des administrations et des départements.

## Architecture

- **Spring Boot 3.5.0** avec Spring Cloud 2025.0.0
- **Java 17** avec Jakarta EE
- **Spring Data JPA** pour l'accès aux données
- **MySQL** comme base de données principale
- **Spring Cloud** pour la découverte de services (Eureka)
- **Swagger/OpenAPI** pour la documentation de l'API
- **Lombok** pour réduire le boilerplate code
- **Validation Jakarta** pour la validation des données
- **Actuator** pour la surveillance et la gestion
- **Spring Data REST** pour l'exposition des APIs

## Fonctionnalités

### Gestion des Utilisateurs
- **CRUD complet des utilisateurs**
  - Création, mise à jour, suppression et consultation
  - Validation des données avec Jakarta Validation
  - Gestion des identifiants uniques (username, email, téléphone)
- **Organisation hiérarchique**
  - Association à une administration
  - Attribution à un département
  - Rôles définis (ADMIN, MANAGER, USER)
- **Informations personnelles**
  - Nom complet, adresse, date et lieu de naissance
  - Statut marital, position
  - Numéro matricule
- **Recherche et filtrage**
  - Recherche par username
  - Filtrage par administration et département
  - Pagination des résultats
- **Validation des données**
  - Format de téléphone spécifique (+223XXXXXXXXX)
  - Validation des emails
  - Contraintes de longueur sur les champs

### Gestion des Administrations
- **CRUD complet des administrations**
  - Création, mise à jour, suppression et consultation
  - Validation des noms d'administration
- **Organisation**
  - Hiérarchie avec départements
  - Association aux utilisateurs
- **Recherche**
  - Recherche par nom avec filtres
  - Liste paginée des administrations

### Gestion des Départements
- **CRUD complet des départements**
  - Création, mise à jour, suppression et consultation
  - Validation des noms de département
- **Organisation**
  - Hiérarchie au sein des administrations
  - Association aux utilisateurs
- **Recherche**
  - Recherche par nom avec filtres
  - Liste paginée des départements

## Installation et Configuration

### Prérequis
- Java 17
- Maven 3.8.6+
- Base de données MySQL 8+
- Docker (optionnel pour le déploiement)
- Spring Boot 3.5.0
- Spring Cloud 2025.0.0

### Configuration

Créez un fichier `application.properties` avec les configurations suivantes :

```properties
# Configuration temporaire pour désactiver Spring Cloud Config
# Permet de démarrer le service sans configuration externe
spring.config.import=optional:configserver:

# Base de données MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/user_data?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=${}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuration Hibernate/JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# Affichage des requêtes SQL dans les logs (utile pour le debug)
spring.jpa.show-sql=true

# Port du service
server.port=8090

# Configuration du service discovery
spring.cloud.discovery.enabled=true
spring.cloud.discovery.client.simple.instances=admin-service=localhost:8081
spring.cloud.discovery.client.simple.instances=auth-service=localhost:8082
spring.cloud.discovery.client.simple.instances=communication-service=localhost:8083
spring.cloud.discovery.client.simple.instances=notification-service=localhost:8084
spring.cloud.discovery.client.simple.instances=user-service=localhost:8090
```

### Build et Déploiement
```bash
# Build le projet
mvn clean install

# Démarrez le service
java -jar target/user-service.jar
```

## Documentation API

### Swagger UI
La documentation complète de l'API est disponible via Swagger UI :
```
http://localhost:8090/swagger-ui/
```

### Documentation des Endpoints
Un fichier détaillé des endpoints et leurs requêtes est disponible dans :
```
endpoints_test.txt
```

Ce fichier contient :
- La liste complète des endpoints
- Les méthodes HTTP
- Les URLs
- Les paramètres nécessaires
- Les données JSON attendues
- Les exemples de requêtes

### Fichier de Test
Un script de test des endpoints est disponible dans :
```
test_api_endpoints.sh
```

Ce script permet de tester toutes les fonctionnalités de l'API avec des exemples de données réalistes.

## Endpoints REST

### Utilisateurs (`/api/users`)

#### Opérations CRUD
- `POST /api/users` - Créer un nouvel utilisateur
  - Corps: `UserRequestDTO`
  - Réponse: `UserResponseDTO`
  - Status: 201 (Créé)
  - Headers: X-Message avec message de succès

- `PUT /api/users/{id}` - Mettre à jour un utilisateur
  - Corps: `UserRequestDTO`
  - Réponse: `UserResponseDTO`
  - Status: 200 (OK)
  - Headers: X-Message avec message de succès

- `DELETE /api/users/{id}` - Supprimer un utilisateur
  - Status: 204 (No Content)

- `GET /api/users/{id}` - Détails d'un utilisateur
  - Réponse: `UserResponseDTO`
  - Status: 200 (OK)

#### Recherche et Filtrage
- `GET /api/users/administration/{administrationId}?page={page}&size={size}`
  - Liste des utilisateurs d'une administration (paginée)
  - Réponse: `Page<UserResponseDTO>`
  - Status: 200 (OK)

- `GET /api/users/department/{departmentId}?page={page}&size={size}`
  - Liste des utilisateurs d'un département (paginée)
  - Réponse: `Page<UserResponseDTO>`
  - Status: 200 (OK)

- `GET /api/users/search?page={page}&size={size}`
  - Recherche avancée d'utilisateurs
  - Paramètres optionnels:
    - username
    - administrationId
    - departmentId
    - role
  - Réponse: `Page<UserResponseDTO>`
  - Status: 200 (OK)

### Administrations (`/api/administrations`)

#### Opérations CRUD
- `POST /api/administrations` - Créer une administration
  - Corps: `AdministrationRequestDTO`
  - Réponse: `AdministrationResponseDTO`
  - Status: 201 (Créé)

- `PUT /api/administrations/{id}` - Mettre à jour une administration
  - Corps: `AdministrationRequestDTO`
  - Réponse: `AdministrationResponseDTO`
  - Status: 200 (OK)

- `DELETE /api/administrations/{id}` - Supprimer une administration
  - Status: 204 (No Content)

- `GET /api/administrations/{id}` - Détails d'une administration
  - Réponse: `AdministrationResponseDTO`
  - Status: 200 (OK)

#### Recherche
- `GET /api/administrations/searchByName?name={name}`
  - Recherche d'administration par nom
  - Réponse: `List<AdministrationResponseDTO>`
  - Status: 200 (OK)

### Départements (`/api/departments`)

#### Opérations CRUD
- `POST /api/departments` - Créer un département
  - Corps: `DepartmentRequestDTO`
  - Réponse: `DepartmentResponseDTO`
  - Status: 201 (Créé)

- `PUT /api/departments/{id}` - Mettre à jour un département
  - Corps: `DepartmentRequestDTO`
  - Réponse: `DepartmentResponseDTO`
  - Status: 200 (OK)

- `DELETE /api/departments/{id}` - Supprimer un département
  - Status: 204 (No Content)

- `GET /api/departments/{id}` - Détails d'un département
  - Réponse: `DepartmentResponseDTO`
  - Status: 200 (OK)

#### Recherche
- `GET /api/departments/searchByName?name={name}`
  - Recherche de département par nom
  - Réponse: `List<DepartmentResponseDTO>`
  - Status: 200 (OK)

### Administrations (`/api/administrations`)
- `POST /api/administrations` - Créer une administration
- `PUT /api/administrations/{id}` - Mettre à jour une administration
- `DELETE /api/administrations/{id}` - Supprimer une administration
- `GET /api/administrations` - Liste des administrations
- `GET /api/administrations/searchByName` - Recherche d'administration par nom

### Départements (`/api/departments`)
- `POST /api/departments` - Créer un département
- `PUT /api/departments/{id}` - Mettre à jour un département
- `DELETE /api/departments/{id}` - Supprimer un département
- `GET /api/departments` - Liste des départements
- `GET /api/departments/searchByName` - Recherche de département par nom

## Technologies utilisées

### Frameworks et Libraries
- Spring Boot 3.5.0
- Spring Cloud 2025.0.0
- Spring Data JPA
- Spring Data REST
- Spring Boot Actuator
- Spring Cloud Config
- Spring Cloud Eureka
- Springdoc OpenAPI

### Base de données
- MySQL 8
- Hibernate 6.6.15.Final
- Jakarta Persistence 3.1.0

### Développement
- Java 17
- Lombok 1.18.30
- Hibernate Validator 8.0.1.Final
- Jakarta Validation 3.0.2

### Documentation
- Swagger/OpenAPI 2.5.0
- Springdoc OpenAPI UI

### Conteneurisation
- Docker
- Docker Compose

## Structure du projet

```
user-service/
├── controller/             → Contrôleurs REST
│   ├── UserController.java
│   ├── AdministrationController.java
│   └── DepartmentController.java
├── service/                → Logique métier
│   ├── UserService.java
│   ├── AdministrationService.java
│   └── DepartmentService.java
├── service/impl/          → Implémentations des services
│   ├── UserServiceImpl.java
│   ├── AdministrationServiceImpl.java
│   └── DepartmentServiceImpl.java
├── dto/                    → Objets de transfert (DTO)
│   ├── request/
│   │   ├── UserRequestDTO.java
│   │   ├── AdministrationRequestDTO.java
│   │   └── DepartmentRequestDTO.java
│   └── response/
│       ├── UserResponseDTO.java
│       ├── AdministrationResponseDTO.java
│       └── DepartmentResponseDTO.java
├── model/                  → Entités JPA
│   ├── User.java
│   ├── Administration.java
│   └── Department.java
├── repository/             → Interfaces Spring Data
│   ├── UserRepository.java
│   ├── AdministrationRepository.java
│   └── DepartmentRepository.java
├── config/                 → Configuration
│   ├── SwaggerConfig.java
│   └── application.properties
├── security/               → Sécurité
│   └── SecurityConfig.java
├── exception/              → Gestion des erreurs
│   ├── GlobalExceptionHandler.java
│   └── EntityNotFoundException.java
└── UserApplication.java
```

## Contribuer

1. Fork le projet
2. Créez votre branche de fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Poussez la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.

## Auteurs

- Makan Sissoko - Développeur principal

## Version

1.0.0 - Version initiale

server:
  port: 8092

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/userdb
    username: user_service
    password: secret
  jpa:
    hibernate.ddl-auto: update
    show-sql: false
    database-platform: org.hibernate.dialect.PostgreSQLDialect

jwt:
  secret: my-secret-key

### Sécurité

Toutes les routes sécurisées par token JWT

Vérification des rôles pour les actions sensibles (ex: modification de rôle)

Masquage des infos personnelles selon les permissions


### Monitoring

Spring Boot Actuator (/actuator/health, /metrics)

Intégration avec Spring Boot Admin


### Tests

Tests unitaires des contrôleurs et services

Tests de validation des rôles et accès


### À venir

Journalisation des accès sensibles via audit-service

Historique de modification du profil

Activation / désactivation temporaire des comptes (admin)