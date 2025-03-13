
# Mega City Cab

**Mega City Cab** is a web-based cab management system built using **Java JSP and Servlets**. It is designed to streamline cab booking operations, manage customer details and handle billing calculations efficiently. The system includes features for both customers and administrators, with role-based access control.

---

## Table of Contents
1. [Features](#1-features)
   - [Customer Features](#11-customer-features)
   - [Admin Features](#12-admin-features)
2. [Technologies Used](#2-technologies-used)
3. [Setup Instructions](#3-setup-instructions)
   - [Prerequisites](#31-prerequisites)
   - [Database Setup](#32-database-setup)
   - [Application Setup](#33-application-setup)
4. [Accessing the Application](#4-accessing-the-application)
5. [Key Functionalities](#5-key-functionalities)
6. [Project Structure](#6-project-structure)
7. [Notes](#7-notes)
8. [Contributing](#8-contributing)
9. [License](#9-license)

---

## 1. Features

### 1.1 Customer Features
1. **Cab Booking**: Customers can book cabs by providing details such as name, address, telephone number and destination.
2. **Registration**: New customers can register in the system by providing necessary details like name, address, NIC, etc.

### 1.2 Admin Features
1. **Dashboard**: Admin can manage drivers, vehicles and view/delete users.
2. **Fare Management**: Admin can edit fare information, including base fare, tax percentage and discount percentage.
3. **User Management**: Only the super admin can create other admin accounts (only the super admin has full privileges).

---

## 2. Technologies Used
- **Backend**: Java Servlets, JSP
- **Database**: MySQL
- **Server**: Apache Tomcat 9
- **Java Version**: 17
- **Dependencies**: MySQL Connector, JSTL, JBCrypt, JUnit, Mockito (see `pom.xml` for details)

---

## 3. Setup Instructions

### 3.1 Prerequisites
1. **Java 17**: Ensure Java 17 is installed on your system.
2. **MySQL**: Install MySQL and create a database named `mega-city-cab_db`.
3. **Apache Tomcat 9**: Download and install Tomcat 9 from [here](https://tomcat.apache.org/download-90.cgi).

### 3.2 Database Setup
1. Create a MySQL database named `mega-city-cab_db`:
   ```sql
   CREATE DATABASE mega-city-cab_db;
   ```
2. Import the SQL file located at:
   ```
   src/main/resources/database/mega-city-cab_db.sql
   ```
3. The SQL file includes a **super admin account** with the following credentials:
   - **Username**: `admin`
   - **Password**: `admin`

### 3.3 Application Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/lahik/mega-city-cab.git
   ```
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
3. Build the project using Maven:
   ```sh
   mvn clean install
   ```
4. Deploy the generated `.war` file (`mega-city-cab.war`) to **Tomcat 9**.
5. Start the Tomcat server.

---

## 4. Accessing the Application
- **Admin Dashboard**: Navigate to `/admin`. If no admin session is detected, you will be redirected to `/admin/login`.
- **Super Admin Credentials**:
  - **Username**: `admin`
  - **Password**: `admin`

---

## 5. Key Functionalities
1. **Authentication**: Secure login for both customers and admins.
2. **Customer Management**: Register new customers and manage their bookings.
3. **Booking Management**: Add, view and manage cab bookings.
4. **Billing**: Calculate and print bills based on booking details, including taxes and discounts.
5. **Driver and Vehicle Management**: Admins can manage driver and vehicle information.
6. **Fare Configuration**: Admins can configure base fare, tax percentage and discount percentage.

---

## 6. Project Structure
- **SQL File**: `src/main/resources/database/mega-city-cab_db.sql`
- **Dependencies**: Managed via `pom.xml`.
- **War File**: Generated as `mega-city-cab.war` after building the project.

---

## 7. Notes
1. The **super admin** has full access to all functionalities, including creating other admins.  
2. The **admin** can manage drivers, vehicles and users but does not have the ability to create super admins.

---

## 8. Contributing
Feel free to contribute by submitting issues or pull requests.

---

## 9. License
This project is open-source under the **MIT License**.
