# TP9 - Persistance et organisation professionnelle

## Objectif
Ajouter la lecture et l'écriture de fichiers afin de sauvegarder les besoins, les plans, les VLANs, les recommandations et les rapports.

## Notions étudiées
Persistance, fichiers CSV, lecture de fichier, écriture de fichier, repository, service, packages, architecture professionnelle.

## Organisation en packages
- **ipplanmanager.model** : classes métier (BesoinReseau, ResultatVLSM, VLAN, Recommandation)
- **ipplanmanager.service** : classes de traitement (CalculateurReseau, MoteurVLSM, GestionnaireVLAN, règles, RapportService)
- **ipplanmanager.repository** : classes d'accès aux fichiers (BesoinRepository, FichierPlanRepository)
- **ipplanmanager.exception** : exceptions personnalisées
- **ipplanmanager.main** : classe de démarrage

## Fichiers utilisés

### Entrée
- `exports/besoins.csv` : besoins utilisateur

### Sortie
- `exports/plan_adressage.csv` : plan VLSM généré
- `exports/vlans.csv` : VLANs créés
- `exports/recommandations.txt` : recommandations techniques
- `exports/rapport_complet.txt` : rapport technique complet

## Scénarios testés
- Lecture des besoins depuis `besoins.csv`
- Génération du plan VLSM (10.10.0.0)
- Création des VLANs (ID 10, 20, 30, 40, 50)
- Application des règles de recommandation
- Sauvegarde des résultats dans le dossier exports
- Génération du rapport complet

## Résultats obtenus
- 5 besoins lus depuis le fichier CSV
- 5 sous-réseaux générés
- 5 VLANs créés
- 4 fichiers générés avec succès dans exports

## Difficultés rencontrées
(A décrire par l'étudiant)

## Réponses aux questions

### 1. Qu'est-ce que la persistance des données ?
La persistance permet de conserver les données après l'arrêt du programme, en les sauvegardant dans des fichiers ou une base de données.

### 2. Pourquoi une application professionnelle doit-elle sauvegarder ses résultats ?
Pour pouvoir consulter, partager et réutiliser les résultats plus tard. Un administrateur doit pouvoir relire un plan d'adressage sans relancer les calculs.

### 3. Quelle est la différence entre un fichier CSV et un rapport texte ?
Un fichier CSV est structuré pour être lu par des machines (tableurs), tandis qu'un rapport texte est formaté pour être lu directement par un humain.

### 4. Pourquoi a-t-on créé un package repository ?
Pour isoler les classes qui gèrent l'accès aux données (fichiers). Cela respecte le principe de séparation des responsabilités.

### 5. Pourquoi a-t-on créé un package service ?
Pour regrouper les classes qui contiennent la logique métier (calculs, moteurs, gestionnaires) séparément des données.

### 6. Pourquoi ne faut-il pas écrire tout le code dans la classe Main ?
Pour éviter un code illisible et impossible à maintenir. Chaque classe doit avoir une responsabilité unique.

### 7. Pourquoi le fichier besoins.csv rend-il l'application plus flexible ?
On peut changer les besoins sans modifier le code Java. L'utilisateur modifie juste le fichier CSV.

### 8. Pourquoi la séparation en packages améliore-t-elle la maintenabilité ?
Les packages organisent le code par rôle. Pour modifier une partie, on sait exactement où aller, sans risquer de casser le reste.
