#!/bin/bash

# Compile and run the CRUD application
# Works immediately - no database setup required!

cd "$(dirname "$0")"

echo "Compiling CRUDApp.java..."
javac src/CRUDApp.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo "Running CRUD Application..."
    java -cp src CRUDApp
else
    echo "✗ Compilation failed!"
    exit 1
fi
