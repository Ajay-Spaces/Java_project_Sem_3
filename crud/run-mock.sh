#!/bin/bash

# Compile and run the CRUD application with Mock Database
# No MySQL required!

cd "$(dirname "$0")"

echo "Compiling MainWithMockDB.java..."
javac src/MainWithMockDB.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo "Running application..."
    java -cp src MainWithMockDB
else
    echo "✗ Compilation failed!"
    exit 1
fi
