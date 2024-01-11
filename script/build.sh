#!/bin/bash

# Fonction build qui réalise tout le travail nécessaire à la construction
build(){

# Construction des fichiers .class
echo "Construction des classes en cours..."

javac src/main/java/*/*.java
javac src/main/java/*/*/*.java



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
