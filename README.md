# 📚 Library Management System

A **Java Swing and MySQL-based Library Management System** that helps manage books, users, and borrowing operations efficiently.  
It includes secure user authentication, role-based access control (Admin & Student), and a clean GUI built with Swing.

---

## 🚀 Features

✅ **User Authentication** – Secure login for admin and students  
✅ **Role-Based Access** – Admin manages books & users; Students can borrow and return  
✅ **Book Management** – Add, edit, delete, and search books  
✅ **Borrowing System** – Track issued and returned books  
✅ **Database Integration** – MySQL via JDBC  
✅ **Error Handling** – Exception management for smoother user experience  

---

## 🗂️ Project Structure

library_management_system/
├─ src/
│ ├─ app/
│ │ └─ Main.java
│ ├─ dao/
│ │ ├─ BookDAO.java
│ │ ├─ BorrowDAO.java
│ │ └─ UserDAO.java
│ ├─ models/
│ │ ├─ Book.java
│ │ └─ User.java
│ ├─ ui/
│ │ ├─ AdminFrame.java
│ │ ├─ LoginFrame.java
│ │ └─ StudentFrame.java
│ └─ utils/
│ └─ DBConnection.java
│
├─ db/
│ └─ schema.sql
│
├─ lib/
│ └─ mysql-connector-j-8.0.xx.jar
│
├─ README.md
└─ build_instructions.txt


---

## ⚙️ Tech Stack

| Component | Technology |
|------------|-------------|
| Language | Java (JDK 8 or above) |
| GUI | Java Swing |
| Database | MySQL |
| Connectivity | JDBC |
| IDE (Recommended) | Eclipse / IntelliJ IDEA |

