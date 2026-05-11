# TP8 - Moteur de recommandations

## Objectif
Ajouter un moteur de recommandations capable d'analyser un plan VLAN et de proposer des conseils techniques.

## Notions étudiées
Interfaces Java, polymorphisme, règles métier, moteur de recommandations, séparation des responsabilités, extensibilité logicielle.

## Classes créées

### Classes reprises du TP7 (16 classes)
AdresseIP, ReseauIP, InterfaceReseau, Equipement, SousReseau, InfrastructureReseau,
BesoinReseau, AdresseIPInvalideException, ConflitVLANException, ChevauchementReseauException,
CalculateurReseau, ResultatVLSM, MoteurVLSM, VLAN, GestionnaireVLAN, ValidateurPlanAdressage

### Nouvelles classes (8 classes)
- **Recommandation** : Objet représentant un conseil avec titre, priorité et message
- **RegleRecommandation** (Interface) : Contrat commun pour toutes les règles
- **RecommandationWifiInvite** : Détecte les VLANs WiFi
- **RecommandationServeurs** : Détecte les VLANs Serveurs
- **RecommandationGrandVLAN** : Détecte les VLANs > 200 hôtes
- **RecommandationAdministration** : Détecte les VLANs Administration
- **MoteurRecommandation** : Applique toutes les règles sur tous les VLANs
- **Main** : Scénario de test

## Scénario testé

### Université (10.10.0.0)
| Besoin | Hôtes | VLAN | Sous-réseau | Capacité |
|--------|-------|------|-------------|----------|
| ETUDIANTS | 500 | 10 | 10.10.0.0/23 | 510 |
| WIFI_INVITES | 200 | 20 | 10.10.2.0/24 | 254 |
| ENSEIGNANTS | 120 | 30 | 10.10.3.0/25 | 126 |
| LABORATOIRES | 60 | 40 | 10.10.3.128/26 | 62 |
| SERVEURS | 30 | 50 | 10.10.3.192/27 | 30 |

## Recommandations obtenues

1. [MOYENNE] VLAN de grande taille : ETUDIANTS (510 hôtes)
2. [ÉLEVÉE] Isolation du WiFi : WIFI_INVITES
3. [MOYENNE] VLAN de grande taille : WIFI_INVITES (254 hôtes)
4. [ÉLEVÉE] Protection du VLAN Serveurs : SERVEURS

## Difficultés rencontrées
(A décrire par l'étudiant)

## Réponses aux questions

### 1. Quel est le rôle d'un moteur de recommandations dans un outil IPAM ?
Un moteur de recommandations analyse le plan généré et propose des conseils techniques pour améliorer la sécurité, la performance et l'organisation du réseau.

### 2. Pourquoi utilise-t-on une interface pour les règles de recommandation ?
L'interface garantit que toutes les règles possèdent la même méthode analyser(), ce qui permet au moteur de les traiter uniformément sans connaître leur logique interne.

### 3. Quelle est la différence entre une classe concrète et une interface ?
Une classe concrète contient du code et peut être instanciée. Une interface définit seulement des signatures de méthodes sans les implémenter.

### 4. Pourquoi la méthode analyser() peut-elle retourner null ?
Si aucune recommandation n'est nécessaire pour un VLAN, retourner null évite de créer un objet inutile. Le moteur filtre ensuite les null.

### 5. Pourquoi le moteur de recommandations illustre-t-il le polymorphisme ?
Le moteur appelle analyser() sur des objets de type RegleRecommandation, mais chaque objet exécute sa propre version. Même appel, comportement différent.

### 6. Pourquoi est-il préférable de créer une classe par règle ?
Chaque règle est isolée, testable et réutilisable. Ajouter une règle ne modifie pas le code existant (principe Open/Closed).

### 7. Pourquoi un VLAN WiFi invité doit-il être isolé des réseaux internes ?
Le WiFi invité est accessible à des personnes extérieures. Sans isolation, elles pourraient accéder aux ressources internes sensibles.

### 8. Pourquoi les VLANs de grande taille doivent-ils être surveillés ?
Un grand VLAN génère beaucoup de broadcasts, peut dégrader les performances et une infection peut s'y propager rapidement.
