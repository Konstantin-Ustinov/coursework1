import java.util.InputMismatchException;
import java.util.Scanner;

import services.EmployeeService;
import utils.util;

public class Main {

    public static void main(String[] args) {
        EmployeeService.firstFillingEmployees();
        mainMenu();
    }

    public static void mainMenu() {
        String input;
        boolean isShowMenu = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в Менеджер сотрудников!");

        do {
            System.out.println("_________________");
            System.out.println("Введите комманду:");
            System.out.println(" \"1\" - Показать всех Сотрудников;");
            System.out.println(" \"2\" - Посчитать сумму затрат на зарплаты в месяц;");
            System.out.println(" \"3\" - Найти сотрудника с минимальной зарплатой;");
            System.out.println(" \"4\" - Найти сотрудника с максимальной зарплатой;");
            System.out.println(" \"5\" - Подсчитать среднее значение зарплат;");
            System.out.println(" \"6\" - Получить Ф. И. О. всех сотрудников;");
            System.out.println(" \"7\" - Проиндексировать зарплату;");
            System.out.println(" \"8\" - Зайти в отдел;");
            System.out.println(" \"9\" - Найти всех сотрудников с зарплатой меньше числа;");
            System.out.println(" \"10\" - Найти всех сотрудников с зарплатой больше числа;");
            System.out.println(" \"11\" - Добавить сотрудника;");
            System.out.println(" \"12\" - Удалить сотрудника;");
            System.out.println(" \"13\" - Изменить сотрудника;");
            System.out.println(" \"14\" - Получить ФИО всех сотрудников по отделам;");
            System.out.println(" \"0\" - Выход.");

            input = scanner.next();

            switch (input) {
                case "1": showAll("all"); break;
                case "2": showSumAllSalary("all"); break;
                case "3": findMinSalary("all"); break;
                case "4": findMaxSalary("all"); break;
                case "5": findAverageSalary("all"); break;
                case "6": showAllFio(); break;
                case "7": riseAllSalary("all", scanner); break;
                case "8": departmentMenu(inputDepartment(scanner), scanner); break;
                case "9": findAllBySalaryLimit("less", scanner); break;
                case "10": findAllBySalaryLimit("more", scanner); break;
                case "11": addEmployee(scanner); break;
                case "12": deleteEmployee(scanner); break;
                case "13": changeEmployee(scanner); break;
                case "14": showAllEmployeesByDepartments(scanner); break;
                case "0": isShowMenu = false; break;
                default: System.out.println("Такой комманды нет. Выберете комманду из списка");
            }
        } while (isShowMenu);
    }

    private static void departmentMenu(String department, Scanner scanner) {
        String input;
        boolean isShowMenu = true;
        System.out.println("Добро пожаловать в отдел " + department);

        do {
            System.out.println("_________________");
            System.out.println("Введите комманду:");
            System.out.println(" \"1\" - Показать всех Сотрудников отдела;");
            System.out.println(" \"2\" - Посчитать сумму затрат на зарплаты в месяц по отделу;");
            System.out.println(" \"3\" - Найти сотрудника с минимальной зарплатой в отделе;");
            System.out.println(" \"4\" - Найти сотрудника с максимальной зарплатой в отделе;");
            System.out.println(" \"5\" - Подсчитать среднюю зарплату по отделу;");
            System.out.println(" \"6\" - Проиндексировать зарплату всех сотрудников отдела;");
            System.out.println(" \"0\" - Вернуться в главное меню.");

            input = scanner.next();

            switch (input) {
                case "1": showAll(department); break;
                case "2": showSumAllSalary(department); break;
                case "3": findMinSalary(department); break;
                case "4": findMaxSalary(department); break;
                case "5": findAverageSalary(department); break;
                case "6": riseAllSalary(department, scanner); break;
                case "0": isShowMenu = false; break;
                default: System.out.println("Такой комманды нет. Выберете комманду из списка");
            }
        } while (isShowMenu);
    }

    private static void showAll(String department) {
        if (department.equals("all")) {
            util.showMessage("Показаны все сотрудники:");
        } else {
            util.showMessage("Показаны все сотрудники отдела:" + department);
        }
        System.out.println(EmployeeService.showAll(department));
    }

    private static void showSumAllSalary(String department) {
        int sum = EmployeeService.sumAllSalary(department);
        util.showMessage("Общая сумма зарплат в месяц: " + sum + EmployeeService.pluralizeRubles(sum));
    }

