package services;

import db.EmployeeBook;
import entities.Employee;
import static db.EmployeeBook.employees;

public class EmployeeService {
    public static void firstFillingEmployees() {
        Employee employee = new Employee("John Adam Smith", "1", 50_000);
        employees[0] = employee;
        employee = new Employee("Malcolm Masaka Hard", "1", 50_000);
        employees[1] = employee;
        employee = new Employee("Poul Ulrih Moll", "2", 55_000);
        employees[2] = employee;
        employee = new Employee("Fill Oak Seek", "3", 30_000);
        employees[3] = employee;
        //employee = new Employee("Ivanov Ivan Ivanovich", "4", 20_000);
        //employees[4] = employee;
        employee = new Employee("Mikhael Petrovich Shumakher", "5", 50_000);
        employees[5] = employee;
        employee = new Employee("Mike Maks Tyson", "1", 100_000);
        employees[6] = employee;
        employee = new Employee("Max Miks Mini", "2", 690_000);
        employees[7] = employee;
        employee = new Employee("Senior Petrosyan Madam", "3", 550_000);
        employees[8] = employee;
        employee = new Employee("Ilon Java Mask", "5", 590_000);
        employees[9] = employee;
    }

    public static String showAll(String department) {
        StringBuilder sb = new StringBuilder();
        if (department.equals("all")) {
            for (int list = 1, i = 0; i < employees.length; i++) {
                if (employees[i] != null) {
                    sb.append(list).append("). ").append(employees[i].toString()).append("\n");
                    list++;
                }
            }
        } else {
            for (int list = 1, i = 0; i < employees.length; i++) {
                if (employees[i] != null && employees[i].getDepartment().equals(department)) {
                    sb.append(list).append("). ").append(employees[i].toStringWithoutDepartment()).append("\n");
                    list++;
                }
            }
        }
        return sb.toString();
    }

    public static String showAllSalaryLessLimit(double limit) {
        StringBuilder sb = new StringBuilder();
        for (int list = 1, i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getSalary() < limit) {
                sb.append(list).append("). ").append(employees[i].toString()).append("\n");
               list++;
           }
        }
        return sb.toString();
    }

    public static String showAllSalaryMoreLimit(double limit) {
        StringBuilder sb = new StringBuilder();
        for (int list = 1, i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getSalary() >= limit) {
                sb.append(list).append("). ").append(employees[i].toString()).append("\n");
               list++;
           }
        }
        return sb.toString();
    }

    public static int sumAllSalary(String department) {
        int sum = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (department.equals("all")) {
                    sum += employees[i].getSalary();
                } else if (employees[i].getDepartment().equals(department)) {
                    sum += employees[i].getSalary();
                }
            }
        }
        return sum;
    }

    public static double findMinSalary(String department) {
        double min = employees[0].getSalary();
        for (int i = 1; i < employees.length; i++) {
            if (employees[i] != null) {
                if (min > employees[i].getSalary()) {
                    if (department.equals("all")) {
                        min = employees[i].getSalary();
                    } else if (employees[i].getDepartment().equals(department)) {
                        min = employees[i].getSalary();
                    }
                }
            }
        }
        return min;
    }

    public static double findMaxSalary(String department) {
        double max = employees[0].getSalary();
        for (int i = 1; i < employees.length; i++) {
            if (employees[i] != null) {
                if (max < employees[i].getSalary()) {
                    if (department.equals("all")) {
                        max = employees[i].getSalary();
                    } else if (employees[i].getDepartment().equals(department)) {
                        max = employees[i].getSalary();
                    }
                }
            }
        }
        return max;
    }

    public static double findAverageSalary(String department) {
        double sum = sumAllSalary(department);
        return sum / numberOfEmployees(department);
    }

    public static String showAllFio() {
        StringBuilder sb = new StringBuilder();
        for (int list = 1, i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                sb.append(list).append("). ").append(employees[i].getFio()).append("\n");
                list++;
            }
        }
        return sb.toString();
    }

    public static boolean riseSalary(String department, int percent) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (department.equals("all")) {
                    employees[i].setSalary(employees[i].getSalary() * ((percent + 100) / 100.0));
                } else if (employees[i].getDepartment().equals(department)) {
                    employees[i].setSalary(employees[i].getSalary() * ((percent + 100) / 100.0));
                }
            }
        }
        return true;
    }

    public static boolean addEmployee(String fio, String department, int salary) {
        Employee newEmployee = new Employee(fio, department, salary);
        return EmployeeBook.add(newEmployee);
    }

    public static boolean deleteEmployee(int id) {
        return EmployeeBook.delete(id);
    }

    public static boolean setSalary(int id, int salary) {
        return EmployeeBook.setSalary(id, salary);
    }

    public static boolean setDepartment(int id, String department) {
        return EmployeeBook.setDepartment(id, department);
    }

    private static int numberOfEmployees(String department) {
        int num = 0;
        for (int i = 1; i < employees.length; i++) {
            if (employees[i] != null) {
                if (department.equals("all")) {
                    num++;
                } else if (employees[i].getDepartment().equals(department)) {
                    num++;
                }
            }
        }
        return num;
    }

    public static String pluralizeRubles(int num) {
        int end10 = num%10;
        int end100 = num%100;

        if ((end10 >= 2) & (end10 <= 4)) {
            return " рубля";
        } else if ((end100 >= 5 || end10 >= 5) || (end100 == 0 || end10 == 0)){
            return  " рублей";
        } else return " рубль";
    }
}
