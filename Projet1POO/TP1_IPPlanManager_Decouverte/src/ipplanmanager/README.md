# TP1 - IPPlan-Manager

## Objectif du TP
Ce TP permet de découvrir les premières classes Java du projet IPPlan-Manager.

## Classes créées
- AdresseIP
- ReseauIP
- InterfaceReseau
- Equipement
- Main

## Travail réalisé
Dans ce TP, j'ai créé les classes de base pour représenter les objets réseau :
- 6 adresses IP (routeur, serveur, client, switch, point d'accès, client2)
- 2 réseaux IP (réseau principal 192.168.1.0/24 et réseau WiFi invités 192.168.2.0/24)
- 7 interfaces réseau (dont une inactive et une sans adresse IP)
- 6 équipements (routeur, serveur, poste client, switch, point d'accès WiFi, poste client supplémentaire)

## Réponses aux questions

### 1. Pourquoi une adresse IP a-t-elle été représentée par une classe au lieu d'être simplement stockée dans une variable String partout dans le programme ?
Une adresse IP a été représentée par une classe pour encapsuler les données et les comportements liés à une adresse IP. Cela permet de centraliser les validations, les méthodes d'affichage et les futures fonctionnalités (comme la validation du format, la conversion en binaire, etc.) dans une seule entité, plutôt que de les répéter partout dans le code.

### 2. Quelle est la différence entre une classe et un objet ?
Une classe est un modèle, un plan qui définit la structure et le comportement d'un type d'entité. Un objet est une instance concrète de cette classe, créée en mémoire avec des valeurs spécifiques. Par exemple, `AdresseIP` est la classe, tandis que `new AdresseIP("192.168.1.1")` crée un objet.

### 3. Quel est le rôle du constructeur dans une classe Java ?
Le constructeur est une méthode spéciale appelée automatiquement lors de la création d'un objet avec `new`. Il permet d'initialiser les attributs de l'objet avec des valeurs de départ.

### 4. Pourquoi la classe InterfaceReseau contient-elle un objet de type AdresseIP ?
La classe `InterfaceReseau` contient un objet de type `AdresseIP` car une interface réseau possède une adresse IP qui est une entité à part entière. Cette composition permet de réutiliser toutes les fonctionnalités de la classe `AdresseIP` et de maintenir une relation logique entre l'interface et son adresse.

### 5. Pourquoi la classe Equipement contient-elle un objet de type InterfaceReseau ?
La classe `Equipement` contient un objet de type `InterfaceReseau` car un équipement réseau possède au moins une interface réseau pour communiquer. Cette relation de composition permet de lier l'équipement à son interface physique ou logique.

### 6. Quelle est la limite actuelle de la classe Equipement dans ce TP ?
La limite actuelle est qu'un équipement ne peut avoir qu'une seule interface réseau (l'interface principale), alors qu'en réalité un routeur ou un switch possède généralement plusieurs interfaces.

### 7. Pourquoi cette première version n'est-elle pas encore suffisante pour produire automatiquement un plan d'adressage IP ?
Cette première version n'est pas suffisante car elle ne contient pas encore de logique de calcul de sous-réseaux, de validation avancée des adresses IP, de gestion des masques, ni d'algorithme de découpage de plages d'adresses. Elle pose uniquement les bases de la représentation objet.
