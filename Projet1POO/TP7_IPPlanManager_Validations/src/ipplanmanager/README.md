# TP7 - Validations avancées et détection des conflits

## Objectif
Ajouter des validations avancées pour détecter les incohérences dans un plan d'adressage.

## Notions étudiées
Exceptions personnalisées, try/catch, throw, validation réseau, détection de chevauchement, conflit VLAN, robustesse logicielle.

## Scénarios testés

### Scénario 1 : Plan VLSM normal
- TECHNIQUE : 120 hôtes → /25
- WIFI : 80 hôtes → /25
- ADMINISTRATION : 50 hôtes → /26
- SERVEURS : 20 hôtes → /27
- Résultat : Validation réussie, aucun conflit

### Scénario 2 : Conflit VLAN
- VLAN 10 (ADMINISTRATION) et VLAN 10 (WIFI) avec même ID
- Résultat : Conflit détecté

### Scénario 3 : Adresse IP invalide
- 192.168.300.0
- Résultat : AdresseIPInvalideException détectée

### Scénario 4 : Chevauchement manuel
- 192.168.1.0/25 et 192.168.1.64/26
- Résultat : ChevauchementReseauException détecté

## Résultats obtenus
- Validation du plan normal : réussie
- Conflit VLAN : correctement détecté
- Adresse IP invalide : correctement détectée
- Chevauchement réseau : correctement détecté

## Difficultés rencontrées
(A décrire par l'étudiant)

## Réponses aux questions

### 1. Pourquoi les validations avancées sont-elles indispensables dans un outil IPAM ?
Un outil IPAM doit garantir la cohérence du plan d'adressage. Sans validation, des erreurs comme des chevauchements ou des VLANs en doublon pourraient causer des pannes réseau.

### 2. Quelle est la différence entre une erreur simple et une exception en Java ?
Une erreur simple (if/else) arrête le traitement localement. Une exception permet de remonter l'erreur à un niveau supérieur et de la traiter avec try/catch, sans arrêter brutalement le programme.

### 3. Pourquoi crée-t-on des exceptions personnalisées ?
Pour donner des messages d'erreur clairs et spécifiques au domaine réseau (AdresseIPInvalideException, ConflitVLANException) plutôt que des erreurs génériques.

### 4. Quel est le rôle du bloc try/catch ?
try exécute du code pouvant générer une erreur, catch récupère cette erreur et permet de la traiter sans planter le programme.

### 5. Pourquoi deux VLANs ne doivent-ils pas avoir le même identifiant ?
L'identifiant VLAN est unique dans une infrastructure. Un doublon créerait des conflits de broadcast et de routage.

### 6. Pourquoi deux sous-réseaux ne doivent-ils pas se chevaucher ?
Un chevauchement signifie que deux sous-réseaux partagent des adresses IP, causant des conflits d'adressage et des erreurs de routage.

### 7. Pourquoi transforme-t-on les adresses IP en entiers pour comparer des plages réseau ?
La conversion en entier permet de faire des comparaisons numériques simples (début/fin) pour détecter les chevauchements.

### 8. Pourquoi la classe ValidateurPlanAdressage doit-elle être séparée du moteur VLSM ?
Séparation des responsabilités : le moteur génère le plan, le validateur vérifie sa cohérence. Chaque classe a un rôle unique.
