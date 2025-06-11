# User-Service

## Aperçu

Le User-Service est un microservice Spring Boot qui gère la gestion des utilisateurs dans le système de communication gouvernementale sécurisée. Il fournit une API REST complète pour la gestion des utilisateurs, des administrations et des départements.

## Fonctionnalités

### Gestion des Utilisateurs
- CRUD complet des utilisateurs
- Recherche avancée avec filtres multiples
- Organisation par administration et département
- Gestion des rôles et permissions
- Pagination des résultats

### Gestion des Administrations
- CRUD complet des administrations
- Recherche par nom
- Liste de toutes les administrations

### Gestion des Départements
- CRUD complet des départements
- Recherche par nom
- Liste de tous les départements

## Installation et Configuration

### Prérequis
- Java 17+
- Maven 3.8.6+
- Base de données MySQL
- Docker (optionnel pour le déploiement)

### Configuration

Créez un fichier `application.properties` avec les configurations suivantes :

```properties
# Base de données
spring.datasource.url=jdbc:mysql://localhost:3306/user_data?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=${}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Sécurité JWT
security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/auth/realms/secure-comm

# Port du service
server.port=8090
```

### Build et Déploiement
```bash
# Build le projet
mvn clean install

# Démarrez le service
java -jar target/user-service.jar
```

## Documentation API

La documentation complète de l'API est disponible via Swagger UI :
```
http://localhost:8090/swagger-ui/
```

## Endpoints REST

### Utilisateurs (`/api/users`)
- `POST /api/users` - Créer un nouvel utilisateur
- `PUT /api/users/{id}` - Mettre à jour un utilisateur
- `DELETE /api/users/{id}` - Supprimer un utilisateur
- `GET /api/users` - Liste des utilisateurs (paginée)
- `GET /api/users/{id}` - Détails d'un utilisateur
- `GET /api/users/administration/{administrationId}` - Liste des utilisateurs d'une administration
- `GET /api/users/department/{departmentId}` - Liste des utilisateurs d'un département
- `GET /api/users/search` - Recherche avancée d'utilisateurs

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

- Spring Boot 3.5.0
- Spring Cloud Config
- Spring Cloud Eureka Client
- Spring Data JPA
- Spring Data REST
- Spring Boot Actuator
- MySQL Connector
- Lombok
- OpenAPI/Swagger

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