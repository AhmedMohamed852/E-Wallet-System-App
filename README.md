# 💳 E-Wallet System

A simple Java web application that simulates an electronic wallet system. Users can register, log in, manage their wallet, transfer money, and check their balance.

## 🚀 Features

- User Registration
- User Login & Logout
- Deposit Money
- Withdraw Money
- Transfer Money
- Check Wallet Balance
- Session & Cookie Authentication

## 🛠 Technologies Used

- Java
- Servlet
- JSP
- JDBC
- Oracle Database
- Apache Tomcat 9
- HTML
- CSS

## 🗄 Database

The project uses Oracle Database with two main tables:

- **USERS_ACCOUNT**
- **WALLETS**

Relationship:

```
USERS_ACCOUNT (1) -------- (1) WALLETS
```

## 📂 Project Structure

```
src
├── controller
├── model
├── walletService
├── WalletServiceImpl

webapp
├── pages
│   ├── login.jsp
│   ├── signup.jsp
│   ├── home.jsp
│   ├── deposit.jsp
│   ├── withdraw.jsp
│   ├── transfer.jsp
│   ├── balance.jsp
│   └── error.jsp
```

## ▶️ How to Run

1. Clone the repository.
2. Import the project into Eclipse as a **Dynamic Web Project**.
3. Configure **Apache Tomcat 9**.
4. Configure the Oracle JDBC DataSource.
5. Create the database tables.
6. Run the project.

## 📸 System Screens

- Login
- Sign Up
- Home Dashboard
- Deposit
- Withdraw
- Transfer
- Balance

## 👨‍💻 Author

**Ahmed Mohamed ElBuhairi**
- Java Backend Developer
- Java Mentor at EraaSoft

---

⭐ If you like this project, don't forget to give it a star.
