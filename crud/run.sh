#!/bin/bash

# Compile and run the CRUD application
# This script assumes MySQL is running at localhost:3306

cd "$(dirname "$0")"

echo "Compiling Main.java..."
javac -cp lib/mysql-connector-java-8.0.11.jar -d . src/Main.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo "Running application..."
    java -cp .:lib/mysql-connector-java-8.0.11.jar Main
else
    echo "✗ Compilation failed!"
    exit 1
fi
