#!/bin/bash

# Spécifiez le chemin du répertoire à vérifier et supprimer
repertoire="build"

# Vérifiez si le répertoire existe
if [ -d "$repertoire" ]; then
    rm -rf "$repertoire"
else
    echo "Le répertoire n'existe pas."
fi