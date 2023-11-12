# Notes POO

**A utiliser :** l’héritage - les interfaces - la généricité - les exceptions - les local iner-class

**Important :** Architecture MVC et Rapport à réaliser !! (organisation et répartition)

**Description du jeu :** Une base à défendre, des vagues d’ennemis et des tours de défense à poser.

Chaque élément à un nombre de PV, de vitesse et de point d’attaque par attaque défini.

Modèle choisi : numéro 1

**Informations pratiques du sujet :**

Les ennemis avancent sur plusieurs lignes droites et les tours sont construites directement sur leur chemin. Les monstres attaquent les tours et doivent détruire celles-ci avant de pouvoir continuer à avancer.

**Ce dont on a besoin (jeu) :**

- Des points de départ et d’arrivée en fonction des lignes
- Plusieurs types d’ennemis
- Plusieurs types de tours
- Une monnaie
- Des ressources droppable externe à l’argent pour améliorer une tour
- Plus le temps passe, plus c’est difficile de jouer
- Système d’XP

**Ce dont on a besoin (autre) :**

- Environnement de jeu : accueil
- Paramétrage : Choisir difficulté (facile moyen difficile), le tableau (chemin et décors différents), mode de jeu (normal ou marathon)
- Un mode de jeu texte ou on peut jouer dans le terminal.
- Utilisation pour le graphique de awt et swing UNIQUEMENT

**Fonctionnalités avancées :**

- Système de sauvegarde (Interface Serialisable)
- Amélioration (améliorer ses tours)
- Déblocage de contenu (avec des tâches particulières)

**Conseils du Sujet :**

- Décomposition du code

Divisier le code, bien l’organiser dans les méthodes, les objets, etc

Distinguer les choses conceptuellement.

Écrire des petites méthodes

- Développement progressif

Réaliser une version minimale puis se lancer dans les fonctionnalités.

Séparer la Vue et le Modèle !!

**Aspects pratiques de l’évalutation :**

- Archive sous le nom Mehdi_Chater-Mathéo_Piget
- Ne pas avoir des fichiers inutiles
- Réaliser un README propre en MD
- Tout doit marcher sous Java 11

Le correcteur va :

- Décompresser dans une console unix
- Lire le README et trouver la ligne qui lance le jeu

**Rapport à rédiger :**

PDF de 5 pages rédigées !!

Expliquer les parties du cahier des charges qui ont été traitées, les problèmes connus et les pistes d’extensions pas implémentées.

Représentation graphique du modèle des classes

+ les choses utile pour la soutenance

**Soutenance :**

Sur une machine du 5ème étage

On va probablement nous demander de modifier notre code pour faire quelque chose de précis

Proposer des tutoriaux accessible dans le menu du jeu

Réaliser des présets de parties de situation particulière.