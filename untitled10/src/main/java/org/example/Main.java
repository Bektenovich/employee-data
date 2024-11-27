package org.example;

import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        employeedata data = new employeedata();

        try {
            // Создаем нового сотрудника
            employee emp1 = new employee("Aidana Kokumova", "Developer", 70000, new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-15"));
            data.createEmployee(emp1);

            // Чтение сотрудника по ID
            employee fetchedEmp = data.getEmployeeById(1);
            if (fetchedEmp != null) {
                System.out.println("Fetched Employee: " + fetchedEmp);
            } else {
                System.out.println("Employee with ID 1 not found.");
            }

            // Чтение всех сотрудников
            System.out.println("All Employees:");
            data.getAllEmployees().forEach(System.out::println);

            // Обновление информации о сотруднике
            if (fetchedEmp != null) {
                fetchedEmp.setPosition("Senior Developer");
                fetchedEmp.setSalary(80000);
                data.updateEmployee(fetchedEmp);
                System.out.println("Updated Employee: " + data.getEmployeeById(1));
            }

            // Удаление сотрудника
            data.deleteEmployee(1);
            System.out.println("All Employees After Deletion:");
            data.getAllEmployees().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
