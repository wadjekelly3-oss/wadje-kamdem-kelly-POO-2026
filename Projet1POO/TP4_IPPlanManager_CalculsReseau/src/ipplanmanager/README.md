# TP4 - Calculs réseau

## Objectif
Introduction des calculs automatiques réseau.

## Notions étudiées
Méthodes statiques, calculs réseau, CIDR, logique algorithmique, classes utilitaires.

## Classes créées
- AdresseIP
- CalculateurReseau (NOUVEAU - classe utilitaire)
- ReseauIP (MODIFIÉE - affichage des calculs)
- InterfaceReseau
- Equipement
- SousReseau
- InfrastructureReseau
- Main

## Tests réalisés
- Test de 6 réseaux avec différents CIDR (/8, /16, /24, /27, /28)
- Vérification du calcul du nombre d'hôtes (2^(32-CIDR)-2)
- Vérification de la classe réseau (A, B, C)
- Vérification du masque décimal
- Test de la méthode estReseauPrive() (plages privées RFC 1918)
- Test avec une adresse publique (8.8.8.0)

## Résultats des tests
- /8  → 16 777 214 hôtes, Classe A, masque 255.0.0.0
- /16 → 65 534 hôtes, Classe B, masque 255.255.0.0
- /24 → 254 hôtes, Classe C, masque 255.255.255.0
- /27 → 30 hôtes, masque 255.255.255.224
- /28 → 14 hôtes, masque 255.255.255.240
- Réseaux privés correctement détectés (10.x, 172.16-31.x, 192.168.x)
- Réseau public correctement détecté (8.8.8.0)

## Difficultés rencontrées
(A décrire par l'étudiant)

## Réponses aux questions

### 1. Pourquoi a-t-on créé une classe utilitaire ?
On a créé une classe utilitaire pour centraliser tous les calculs réseau dans un seul endroit. Cela évite de répéter le code dans chaque classe, facilite la maintenance et sépare la logique de calcul des objets métier.

### 2. Quel est le rôle du mot-clé static ?
Le mot-clé `static` permet d'appeler une méthode sans avoir à créer un objet de la classe. On peut appeler directement `CalculateurReseau.calculerNombreHotes(24)` sans faire `new CalculateurReseau()`.

### 3. Pourquoi les calculs réseau sont-ils importants dans un outil IPAM ?
Les calculs réseau automatisent la détermination du nombre d'hôtes, du masque et de la classe réseau, ce qui évite les erreurs humaines et accélère le déploiement des plans d'adressage.

### 4. Quelle est l'utilité du CIDR ?
Le CIDR (Classless Inter-Domain Routing) indique le nombre de bits réservés au réseau dans une adresse IP. Il permet de déterminer la taille du réseau et le nombre d'hôtes disponibles.

### 5. Pourquoi le nombre d'hôtes dépend-il du masque réseau ?
Plus le masque est grand (CIDR élevé), moins il reste de bits pour les hôtes. La formule 2^(32-CIDR)-2 montre que le nombre d'hôtes diminue quand le CIDR augmente.

### 6. Pourquoi certaines adresses IP sont-elles privées ?
Les adresses privées (RFC 1918) sont réservées pour les réseaux internes et ne sont pas routables sur Internet. Elles permettent d'économiser les adresses IP publiques.

### 7. Pourquoi la séparation entre logique métier et logique de calcul améliore-t-elle le projet ?
Cette séparation rend le code plus organisé, plus facile à maintenir et à tester. Si un calcul change, on modifie uniquement la classe utilitaire sans toucher aux autres classes.

### 8. Pourquoi les outils de planification réseau doivent-ils automatiser les calculs ?
L'automatisation évite les erreurs de calcul manuel, accélère le travail des administrateurs et garantit des résultats cohérents et fiables pour le plan d'adressage.
