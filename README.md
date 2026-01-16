# ERP Freelance - Prospecting Management Platform

**[🇫🇷 Français](#français) | [🇬🇧 English](#english)**

---

<a id="english"></a>

## English Version

### 📋 Project Overview

**ERP Freelance** is a comprehensive prospecting management platform designed specifically for freelance developers. The application provides an intuitive, data-driven approach to help freelancers manage client outreach through campaigns, contacts, companies, and automated workflows.

**Problem Statement:** Freelance developers often struggle with marketing and business development, leading to longer periods between contracts. This application makes prospecting systematic, measurable, and developer-friendly.

### 🛠️ Technology Stack

| Layer | Technologies |
|-------|--------------|
| **Backend** | Spring Boot 4.0.1, Java 17, PostgreSQL, JPA/Hibernate, Spring Data JPA |
| **Frontend** | Angular 21, TypeScript, RxJS |
| **Architecture** | RESTful API, Monolithic, Docker containerized |
| **Methodology** | Data-driven, Iterative, Agile |
| **DevOps** | Docker, Docker Compose |

### 🏗️ Architecture

#### Backend Architecture

**Package Structure:**
```
com.fredlecoat.erp_freelance
├── domain/
│   ├── entities/          # JPA entities with Lombok
│   ├── entities/dtos/     # Request/Response DTOs
│   ├── entities/mappers/  # Entity to DTO conversion
│   ├── entities/values/   # Enums and value objects
│   ├── services/          # Business logic layer
│   └── repositories/      # Spring Data JPA repositories
├── application/
│   └── controllers/       # REST API endpoints
└── configs/               # CORS, Security, Database config
```

**Core Design Patterns:**
- **Generic CRUD Service**: All entity services implement `EntityCrudService<T>` with standard operations
- **DTO Pattern**: Request DTOs (`*WithoutIdRequest`) and Response DTOs (`*TotalResponse`)
- **Auto-Timestamp Management**: `@PrePersist` and `@PreUpdate` callbacks handle `createdOn`/`updatedOn`
- **Entity Versioning**: `updateWithOldData()` preserves ID and creation timestamp during updates

#### Core Domain Entities

| Entity | Purpose |
|--------|---------|
| **CompanyEntity** | Client organizations (ESN or end clients) with SIRET, category, size |
| **ContactEntity** | Company employees with job title, email, phone, LinkedIn |
| **MessageTemplateEntity** | Reusable prospecting message formats |
| **AttachmentEntity** | Documents (CV, cover letters) for message attachments |
| **TaskEntity & TaskStackEntity** | Process and workflow management |
| **HistoryEntity** | Audit trail for message template changes |

#### Frontend Architecture

**Component Organization:**
```
app/
├── components/       # Reusable UI components
├── pages/           # Page-level components
├── services/        # API integration and business logic
└── models/          # TypeScript interfaces and enums
```

**Service Layer:**
- Generic `ApiService` wrapper with automatic error handling
- Entity-specific services: `CompanyService`, `ContactService`, etc.
- Configurable API base URL for dev/production environments

### 🚀 Quick Start

#### Prerequisites
- Docker & Docker Compose
- Java 17+ (for local backend development)
- Node.js 18+ (for frontend development)
- PostgreSQL (included in Docker setup)

#### Environment Setup
```bash
# Copy environment template
cp .env.example .env

# Configure PostgreSQL credentials in .env
POSTGRES_URL=jdbc:postgresql://postgres:5432/erp_freelance
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=your_password
```

#### Running with Docker
```bash
# Start all services
docker-compose up -d

# Access the application
# Frontend: http://localhost:8081
# Backend API: http://localhost:8080
```

#### Local Development

**Backend:**
```bash
cd backend
./mvnw clean package          # Build
./mvnw spring-boot:run        # Run locally
./mvnw test                   # Run tests
```

**Frontend:**
```bash
cd frontend
npm install                   # Install dependencies
npm start                     # Dev server (http://localhost:4200)
npm run build                 # Production build
npm test                      # Run tests
```

### 📊 Business Features

#### Companies Management
- Create, read, update, delete company profiles
- Track company category and size
- Access company statistics and contact history

#### Contacts Management
- Manage company employees and decision-makers
- Link contacts to companies
- Track contact details (email, phone, LinkedIn)
- Assign contacts to campaigns

#### Prospecting Campaigns
- Define and execute prospecting workflows
- Track campaign performance metrics
- Assign contacts to campaign tasks
- Monitor task progression

#### Message Management
- Create reusable message templates
- Attach documents (CVs, cover letters) to messages
- Manage message history and versions
- Track message status and delivery

#### Process & Workflow
- Define custom prospecting processes
- Create hierarchical task sequences
- Track process progression per contact
- Measure campaign effectiveness

### 🏢 Professional Implementation Details

**Database Design:**
- PostgreSQL with Hibernate DDL auto-update
- Foreign key relationships for data integrity
- Timestamp tracking for all entities
- Change audit trail via `HistoryEntity`

**API Compliance:**
- RESTful design principles
- Standard HTTP methods and status codes
- DTO-based request/response handling
- Error handling with meaningful messages

**CORS Configuration:**
- Configured for development (`localhost:4200`)
- Production-ready deployment support
- Docker-based isolation between frontend/backend

### 📈 Development Methodology

- **Agile Approach**: Iterative development with defined backlog
- **Data-Driven**: Metrics and analytics at the core of features
- **Scalable Design**: Monolithic but modular architecture
- **Container-Ready**: Full Docker support for development and deployment

### 📝 Project Statistics

- **Backend**: Spring Boot REST API with 8+ core entities
- **Frontend**: Angular SPA with component-based architecture
- **Database**: Normalized PostgreSQL schema with audit trail
- **Code Quality**: Lombok for reduced boilerplate, DTOs for API safety

---

<a id="français"></a>

## Version Française

### 📋 Présentation du Projet

**ERP Freelance** est une plateforme de gestion de la prospection spécialement conçue pour les développeurs freelances. L'application propose une approche systématique et basée sur les données pour aider les freelances à gérer la prospection client via des campagnes, des contacts, des entreprises et des workflows automatisés.

**Problématique:** Les développeurs freelances ont souvent du mal avec le marketing et le développement commercial, ce qui entraîne des périodes plus longues entre les contrats. Cette application rend la prospection systématique, mesurable et conviviale pour les développeurs.

### 🛠️ Stack Technologique

| Couche | Technologies |
|--------|--------------|
| **Backend** | Spring Boot 4.0.1, Java 17, PostgreSQL, JPA/Hibernate, Spring Data JPA |
| **Frontend** | Angular 21, TypeScript, RxJS |
| **Architecture** | API RESTful, Monolithique, Containerisée Docker |
| **Méthodologie** | Data-driven, Itérative, Agile |
| **DevOps** | Docker, Docker Compose |

### 🏗️ Architecture

#### Architecture Backend

**Structure des Packages:**
```
com.fredlecoat.erp_freelance
├── domain/
│   ├── entities/          # Entités JPA avec Lombok
│   ├── entities/dtos/     # DTOs de requête/réponse
│   ├── entities/mappers/  # Conversion entité vers DTO
│   ├── entities/values/   # Enums et objets de valeur
│   ├── services/          # Couche métier
│   └── repositories/      # Repositories Spring Data JPA
├── application/
│   └── controllers/       # Points de terminaison API REST
└── configs/               # Configuration CORS, Sécurité, Base de données
```

**Patterns de Conception:**
- **Service CRUD Générique**: Tous les services implémentent `EntityCrudService<T>`
- **Pattern DTO**: DTOs de requête (`*WithoutIdRequest`) et réponse (`*TotalResponse`)
- **Gestion Auto des Timestamps**: Callbacks `@PrePersist` et `@PreUpdate`
- **Versionnage d'Entités**: `updateWithOldData()` préserve l'ID et la date de création

#### Entités du Domaine Métier

| Entité | Objectif |
|--------|----------|
| **CompanyEntity** | Organisations clients (ESN ou clients finaux) avec SIRET, catégorie, taille |
| **ContactEntity** | Employés d'entreprises avec poste, email, téléphone, LinkedIn |
| **MessageTemplateEntity** | Formats de messages de prospection réutilisables |
| **AttachmentEntity** | Documents (CV, lettres de motivation) à joindre aux messages |
| **TaskEntity & TaskStackEntity** | Gestion des processus et workflows |
| **HistoryEntity** | Historique des modifications des modèles de messages |

#### Architecture Frontend

**Organisation des Composants:**
```
app/
├── components/       # Composants UI réutilisables
├── pages/           # Composants au niveau des pages
├── services/        # Intégration API et logique métier
└── models/          # Interfaces TypeScript et enums
```

**Couche Services:**
- Wrapper `ApiService` générique avec gestion d'erreurs automatique
- Services spécifiques par entité: `CompanyService`, `ContactService`, etc.
- URL de base d'API configurable dev/production

### 🚀 Démarrage Rapide

#### Prérequis
- Docker & Docker Compose
- Java 17+ (développement backend local)
- Node.js 18+ (développement frontend)
- PostgreSQL (inclus dans Docker)

#### Configuration de l'Environnement
```bash
# Copier le template d'environnement
cp .env.example .env

# Configurer les identifiants PostgreSQL dans .env
POSTGRES_URL=jdbc:postgresql://postgres:5432/erp_freelance
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=votre_mot_de_passe
```

#### Lancement avec Docker
```bash
# Démarrer tous les services
docker-compose up -d

# Accéder à l'application
# Frontend: http://localhost:8081
# API Backend: http://localhost:8080
```

#### Développement Local

**Backend:**
```bash
cd backend
./mvnw clean package          # Construction
./mvnw spring-boot:run        # Exécution locale
./mvnw test                   # Tests unitaires
```

**Frontend:**
```bash
cd frontend
npm install                   # Installation des dépendances
npm start                     # Serveur de développement (http://localhost:4200)
npm run build                 # Construction production
npm test                      # Tests
```

### 📊 Fonctionnalités Métier

#### Gestion des Entreprises
- Créer, consulter, modifier, supprimer les profils d'entreprises
- Suivi de la catégorie et taille des entreprises
- Statistiques et historique des contacts par entreprise

#### Gestion des Contacts
- Gérer les employés et décideurs des entreprises
- Lier les contacts aux entreprises
- Tracer les détails (email, téléphone, LinkedIn)
- Assigner des contacts aux campagnes

#### Campagnes de Prospection
- Définir et exécuter des workflows de prospection
- Suivre les métriques de performance
- Assigner des contacts à des tâches
- Monitorer la progression des tâches

#### Gestion des Messages
- Créer des modèles de messages réutilisables
- Joindre des documents (CV, lettres) aux messages
- Gérer l'historique et les versions
- Suivre le statut et la livraison

#### Processus et Workflows
- Définir des processus de prospection personnalisés
- Créer des séquences de tâches hiérarchiques
- Suivre la progression par contact
- Mesurer l'efficacité des campagnes

### 🏢 Détails de l'Implémentation Professionnelle

**Design de la Base de Données:**
- PostgreSQL avec auto-update Hibernate
- Relations par clés étrangères pour l'intégrité
- Suivi des timestamps pour toutes les entités
- Piste d'audit via `HistoryEntity`

**Conformité API:**
- Principes RESTful
- Méthodes HTTP et codes de statut standards
- Gestion basée sur DTOs
- Gestion des erreurs avec messages explicites

**Configuration CORS:**
- Configuré pour développement (`localhost:4200`)
- Support du déploiement en production
- Isolation Docker entre frontend/backend

### 📈 Méthodologie de Développement

- **Approche Agile**: Développement itératif avec backlog défini
- **Data-Driven**: Les métriques au cœur des fonctionnalités
- **Design Évolutif**: Architecture monolithique mais modulaire
- **Conteneurisé**: Support Docker complet dev/production

### 📝 Statistiques du Projet

- **Backend**: API REST Spring Boot avec 8+ entités principales
- **Frontend**: SPA Angular avec architecture basée sur composants
- **Base de Données**: Schéma PostgreSQL normalisé avec piste d'audit
- **Qualité du Code**: Lombok pour moins de boilerplate, DTOs pour sécurité API

---

## 👨‍💻 Author

**Frédéric Le Coat** - Full Stack Developer & Project Manager
- Expertise: Java/Spring Boot, Angular, PostgreSQL
- Focus: Data-driven architecture and scalable solutions

## 📄 License

This project is proprietary and confidential.

---

**Last Updated:** January 2026
