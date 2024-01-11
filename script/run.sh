#!/bin/bash


# On sort de script si on y est pour correctement lancer le projet
repo_actuel=$(basename "$(pwd)")
if [ "$repo_actuel" == "script" ]; then
    cd ..
    java src/main/java/start/Start
fi

java src/main/java/start/Start
