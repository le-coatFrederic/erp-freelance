# ERP FREELANCE

## Résumé 

En tant que Freelance, la principale difficulté que l'on rencontre est la **prospection***. En effet, très peu de développeurs sont formés au Marketing et cela se voit sur la **durée de leurs inter-contrats**.

Grâce à cette application **Developper-friendly**, nous suivons une méthode **itérative**, simple et bien connue des développeurs pour leur permettre de faire une **prospection efficace et personnalisée**.

Je suis **Développeur Fullstack** et **Chef de projet** avec une expertise en Ingénierie de la donnée. J'ai construit l'application de A à Z en utilisant mes compétences en gestion de projet pour aboutir à une solution optimale.

Ainsi, les **technologies** et **frameworks** utilisés dans ce projet sont les suivante :

- Backend : Spring boot, Java, PostgreSQL
- Frontend : Angular, Typescript, Javascript
- Architecture Logicielle : Monolithique, Data Driven, RestAPI
- Gestion de projet : Méthode Agile, Backlog

## Contexte et problématique métier

Les **développeurs** ne sont pas très efficaces avec le **marketing**. Quand on est **Freelance**, c'est une **compétence essentielle**. Pourtant, d'après "Freelancer Study", l'**acquisition de projet** est une des plus grosses difficultés du métier. Aussi, selon le site Malt, 41% des développeurs pensent que le marketing est inutile et **78% pensent qu'il est contraignant**.

Cette application a pour vocation de **rendre facile le marketing** pour les **développeurs freelance** qui n'ont pas de compétences dans ce domaine.

La principale **contrainte** de cette application est de **rendre attractif le marketing** pour les développeurs.

## Architecture globale

Cette section est séparée en plusieurs parties : 

- [Backend](###backend)
  - [Domaine](####domaine) : explication des entités et des fonctionnalités à intégrer dans l'application
  - [Application](####application) : comment le domaine va interagir avec les contrôleurs.
- [Frontend](###frontend)
  - [Organisation des pages](####organisation-des-pages)

### Backend

#### Domaine

Au niveau des entités, nous avons :

- Entreprise : qui représente un client final ou une ESN.
- Contact : qui est un employé d'une entreprise.
- Document : permet de représenter un CV ou une lettre de motivation.
- Processus : est un enchaînement d'action et de condition qui permet de suivre l'avancée de la prospection.
- Etape : action à réaliser dans un processus.
- ModeleMessage : est le format du message.
- Message : est créé grâce au ModeleMessage, il doit être envoyé au contact, il peut contenir un document.
- Campagne : essentielle pour mesurer la performance de la prospection, elle permet de définir pour une liste de contact, le processus que l'on va utiliser.

Et pour les fonctionnalités :

- Entreprises :
  - Créer, modifier, supprimer et afficher les données d'une entreprise.
  - Afficher les statistiques d'une entreprise.
- Contacts :
  - Créer, modifier, supprimer et afficher les données d'un contact.
  - Ajouter le contact à une entreprise.
  - Ajouter le contact à une campagne.
- Documents : 
  - Créer, modifier, supprimer et afficher un document.
  - Ajouter le document à un message
- Processus :
  - Créer, modifier, supprimer et afficher les données d'un processus.
  - Ajouter, supprimer ou modifier une étape au processus.
  - Suivre l'évolution du processus.
- ContactProcessus :
  - Créer, modifier, supprimer et afficher les données d'un contactProcessus.
  - Définir le contact.
  - Définir le processus.
- Etapes :
  - Créer, modifier, supprimer et afficher les données d'une étape.
  - Ajouter un message à l'étape.
  - Ajouter une étape fille (suivante) à l'étape.
- ModeleMessages :
  - Créer, modifier, supprimer et afficher les données d'un message.
- Messages :
  - Créer, modifier, supprimer et afficher les données d'un message.
  - Faire évoluer le statut du message.
- Campagnes :
  - Créer, modifier, supprimer et afficher les données d'une campagne.
  - Définir la liste des contactProcessus.

Nous avons donc pour le **Dictionnaire de données** (en français, dans le code tout est traduit en anglais) : 

- Entreprise(**<u>id</u>**, nom, siret, categorie, taille)
- Contact(**<u>id</u>**, **#id_entreprise**, nom, prénom, téléphone, email, linkedin)
- Document(**<u>id</u>**, nom, lien_document, date_depot, category)
- Processus(**<u>id</u>**, description)
- ContactCampagne(**<u>id</u>**, **#id_contact**, **#id_campagne**, **#id_etape_courante**, date_entree, date_sortie)
- Etape(**<u>id</u>**, **#id_etape_mere**, nom, description, objectifs)
- ModeleMessage(**<u>id</u>**, sujet, contenu, type, flux)
- Message(**<u>id</u>**, **#id_modele_message**, **#id_etape**, statut)
- MessageDocument(**<u>#id_message</u>**, **<u>#id_document</u>**)
- Campagne(**<u>id</u>**, début_campagne, fin_campagne)

#### Application

### Frontend

#### Organisation des pages