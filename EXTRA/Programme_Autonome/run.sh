#!/bin/bash
# Se placer dans le dossier du script
cd "$(dirname "$0")"

mkdir -p lib bin
M=https://repo1.maven.org/maven2

# Télécharger les jars manquants
[ -f lib/gson-2.10.1.jar ] || curl -L -o lib/gson-2.10.1.jar $M/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
[ -f lib/core-3.5.3.jar ]  || curl -L -o lib/core-3.5.3.jar  $M/com/google/zxing/core/3.5.3/core-3.5.3.jar
[ -f lib/javase-3.5.3.jar ] || curl -L -o lib/javase-3.5.3.jar $M/com/google/zxing/javase/3.5.3/javase-3.5.3.jar

# Dossier de sortie (une seule fois)
[ -w /usr/local/cdn ] || { sudo mkdir -p /usr/local/cdn && sudo chown "$USER" /usr/local/cdn; }

# Compiler puis lancer
javac -cp "lib/*" -d bin src/main/Main.java && java -cp "bin:lib/*" main.Main