    private static void findMinSalary(String department) {
        double min = EmployeeService.findMinSalary(department);
        util.showMessage("Минимальная зарплата: " + min + EmployeeService.pluralizeRubles((int) min));
    }

    private static void findMaxSalary(String department) {
        double max = EmployeeService.findMaxSalary(department);
        util.showMessage("Максимальная зарплата: " + max + EmployeeService.pluralizeRubles((int) max));
    }

    private static void findAverageSalary(String department) {
        double av = EmployeeService.findAverageSalary(department);
        util.showMessage("Средняя зарплата: " + av + EmployeeService.pluralizeRubles((int) av));
    }

    private static void showAllFio() {
        util.showMessage("Показаны все Ф.И.О. сотрудников:");
        System.out.println(EmployeeService.showAllFio());
    }

    private static void riseAllSalary(String department, Scanner scanner) {
        System.out.println("Введите % подъема зарплаты целым числом (например 10)");
        int percent = inputNum(scanner);
        if (EmployeeService.riseSalary(department, percent)) {
            util.showMessage("Зарплаты всех сотрудников увеличены на " + percent + " %");
        }
    }

    private static String inputDepartment(Scanner scanner) {
        System.out.println("Введите название отдела (1-5)");
        String department = "";
        while (department.equals("")) {
            department = scanner.next();
        }
        return department;
    }

    private static void findAllBySalaryLimit(String lessOrMore, Scanner scanner) {
        System.out.println("Введите лимит зарплаты целым числом (например 50000)");
        int limit = inputNum(scanner);
        if (lessOrMore.equals("less")) {
            util.showMessage("Показаны все сотрудники с зарплатой меньше " + limit + ":");
            System.out.println(EmployeeService.showAllSalaryLessLimit(limit));
        } else if (lessOrMore.equals("more")){
            util.showMessage("Показаны все сотрудники с зарплатой больше " + limit + ":");
            System.out.println(EmployeeService.showAllSalaryMoreLimit(limit));
        }
    }

    private static int inputNum(Scanner scanner) {
        int input = -1;
        while (input == -1) {
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели не целое число, повторите попытку.");
            }
            if (input < 0 ) {
                System.out.println("Введите положительное число.");
                input = 0;
            }
        }
        return input;
    }

    private static void addEmployee(Scanner scanner) {
        System.out.println("Введите ФИО сотрудника");
        String fio = "";
        while (fio.equals("")) {
            fio = scanner.next();
        }
        System.out.println("Введите Отдел");
        String department = inputDepartment(scanner);
        System.out.println("Введите зарплату сотрудника");
        int salary = inputNum(scanner);
        if (EmployeeService.addEmployee(fio, department, salary)) {
            util.showMessage("Сотрудник успешно добавлен!");
        } else {
            util.showMessage("Сотрудник не добавлен. В массиве нет места.");
        }
    }

    private static void deleteEmployee(Scanner scanner) {
        // удаление и изменение только по id, т.к. могут быть полные тезки.
        System.out.println("Введите id сотрудника для удаления:");
        showAll("all");
        int id = inputNum(scanner);
        if (EmployeeService.deleteEmployee(id)) {
            util.showMessage("Сотрудник успешно удален!");
        } else {
            util.showMessage("Такой сотрудник не найден.");
        }
    }

    private static void changeEmployee(Scanner scanner) {
        System.out.println("Введите id сотрудника для изменения:");
        showAll("all");
        int id = inputNum(scanner);
        System.out.println("Что вы хотите изменить? (1 - Зарплату | 2 - Отдел)");
        int choice = inputNum(scanner);
        if (choice == 1) {
            System.out.println("Введите зарплату:");
            int salary = inputNum(scanner);
            if (EmployeeService.setSalary(id, salary)) {
                util.showMessage("Зарплата изменена!");
            } else {
                util.showMessage("Такой сотрудник не найден.");
            }
        } else if (choice == 2) {
            System.out.println("Введите отдел:");
            String department = inputDepartment(scanner);
            if (EmployeeService.setDepartment(id, department)) {
                util.showMessage("Отдел успешно изменен!");
            } else {
                util.showMessage("Такой сотрудник не найден.");
            }
        }

    }

    private static void showAllEmployeesByDepartments(Scanner scanner) {
        for (int i = 1; i <= 5; i++) {
            showAll(Integer.toString(i));
        }

    }
}
