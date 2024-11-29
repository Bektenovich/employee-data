# Employee Database Management

## Project Overview
This project is a simple Java application that interacts with a PostgreSQL database to manage employee data. Using JDBC (Java Database Connectivity), the application performs CRUD operations (Create, Read, Update, Delete) on an employee table in the database.

The project demonstrates the following concepts:

Object-Relational Mapping (ORM) using the Employee class.
Modular and reusable database access with the EmployeeData class.
Safe database interactions using prepared statements.
## Features
Add new employees to the database.
Retrieve employee details by their ID.
View all employees in the database.
Update employee details (e.g., position, salary).
Delete employees by their ID.
## Instructions to Run the Program
### 1. Database Setup

#### Install PostgreSQL
Make sure PostgreSQL is installed and the server is running on your machine.
#### Create the Database
Open a PostgreSQL client (e.g., psql or pgAdmin) and run the following SQL command to create the database:
''' 
CREATE DATABASE employee_db; 
'''
