#!/bin/bash

# Compile and run the CRUD application with SQLite
# No external database required!

cd "$(dirname "$0")"

echo "Compiling MainSQLite.java..."
javac -cp lib/sqlite-jdbc-3.44.0.0.jar src/MainSQLite.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo "Running application..."
    java -cp .:src:lib/sqlite-jdbc-3.44.0.0.jar MainSQLite
else
    echo "✗ Compilation failed!"
    exit 1
fi
