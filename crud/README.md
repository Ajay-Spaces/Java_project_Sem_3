# CRUD Operation in Java with JDBC

A complete JDBC-based CRUD application for managing employee data with MySQL database.

## ✅ Fixed JDBC Issues

- **SQL Injection Prevention**: Uses `PreparedStatement` with parameterized queries
- **Resource Management**: Implements try-with-resources and proper connection cleanup
- **Automatic Classpath**: Includes MySQL JDBC driver in `lib/` folder

## Prerequisites

- Java 21 or higher
- MySQL Server running on `localhost:3306`
- Database: `employeedb`
- Table: `employees` (id, name, department, location)

## Database Setup

Create the database and table:

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

## Compilation

```bash
cd crud
javac -cp lib/mysql-connector-java-8.0.11.jar -d . src/Main.java
```

## Execution

**Option 1: Using run script**
```bash
./run.sh
```

**Option 2: Direct Java command**
```bash
cd crud
java -cp .:lib/mysql-connector-java-8.0.11.jar Main
```

## Features

1. **Create Data** - Add new employee records
2. **Read Data** - Display all employees
3. **Update Data** - Modify employee information (ID, Name, Location, or Department)
4. **Delete Data** - Remove employee records
5. **Exit** - Close the application

## Security Features

- ✅ PreparedStatement prevents SQL injection attacks
- ✅ Try-with-resources ensures proper resource cleanup
- ✅ Exception handling for database errors

## File Structure

```
crud/
├── src/
│   └── Main.java          # Main CRUD application
├── lib/
│   └── mysql-connector-java-8.0.11.jar   # MySQL JDBC Driver
├── crud.iml               # IntelliJ project file
└── run.sh                 # Convenient run script
```

## Notes

- Credentials: User `root` with no password (modify in `getConnection()` method if needed)
- The application uses proper JDBC best practices
- All resource leaks have been fixed
