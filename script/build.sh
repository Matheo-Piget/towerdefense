#!/bin/bash

# Fonction build qui réalise tout le travail nécessaire à la construction
build(){
# Construction des fichiers .class
    echo "Construction des classes en cours..."
    javac src/main/java/*/*.java
    javac src/main/java/*/*/*.java
    echo "Fin du script buid.sh, pour lancer le programme, veuillez executer le script run.sh"
}

remove(){
    rm src/main/java/*/*.class
    rm src/main/java/*/*/*.class
}

fichier_recherche="src/main/java/configMap/Cellule.class"
if [ -e "$fichier_recherche" ]; then
    remove
    build
else
    build
fi