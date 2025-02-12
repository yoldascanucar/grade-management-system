# Grade Management System

## Overview
A web-based grade management system developed to streamline the tracking and management of student grades. The system provides separate interfaces for teachers and students, offering a comprehensive solution for academic grade management.

## Features
### User Authentication & Management

- Separate login portals for teachers and students
- Automated user number generation for new accounts
- Robust login validation with automatic session timeout
- Email confirmation system for new user enrollment

### Grade Management

- Dynamic grading system for midterm and final exams
- Automatic calculation of averages and letter grades
- Real-time grade updates with email notifications
- Comprehensive grade history tracking

### Access Control & Security

- Role-based authorization controls
- Restricted access to class pages based on user roles
- Secure session management
- Protected student information

### User Interface

- Digital ID cards for student information display
- Intuitive dashboard for grade management
- User-friendly navigation system

### Technologies Used

- Backend: Core Java, Java Servlets
- Frontend: Javascript
- Database: MySQL
- ORM: Hibernate
- Database Connectivity: JDBC

### Setup Instructions

#### Prerequisites

Ensure you have Java, Maven, MySQL, and Apache Tomcat installed on your system.

Verify that the MySQL server is running and accessible.

- Clone the repository

git clone https://github.com/yoldascanucar/grade-management-system.git

- Create a MySQL database and execute SQL scripts from the `sql` folder

- Update application.properties with your database credentials
   
          db.url=jdbc:mysql://localhost:3306/grade_management
 
          db.username=your_username

          db.password=your_password

- Build the project

          mvn clean install

- Deploy the project on Apache Tomcat:

          Copy the generated WAR file (`target/grade-management-system.war`)
    
          Paste it into the Tomcat `webapps/` directory
   
          Start Tomcat and access the application at:

 - Access the application at  http://localhost:8080/GradeManagementSystem/
