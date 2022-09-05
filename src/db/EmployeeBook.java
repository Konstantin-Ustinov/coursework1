package db;

import entities.Employee;

public class EmployeeBook {
    public static Employee[] employees = new Employee[10];

    public static boolean add(Employee newEmployee) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = newEmployee;
                return true;
            }
        }
        return false;
    }

    public static boolean delete(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getId() == id) {
                    employees[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean setSalary(int id, int salary) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getId() == id) {
                    employees[i].setSalary(salary);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean setDepartment(int id, String department) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getId() == id) {
                    employees[i].setDepartment(department);
                    return true;
                }
            }
        }
        return false;
    }


}
