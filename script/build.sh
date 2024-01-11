#!/bin/bash

# Fonction build qui réalise tout le travail nécessaire à la construction
build(){

# Construction des fichiers .class
echo "Construction des classes en cours..."

javac src/main/java/configMap/Cellule.java
javac src/main/java/configMap/GameMap.java

javac src/main/java/launchsounds/ActionSound.java
javac src/main/java/launchsounds/MusicAndSound.java

javac src/main/java/model/enemies/Dreth.java
javac src/main/java/model/enemies/Fyron.java
javac src/main/java/model/enemies/Gazer.java
javac src/main/java/model/enemies/Kryon.java
javac src/main/java/model/enemies/Liche.java
javac src/main/java/model/enemies/Zorch.java

javac src/main/java/model/towers/BulletTower.java
javac src/main/java/model/towers/FightTower.java
javac src/main/java/model/towers/NukeTower.java
javac src/main/java/model/towers/SniperTower.java
javac src/main/java/model/towers/SpeedTower.java
javac src/main/java/model/towers/TntTower.java

javac src/main/java/model/Element.java
javac src/main/java/model/Enemy.java
javac src/main/java/model/Tower.java

javac src/main/java/start/Player.java
javac src/main/java/start/Start.java

javac src/main/java/UI/TerminalUI.java

javac src/main/java/UI/GUI/GUI.java
javac src/main/java/UI/GUI/GameMapPanel.java
javac src/main/java/UI/GUI/GameState.java


echo "Compilation des classes terminé"

# On créé un répertoire build et on met toute l'arborescence sur projet dedans
echo "Création du répertoire build et déplacement dans celui-ci..."

mkdir build
cd build && mkdir src && cd src && mkdir main && cd main && mkdir java && cd java
mkdir configMap
mkdir launchsounds
mkdir model
mkdir start
mkdir UI
cd UI && mkdir GUI
cd ../../../../..

# On déplace les fichiers .class au bon endroit dans le fichier build
mv "src/main/java/configMap/Cellule.class" "build/src/main/java/configMap/"
mv "src/main/java/configMap/GameMap.class" "build/src/main/java/configMap/"

mv "src/main/java/launchsounds/ActionSound.class" "build/src/main/java/launchsounds/"
mv "src/main/java/launchsounds/MusicAndSound.class" "build/src/main/java/launchsounds/"

mv "src/main/java/model/Element.class" "build/src/main/java/model/"
mv "src/main/java/model/Enemy.class" "build/src/main/java/model/"
mv "src/main/java/model/Tower.class" "build/src/main/java/model/"

mv "src/main/java/model/enemies/Dreth.class" "build/src/main/java/model/enemies/"
mv "src/main/java/model/enemies/Fyron.class" "build/src/main/java/model/enemies/"
mv "src/main/java/model/enemies/Gazer.class" "build/src/main/java/model/enemies/"
mv "src/main/java/model/enemies/Kryon.class" "build/src/main/java/model/enemies/"
mv "src/main/java/model/enemies/Liche.class" "build/src/main/java/model/enemies/"
mv "src/main/java/model/enemies/Zorch.class" "build/src/main/java/model/enemies/"

mv "src/main/java/model/towers/BulletTower.class" "build/src/main/java/model/towers/"
mv "src/main/java/model/towers/FightTower.class" "build/src/main/java/model/towers/"
mv "src/main/java/model/towers/NukeTower.class" "build/src/main/java/model/towers/"
mv "src/main/java/model/towers/SniperTower.class" "build/src/main/java/model/towers/"
mv "src/main/java/model/towers/TntTower.class" "build/src/main/java/model/towers/"

mv "src/main/java/start/Player.class" "build/src/main/java/start/"
mv "src/main/java/start/Start.class" "build/src/main/java/start/"

mv "src/main/java/UI/TerminalUI.class" "build/src/main/java/UI/"
mv "src/main/java/UI/GUI/GameMapPanel.class" "build/src/main/java/UI/GUI/"
mv "src/main/java/UI/GUI/GUI.class" "build/src/main/java/UI/GUI/"
mv "src/main/java/UI/GUI/GameState.class" "build/src/main/java/UI/GUI/"

mv "src/main/java/UI/GUI/GUI\$1.class" "build/src/main/java/UI/GUI/"
mv "src/main/java/UI/GUI/GUI\$2.class" "build/src/main/java/UI/GUI/"
mv "src/main/java/UI/GUI/GUI\$3.class" "build/src/main/java/UI/GUI/"
mv "src/main/java/UI/GUI/GUI\$4.class" "build/src/main/java/UI/GUI/"
mv "src/main/java/UI/GUI/GUI\$5.class" "build/src/main/java/UI/GUI/"

mv "src/main/java/UI/GUI/GameMapPanel\$1.class" "build/src/main/java/UI/GUI/"
mv "src/main/java/UI/GUI/GameMapPanel\$2.class" "build/src/main/java/UI/GUI/"

mv "src/main/java/UI/GUI/GameState\$1.class" "build/src/main/java/UI/GUI/"
mv "src/main/java/UI/GUI/GameState\$2.class" "build/src/main/java/UI/GUI/"
mv "src/main/java/UI/GUI/GameState\$3.class" "build/src/main/java/UI/GUI/"
mv "src/main/java/UI/GUI/GameState\$4.class" "build/src/main/java/UI/GUI/"
mv "src/main/java/UI/GUI/GameState\$5.class" "build/src/main/java/UI/GUI/"



echo "Fin du script buid.sh, pour lancer le programme, veuillez executer le script run.sh"

}


# On sort de script si on y est pour correctement lancer le projet
repo_actuel=$(basename "$(pwd)")
if [ "$repo_actuel" == "script" ]; then
    cd ..
fi


# Chemin du répertoire à supprimer
repertoire="build"

#On vérifie si le répertoire existe pour le supprimer (lui et son contenu) en cas de conflits
if [ -d "$repertoire" ]; then
    rm -rf "$repertoire"
    build
else
    build
fi