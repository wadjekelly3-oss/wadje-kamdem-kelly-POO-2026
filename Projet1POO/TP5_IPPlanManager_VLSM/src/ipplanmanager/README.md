# TP5 - Moteur VLSM

## Objectif
Développer un moteur VLSM capable de proposer automatiquement un plan d'adressage à partir des besoins exprimés.

## Notions étudiées
VLSM, tri de collections, classe de service métier, calcul CIDR, conversion IP-entier, génération automatique de sous-réseaux.

## Classes créées
- AdresseIP
- CalculateurReseau (ENRICHI : conversion IP-entier, calcul CIDR automatique, plages utilisables)
- ReseauIP
- InterfaceReseau
- Equipement
- SousReseau
- InfrastructureReseau
- BesoinReseau (NOUVEAU : représente un besoin utilisateur)
- ResultatVLSM (NOUVEAU : résultat produit par le moteur VLSM)
- MoteurVLSM (NOUVEAU : service métier de découpage VLSM)
- Main (3 scénarios de test)

## Scénarios testés

### Scénario 1 : Besoins standards (192.168.1.0)
- TECHNIQUE : 120 hôtes → /24 (254 hôtes)
- WIFI : 80 hôtes → /25 (126 hôtes)
- ADMINISTRATION : 50 hôtes → /26 (62 hôtes)
- SERVEURS : 20 hôtes → /27 (30 hôtes)
- DIRECTION : 10 hôtes → /28 (14 hôtes)

### Scénario 2 : Petits besoins (10.0.0.0)
- COM : 15 hôtes → /27 (30 hôtes)
- ADMIN : 10 hôtes → /28 (14 hôtes)
- LABO : 5 hôtes → /29 (6 hôtes)

### Scénario 3 : Gros besoins (172.16.0.0)
- SIEGE : 500 hôtes → /23 (510 hôtes)
- DATACENTER : 200 hôtes → /24 (254 hôtes)
- R&D : 100 hôtes → /25 (126 hôtes)
- SUPPORT : 60 hôtes → /26 (62 hôtes)
- STAGIAIRES : 25 hôtes → /27 (30 hôtes)

## Résultats obtenus
Tous les plans d'adressage ont été générés avec succès. Les sous-réseaux sont correctement dimensionnés (capacité ≥ besoin) et ne se chevauchent pas. Les plages utilisables (première et dernière adresse) sont correctement calculées.

## Difficultés rencontrées
(A décrire par l'étudiant)

## Réponses aux questions

### 1. Pourquoi le VLSM permet-il d'économiser les adresses IP ?
Le VLSM permet d'attribuer à chaque besoin exactement le sous-réseau adapté à sa taille, sans gaspillage. Au lieu de donner un /24 (254 hôtes) à un service qui n'a besoin que de 20 hôtes, on lui attribue un /27 (30 hôtes).

### 2. Pourquoi faut-il traiter les plus grands besoins en premier ?
Il faut commencer par les plus grands besoins pour éviter de fragmenter l'espace d'adressage. Si on place d'abord de petits sous-réseaux, on risque de ne plus avoir de bloc assez grand pour accueillir les besoins importants.

### 3. Quelle est la différence entre un besoin réseau et un résultat VLSM ?
Un BesoinReseau représente une demande utilisateur, tandis qu'un ResultatVLSM est la solution calculée par le moteur. L'un exprime un besoin, l'autre une proposition concrète.

### 4. Pourquoi la classe MoteurVLSM est-elle une classe de service métier ?
La classe MoteurVLSM applique un algorithme métier pour transformer des besoins en résultats. Elle ne stocke pas de données mais exécute la logique de découpage VLSM.

### 5. Pourquoi transforme-t-on une adresse IP en entier pour certains calculs ?
Une adresse IP en entier permet de faire des opérations arithmétiques simples (addition, soustraction) pour calculer des plages, générer des adresses successives et éviter les manipulations complexes de texte.

### 6. Quel est le rôle de la méthode calculerCidrPourHotes() ?
Cette méthode détermine le CIDR le plus adapté à un nombre d'hôtes donné en testant les CIDR jusqu'à trouver une capacité suffisante, minimisant ainsi le gaspillage.

### 7. Pourquoi une adresse de réseau et une adresse de broadcast ne sont-elles pas attribuées aux machines ?
L'adresse réseau identifie le sous-réseau et l'adresse de broadcast permet la diffusion. Ces deux adresses sont réservées par le protocole IP.

### 8. Pourquoi le moteur VLSM représente-t-il une étape importante dans le projet IPPlan-Manager ?
Le moteur VLSM est le cœur technique du projet. Il automatise la création d'un plan d'adressage optimisé, fonction principale d'un outil de planification réseau.
