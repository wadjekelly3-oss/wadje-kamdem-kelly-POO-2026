# TP3 - Collections et composition

## Objectif
Introduction des collections et des relations entre objets.

## Notions étudiées
Composition, ArrayList, collections, parcours de listes, relations entre objets.

## Classes créées
- AdresseIP
- ReseauIP
- InterfaceReseau
- Equipement (modifié avec ArrayList)
- SousReseau (nouveau)
- InfrastructureReseau (nouveau)
- Main

## Tests réalisés
- Création d'une infrastructure avec 3 sous-réseaux (ADMIN, TECH, WIFI)
- Création de 6 équipements (Routeur, Switch, Serveur, Point WiFi, 2 Postes)
- Chaque équipement possède plusieurs interfaces
- Test de la méthode rechercherEquipement() (trouvé et introuvable)
- Vérification de l'affichage complet de l'infrastructure

## Difficultés rencontrées
(A décrire par l'étudiant)

## Réponses aux questions

### 1. Qu'est-ce qu'une composition en Programmation Orientée Objet ?
La composition est une relation entre objets où un objet contient d'autres objets. Par exemple, une InfrastructureReseau contient plusieurs Equipement et plusieurs SousReseau. Un Equipement contient plusieurs InterfaceReseau. La composition permet de représenter des relations réelles du type "possède" ou "est composé de".

### 2. Pourquoi utilise-t-on ArrayList dans ce TP ?
On utilise ArrayList pour stocker dynamiquement plusieurs objets sans avoir à déclarer une variable par objet. On peut facilement ajouter, supprimer et parcourir les éléments.

### 3. Quelle différence existe entre une variable simple et une collection ?
Une variable simple ne peut contenir qu'un seul objet, tandis qu'une collection peut contenir plusieurs objets du même type.

### 4. Pourquoi un équipement possède-t-il plusieurs interfaces ?
Dans la réalité, un équipement réseau possède généralement plusieurs interfaces physiques ou logiques pour se connecter à différents réseaux.

### 5. Pourquoi une infrastructure réseau contient-elle plusieurs sous-réseaux ?
Une infrastructure réseau d'entreprise est découpée en plusieurs sous-réseaux pour des raisons de sécurité, d'organisation et de performance.

### 6. Quel est le rôle de la boucle for-each ?
La boucle for-each permet de parcourir tous les éléments d'une collection un par un, sans avoir besoin de gérer un index.

### 7. Pourquoi la classe InfrastructureReseau devient-elle importante dans le projet ?
Elle devient le cœur du projet car elle centralise tous les objets (équipements et sous-réseaux) en un seul endroit.

### 8. Pourquoi les collections sont-elles indispensables dans les applications professionnelles ?
Les applications professionnelles gèrent souvent des milliers d'objets. Sans collections, il serait impossible de les stocker et de les manipuler efficacement.
