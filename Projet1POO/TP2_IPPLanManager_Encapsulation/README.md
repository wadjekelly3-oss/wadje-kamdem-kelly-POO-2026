# TP2 - Encapsulation

## Objectif
Introduction de l'encapsulation et des validations.

## Notions étudiées
- private
- getters
- setters
- validation
- this

## Tests réalisés
- Création d'adresses IP valides (192.168.1.1, 10.0.0.1, 172.16.0.254)
- Création d'adresses IP invalides (chaîne vide, null)
- Création d'interfaces avec noms valides et invalides
- Activation/désactivation d'interfaces
- Création de plusieurs équipements (routeur, switch, firewall, serveur)
- Création d'équipements avec noms et types invalides
- Modification des attributs avec les setters
- Test de la méthode estAdresseLocale()
- Validation du masque CIDR (0-32)

## Difficultés rencontrées
- Comprendre la différence avec le TP1 où tout était public
- Bien utiliser le mot-clé this pour distinguer attributs et paramètres
- Gérer les cas où les objets sont null

## Réponses aux questions

### 1. Pourquoi utilise-t-on private dans les classes ?
On utilise private pour protéger les attributs d'une classe et empêcher 
leur modification directe depuis l'extérieur. Cela garantit l'intégrité 
des données et applique le principe d'encapsulation.

### 2. Quelle différence existe entre un attribut public et un attribut privé ?
- **public** : l'attribut est accessible et modifiable directement depuis 
  n'importe quelle autre classe, sans aucun contrôle.
- **private** : l'attribut n'est accessible que depuis l'intérieur de la 
  classe elle-même. Toute modification doit passer par des setters avec validation.

### 3. Pourquoi utilise-t-on des getters et setters ?
- **getters** (get) : permettent de lire un attribut privé de façon contrôlée.
- **setters** (set) : permettent de modifier un attribut privé en appliquant 
  des validations avant la modification. Ils assurent que l'objet reste 
  toujours dans un état cohérent.

### 4. Pourquoi les validations sont-elles importantes dans un logiciel réseau ?
Les validations sont cruciales car une configuration réseau incorrecte 
(adresse IP vide, masque CIDR invalide) peut entraîner :
- des pannes réseau
- des conflits d'adresses
- des failles de sécurité
- des comportements imprévisibles

### 5. Quel est le rôle du mot-clé this ?
this fait référence à l'instance courante de l'objet. Il permet de :
- distinguer l'attribut de l'objet du paramètre de la méthode 
  quand ils portent le même nom (ex : this.nom = nom)
- appeler un autre constructeur de la même classe

### 6. Pourquoi le constructeur appelle-t-il les setters ?
Le constructeur appelle les setters pour réutiliser le code de validation 
déjà écrit dans les setters. Cela évite de dupliquer le code et garantit 
que la création d'un objet passe par les mêmes contrôles que les modifications.

### 7. Pourquoi la validation du masque CIDR est-elle importante ?
Le masque CIDR doit être entre 0 et 32 en IPv4. Une valeur invalide 
(ex: 45) rendrait impossible le calcul des adresses réseau, du broadcast 
et de la plage d'adresses disponibles.

### 8. Pourquoi l'encapsulation améliore-t-elle la sécurité logicielle ?
L'encapsulation améliore la sécurité en :
- empêchant les modifications non contrôlées des données
- garantissant que les objets restent dans un état cohérent
- réduisant les erreurs humaines
- facilitant la maintenance du code
