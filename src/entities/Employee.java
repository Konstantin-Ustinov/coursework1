package entities;

import java.util.Objects;

public class Employee {
    static int commonId;
    private int id;
    private String fio;
    private String department;
    private double salary;

    public Employee(String fio, String department, int salary) {
        this.id = commonId++;
        this.fio = fio;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Данные по сотруднику: " +
                "\n id = " + id +
                ",\n ФИО = " + fio +
                ",\n Отдел = " + department +
                ",\n Зарплата = " + Math.round(salary);

    }

    public String toStringWithoutDepartment() {
        return "Данные по сотруднику: " +
                "\n id = " + id +
                ",\n ФИО = " + fio +
                ",\n Зарплата = " + Math.round(salary);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Double.compare(employee.salary, salary) == 0
                && Objects.equals(fio, employee.fio) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, department, salary);
    }
}
