# Library Management System (Java Swing + MySQL)

## Overview
A simple Library Management System built with Java Swing (GUI) and MySQL (database). 
Features:
- Secure user authentication (simple password check)
- Role-based access: Admin and Student
- Manage books: add, list, delete (Admin)
- Borrow/Return books (Student)
- JDBC-based database connectivity

## Requirements
- Java 8+ (JDK)
- MySQL Server
- MySQL Connector/J (add the .jar to your project's classpath)

## Database setup
1. Create a database and run `db/schema.sql`:
```
CREATE DATABASE library_db;
USE library_db;
SOURCE schema.sql;
```
2. Update DB credentials in `src/utils/DBConnection.java`.

## How to compile & run (command line)
1. Place the MySQL Connector/J jar (e.g. mysql-connector-java-8.0.xx.jar) in the `lib/` folder or add to classpath.
2. Compile:
```
javac -cp "lib/*" -d out $(find src -name "*.java")
```
3. Run:
```
java -cp "out:lib/*" app.Main
```

## Notes
- This is a minimal educational example. For production use, improve password hashing, input validation, and use connection pooling.
