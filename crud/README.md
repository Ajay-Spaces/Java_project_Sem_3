# CRUD Operation in Java with JDBC

A complete JDBC-based CRUD application for managing employee data. Choose your preferred database!

## ✅ Fixed JDBC Issues

- **SQL Injection Prevention**: Uses `PreparedStatement` with parameterized queries
- **Resource Management**: Implements try-with-resources and proper connection cleanup
- **Multiple Database Options**: MySQL, SQLite, or Mock Database

---

## 🚀 Quick Start (Recommended: SQLite - No Setup Required!)

```bash
cd /workspaces/Java_project_Sem_3/crud
./run-sqlite.sh
```

SQLite version creates a local `employees.db` file automatically. **No external database needed!**

---

## Running the Application

### Option 1: SQLite (✅ RECOMMENDED - No setup required)
```bash
./run-sqlite.sh
```
**Pros:** Works immediately, no MySQL needed, database file stored locally
**Cons:** Single-user only

### Option 2: Mock Database (In-memory, no persistence)
```bash
java -cp src MainWithMockDB
```
**Pros:** Fast, no files, good for testing logic
**Cons:** Data lost on exit

### Option 3: MySQL (Production-grade)
First, ensure MySQL is running:
```bash
./run.sh
```
**Pros:** Full enterprise database, multi-user support
**Cons:** Requires MySQL server setup

---

## Database Setup (MySQL Only)

If using MySQL, create the database:

```sql
CREATE DATABASE employeedb;

USE employeedb;

CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50),
    location VARCHAR(50)
);
```

---

## Files

```
crud/
├── src/
│   ├── Main.java                    # MySQL version
│   ├── MainSQLite.java              # SQLite version ✅ RECOMMENDED
│   └── MainWithMockDB.java          # In-memory mock version
├── lib/
│   ├── mysql-connector-java-8.0.11.jar
│   └── sqlite-jdbc-3.44.0.0.jar
├── run.sh                           # MySQL runner
├── run-sqlite.sh                    # SQLite runner ✅ RECOMMENDED
└── run-mock.sh                      # Mock database runner
```

---

## Features

1. **Create Data** - Add new employee records
2. **Read Data** - Display all employees
3. **Update Data** - Modify employee information (ID, Name, Location, or Department)
4. **Delete Data** - Remove employee records
5. **Exit** - Close the application

---

## Security Features

- ✅ **PreparedStatement** prevents SQL injection attacks
- ✅ **Try-with-resources** ensures proper resource cleanup
- ✅ **Exception handling** for database errors
- ✅ **Parameterized queries** throughout the application

---

## Compilation (Manual)

```bash
cd crud

# SQLite version (recommended)
javac -cp lib/sqlite-jdbc-3.44.0.0.jar src/MainSQLite.java

# MySQL version
javac -cp lib/mysql-connector-java-8.0.11.jar src/Main.java

# Mock version
javac src/MainWithMockDB.java
```

---

## Execution (Manual)

```bash
cd crud

# SQLite (recommended)
java -cp .:src:lib/sqlite-jdbc-3.44.0.0.jar MainSQLite

# MySQL
java -cp .:lib/mysql-connector-java-8.0.11.jar Main

# Mock
java -cp src MainWithMockDB
```

---

## Summary

| Option | Setup Required | Data Persistence | Use Case |
|--------|----------------|------------------|----------|
| **SQLite** ✅ | None | Yes (.db file) | Development, Testing, Demo |
| **MySQL** | Database + Server | Yes | Production, Multi-user |
| **Mock** | None | No | Logic testing only |

**Start with SQLite for a hassle-free experience!**
