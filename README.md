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
```
CREATE DATABASE employee_db;
```
#### Create the Table
Switch to the newly created employee_db database:
```
\c employee_db
```
Then create the employee table using the following SQL command:
```
ALTER TABLE employee RENAME COLUMN hire_date TO hireDate;

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    salary DOUBLE PRECISION NOT NULL,
    hire_date DATE NOT NULL
);
```
#### Insert Sample Data (Optional)
You can populate the employee table with some sample data:
```
INSERT INTO employee (name, position, salary, hire_date)
VALUES
    ('Kokumova Aidana', 'Data Analyst', 65000.00, '2024-02-02'),
    ('Bekten uly Mukhammed', 'Software Engineer', 70000.00, '2022-05-05');
```
To verify the inserted data, use the following SQL query:
```
SELECT * FROM employee;
```
### 2. Java Setup

#### Install Java
Ensure you have Java JDK 8 or later installed on your system.
#### Add JDBC Dependency
If you're using Maven, add the PostgreSQL JDBC driver dependency to your pom.xml file:
```
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.6.0</version> <!-- Use the latest version -->
</dependency>
```
#### Configure Database Credentials
Update the EmployeeData class with your PostgreSQL database credentials:
```
private static final String URL = "jdbc:postgresql://localhost:5432/employee_db";
private static final String USER = "postgres";
private static final String PASSWORD = "your_password"; // Use your actual password
```
### 3. Running the Program

After setting up the database and configuring the Java environment, you can run the program to perform CRUD operations on the employee table.

#### Main Program (Main.java):
This is where the application starts. It demonstrates creating a new employee, retrieving employee details by ID, fetching all employees, updating an employee’s details, and deleting an employee.
```
package org.example;

import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        EmployeeData data = new EmployeeData();

        try {
            // Create a new employee
            Employee emp1 = new Employee("Aidana Kokumova", "Developer", 70000, new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-15"));
            data.createEmployee(emp1);

            // Read employee by ID
            Employee fetchedEmp = data.getEmployeeById(1);
            if (fetchedEmp != null) {
                System.out.println("Fetched Employee: " + fetchedEmp);
            } else {
                System.out.println("Employee with ID 1 not found.");
            }

            // Read all employees
            System.out.println("All Employees:");
            data.getAllEmployees().forEach(System.out::println);

            // Update employee details
            if (fetchedEmp != null) {
                fetchedEmp.setPosition("Senior Developer");
                fetchedEmp.setSalary(80000);
                data.updateEmployee(fetchedEmp);
                System.out.println("Updated Employee: " + data.getEmployeeById(1));
            }

            // Delete employee
            data.deleteEmployee(1);
            System.out.println("All Employees After Deletion:");
            data.getAllEmployees().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
#### Class Overview:
~ Employee: A class representing an employee with fields like id, name, position, salary, and hireDate.
~ EmployeeData: A class that handles database operations such as creating an employee (createEmployee), retrieving an employee by ID (getEmployeeById), retrieving all employees (getAllEmployees), updating an employee (updateEmployee), and deleting an employee (deleteEmployee).
### Expected Output
When you run the program, you should see output similar to the following:
```
Fetched Employee: Employee{id=1, name='Aidana Kokumova', position='Developer', salary=70000.0, hireDate=2022-01-15}
All Employees:
Employee{id=1, name='Aidana Kokumova', position='Developer', salary=70000.0, hireDate=2022-01-15}
Employee{id=2, name='Mukhammed Bekten uly', position='Product Manager', salary=95000.0, hireDate=2021-11-20}
Updated Employee: Employee{id=1, name='Aidana Kokumova', position='Senior Developer', salary=80000.0, hireDate=2022-01-15}
All Employees After Deletion:
Employee{id=2, name='Mukhammed Bekten uly', position='Product Manager', salary=95000.0, hireDate=2021-11-20}
```
## Screenshots
<img width="1470" alt="Screenshot 2024-11-29 at 15 04 37" src="https://github.com/user-attachments/assets/e6258bcd-f3b3-4145-a728-9eb39dcfe3ce">
<img width="1470" alt="Screenshot 2024-11-29 at 15 05 30" src="https://github.com/user-attachments/assets/d6a318e6-f782-41b2-bf62-53747cc15951">
