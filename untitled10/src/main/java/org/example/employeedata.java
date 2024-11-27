package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class employeedata {

    private static final String URL = "jdbc:postgresql://localhost:5432/employee_db";
    private static final String USER = "postgres";   // Убедитесь, что это ваши правильные данные
    private static final String PASSWORD = "1234";

    // Метод для установления соединения с базой данных
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE: Добавление нового сотрудника
    public void createEmployee(employee employee) {
        String sql = "INSERT INTO employee (name, position, salary, hire_date) VALUES (?, ?, ?, ?)"; // Используйте hire_date

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getPosition());
            statement.setDouble(3, employee.getSalary());
            statement.setDate(4, new java.sql.Date(employee.getHireDate().getTime()));

            statement.executeUpdate();

            // Получаем сгенерированный ID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                employee.setId(generatedKeys.getInt(1)); // Устанавливаем ID после добавления
            }

            System.out.println("Employee created successfully! ID: " + employee.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: Получение сотрудника по ID
    public employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        employee employee = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                employee = new employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salary"),
                        new java.util.Date(resultSet.getDate("hire_date").getTime()) // Используйте hire_date
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    // READ: Получение всех сотрудников
    public List<employee> getAllEmployees() {
        List<employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                employee employee = new employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salary"),
                        new java.util.Date(resultSet.getDate("hire_date").getTime()) // Используйте hire_date
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    // UPDATE: Обновление информации о сотруднике
    public void updateEmployee(employee employee) {
        String sql = "UPDATE employee SET name = ?, position = ?, salary = ?, hire_date = ? WHERE id = ?"; // Используйте hire_date

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getPosition());
            statement.setDouble(3, employee.getSalary());
            statement.setDate(4, new java.sql.Date(employee.getHireDate().getTime()));
            statement.setInt(5, employee.getId());

            statement.executeUpdate();
            System.out.println("Employee updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE: Удаление сотрудника по ID
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Employee deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
