# TP6 - VLAN et segmentation logique

## Objectif
Mettre en place la gestion des VLANs et associer automatiquement les sous-réseaux générés aux VLANs.

## Notions étudiées
Segmentation logique, VLAN, gestionnaires métier, collections, associations entre objets, architecture métier.

## Classes créées
- AdresseIP
- CalculateurReseau
- ReseauIP
- InterfaceReseau
- Equipement
- SousReseau
- InfrastructureReseau
- BesoinReseau
- ResultatVLSM
- MoteurVLSM
- VLAN (NOUVEAU : segmentation logique)
- GestionnaireVLAN (NOUVEAU : gestion centralisée des VLANs)
- Main (2 scénarios)

## Scénarios testés

### Scénario A : Besoins standards (192.168.1.0)
- TECHNIQUE : 120 hôtes → VLAN 10, /25 (126 hôtes)
- WIFI : 80 hôtes → VLAN 20, /25 (126 hôtes)
- ADMINISTRATION : 50 hôtes → VLAN 30, /26 (62 hôtes)
- SERVEURS : 20 hôtes → VLAN 40, /27 (30 hôtes)

### Scénario B : Université (10.0.0.0)
- ETUDIANTS : 500 hôtes → VLAN 100, /23 (510 hôtes)
- WIFI_PUBLIC : 200 hôtes → VLAN 110, /24 (254 hôtes)
- ENSEIGNANTS : 120 hôtes → VLAN 120, /25 (126 hôtes)
- LABORATOIRES : 60 hôtes → VLAN 130, /26 (62 hôtes)
- SERVEURS : 30 hôtes → VLAN 140, /27 (30 hôtes)

## Résultats obtenus
- Tous les VLANs sont générés automatiquement à partir des résultats VLSM
- La recherche de VLAN par ID fonctionne correctement
- La détection des VLANs critiques (>100 hôtes) est opérationnelle
- L'identification du plus grand VLAN fonctionne
- Les IDs VLAN sont attribués par incréments de 10

## Difficultés rencontrées
(A décrire par l'étudiant)

## Réponses aux questions

### 1. Pourquoi les VLANs sont-ils importants dans les réseaux modernes ?
Les VLANs permettent de segmenter logiquement un réseau physique en plusieurs réseaux indépendants, améliorant la sécurité, réduisant les domaines de broadcast et facilitant la gestion des politiques réseau.

### 2. Pourquoi un VLAN est-il souvent associé à un sous-réseau spécifique ?
Chaque VLAN correspond à un sous-réseau IP différent car le routage entre VLANs nécessite des adresses réseau distinctes pour contrôler les communications.

### 3. Pourquoi la séparation logique améliore-t-elle la sécurité ?
La séparation logique isole les différents services. Même sur une même infrastructure physique, les flux restent séparés, limitant les risques de propagation d'attaques.

### 4. Quel est le rôle de la classe GestionnaireVLAN ?
Elle centralise toutes les opérations liées aux VLANs : ajout, recherche, affichage, détection des VLANs critiques, via une ArrayList<VLAN>.

### 5. Pourquoi la classe VLAN contient-elle un objet ResultatVLSM ?
Pour lier l'identifiant VLAN (logique) au sous-réseau IP (adressage) calculé par le moteur VLSM. C'est une relation de composition.

### 6. Pourquoi utilise-t-on encore ArrayList dans ce TP ?
Pour gérer dynamiquement la collection de VLANs, comme pour les équipements et sous-réseaux dans les TPs précédents.

### 7. Pourquoi les responsabilités des classes doivent-elles être séparées ?
La séparation des responsabilités rend le code plus maintenable, lisible et évolutif. Chaque classe a un rôle unique et précis.

### 8. Pourquoi le projet commence-t-il maintenant à ressembler à une véritable application professionnelle ?
Le projet possède une architecture en couches avec des rôles bien définis : besoins, calculs, résultats, segmentation logique, gestion centralisée, comme une vraie application métier.
