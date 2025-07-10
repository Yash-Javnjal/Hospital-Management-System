# 🏥 HOSPITAL MANAGEMENT SYSTEM
[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.oracle.com/java/) 
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql)](https://www.postgresql.org/) 
[![Maven](https://img.shields.io/badge/Maven-3.8.6-orange?logo=apachemaven)](https://maven.apache.org/) 
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

---

The **HOSPITAL MANAGEMENT SYSTEM** is a **console-based application** built using **CORE JAVA**, **JDBC**, and **POSTGRESQL**. It allows users to perform various operations to manage doctor and patient records stored in a PostgreSQL database. The application provides an **intuitive MENU-DRIVEN INTERFACE** for seamless hospital administration.  

---

# ✨ FEATURES

- 🩺 **ADD DOCTOR**: Add a new doctor record with name and specialty.  
- 📜 **VIEW ALL DOCTORS**: Fetch and display all doctor records.  
- ❌ **REMOVE DOCTOR**: Delete a doctor record by name.  
- 👩‍⚕️ **ADD PATIENT**: Add a new patient record with details such as name, age, ID, and diagnosed disease.  
- 🔗 **ASSIGN DOCTOR**: Assign a doctor to treat a patient.  
- 💊 **PRESCRIBE MEDICINE**: Automatically prescribe medicine based on the patient’s disease.  
- 👀 **VIEW ALL PATIENTS**: Fetch and display all patient records, including assigned doctor and prescribed medicine.  
- 🗑 **REMOVE PATIENT**: Delete a patient record by patient ID.  
- 🖥 **MENU-DRIVEN INTERFACE**: Easy-to-use console interface for managing doctors and patients.  
- ⏳ **BACKGROUND THREAD**: Simulates background tasks in the system.  

---

# 🛠 TECHNOLOGIES USED

- **CORE JAVA**: For implementing application logic and OOP concepts.  
- **JDBC (JAVA DATABASE CONNECTIVITY)**: For connecting to and interacting with the POSTGRESQL database.  
- **POSTGRESQL**: For storing and managing hospital data.  
- **MAVEN**: For project dependency management and building the project.  

---

# 📦 DEPENDENCIES

This project uses the following Maven dependency:  

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.6.0</version>
</dependency>
```
---


# 🖥 PREREQUISITES
Make sure you have:

✅ **JAVA DEVELOPMENT KIT (JDK) 17 or higher**.

✅ **APACHE MAVEN 3.8.6 or higher**.

✅ **POSTGRESQL 15 or higher**.

---

# 🗄 DATABASE SETUP
**Create a PostgreSQL database named hospitaldb with the following tables:**

```sql

CREATE TABLE doctor (
    dName VARCHAR(100) PRIMARY KEY,
    speciality VARCHAR(100) NOT NULL
);

CREATE TABLE patient (
    pName VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    p_id INT PRIMARY KEY,
    disease VARCHAR(100) NOT NULL,
    assiDoc VARCHAR(100),
    medicine VARCHAR(100),
    FOREIGN KEY (assiDoc) REFERENCES doctor(dName)
);
```
<<<<<<< HEAD

=======
>>>>>>> 846718022bc8450b929038f8610ae493a24c855f
---

# Project Structure
```graphql

com/yash/
├── DBConnection.java       # Database connection utility class
├── Doctor.java             # Model & DAO class for Doctor
├── Patient.java            # Model & DAO class for Patient
├── Threading.java          # Multithreaded background simulation
└── App.java                # Main application class (menu-driven UI)
```
---

# 🚀 How to run
-**Clone the repository or download the project.**

-**Open the project in IntelliJ IDEA, Eclipse, or VS Code.**

-**Configure PostgreSQL credentials in DBConnection.java:**

```java
<<<<<<< HEAD
private static final String URL = "jdbc:postgresql://localhost:5432/hospitaldb";
private static final String USER_NAME = "postgres";
private static final String PASSWD = "your_password";
=======

private static final String URL = "jdbc:postgresql://localhost:5432/hospitaldb";
private static final String USER_NAME = "postgres";
private static final String PASSWD = "your_password";
```

---
>>>>>>> 846718022bc8450b929038f8610ae493a24c855f

```

---
```bash
<<<<<<< HEAD
Build the project:
mvn clean install
Run the application:
```

---

```bash
java -cp target/hospital-management-system-1.0-SNAPSHOT.jar com.yash.App
```
**Use the menu options to manage doctors and patients.**
=======
mvn clean install
```
----

```bash
>>>>>>> 846718022bc8450b929038f8610ae493a24c855f

java -cp target/hospital-management-system-1.0-SNAPSHOT.jar com.yash.App
**//Use the menu options to manage doctors and patients.**
```
----


# 📬 CONTACT
**DEVELOPER: YASH JAVANJAL**
**EMAIL: yashjavanjal2512@gmail.com**
**GITHUB: Yash-Javnjal**

Enjoy managing your hospital with HOSPITAL MANAGEMENT SYSTEM! 🚑💉

# Image for Reference!

![hospital](hospital.png)
