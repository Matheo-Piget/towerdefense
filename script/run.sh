#!/bin/bash

# Fonction qui lance la classe Start
launch() {
    java -cp build src.main.java.start.Start
}

# On sort de script si on y est pour correctement lancer le projet
if [ $(pwd) = "script" ]; then
    cd ..
    pwd
fi


# Vérification de l'existence du répertoire build
repertoire="build"

if [ -d "$repertoire" ]; then
    echo "Lancement du programme en cours..."
    launch
else
    echo "Erreur : le répertoire build n'existe pas"
    echo "Merci de lancer le script build.sh avant de lancer le script run.sh"
fi