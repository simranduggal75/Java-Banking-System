🙋‍♀️ Author
Simran Duggal
Java Developer | MCA | Passionate about clean UI and backend systems

# 💳 Java Banking System with GUI

A simple Java Swing-based banking system that allows users to:
- Create accounts
- Login securely
- Deposit & Withdraw money
- View transaction history (auto-logged)
- Data stored and retrieved from MySQL database

## 🛠️ Technologies Used
- Java 18
- Swing (GUI)
- JDBC
- MySQL 8.0+
- MySQL Connector/J 8.0.42

## 📌 Features
- Input validation
- Account login using email/password
- Transaction table with history (with date, type, amount)
- Backend-connected GUI


## ⚙️ Setup Instructions

1. **Clone this repo**
2. **Open in Eclipse / IntelliJ**
3. **Add MySQL JDBC Driver to classpath**
4. **Set your DB credentials in `DBConnection.java`**
5. **Create MySQL DB tables**

## Create the database and tables in MySQL:

CREATE DATABASE bank;

USE bank;

CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    balance DECIMAL(10,2)
);

CREATE TABLE transactions (
    txn_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    amount DECIMAL(10,2),
    txn_type ENUM('deposit', 'withdraw'),
    txn_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);


## Update DB credentials in DBConnection.java

private static final String URL = "jdbc:mysql://localhost:3306/bank";
private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";

## Add MySQL Connector/J JAR to classpath

Right-click project → Build Path → Configure Build Path → Libraries → Add External JAR

## Run MainUi.java to start the application
