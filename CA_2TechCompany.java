/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca_2techcompany;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class CA_2TechCompany {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
       addSampleEmployees();

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getUserChoice();

            if (choice >= 1 && choice <= MenuOptions.values().length) {
                MenuOptions selectedOption = MenuOptions.values()[choice - 1];

                switch (selectedOption) {
                    case ADD_EMPLOYEE:
                        addEmployee();
                        break;
                    case VIEW_EMPLOYEES:
                        viewEmployees();
                        break;
                    case SEARCH_EMPLOYEE:
                        searchEmployee();
                        break;
                    case EXIT:
                        running = false;
                        System.out.println("Exiting program...");
                        break;
                }
            } else {
                System.out.println("Invalid option. Please choose a number between 1 and " + MenuOptions.values().length);
            }
        }
    }

    /**
     * Adds a predefined list of 5 employees to the system.
     */
    private static void addSampleEmployees() {
        employees.add(new Employee("Maria Henney", ManagerType.HEAD_MANAGER, Department.HR));
        employees.add(new Employee("Jane Smith", ManagerType.ASSISTANT_MANAGER, Department.CUSTOMER_SERVICE));
        employees.add(new Employee("Michael Swim", ManagerType.TEAM_LEAD, Department.TECHNICAL_SUPPORT));
        employees.add(new Employee("Anna Tylor", ManagerType.ASSISTANT_MANAGER, Department.HR));
        employees.add(new Employee("Karen McDonall", ManagerType.TEAM_LEAD, Department.TECHNICAL_SUPPORT));

        System.out.println("Sample employees added successfully.");
    }

    private static void displayMenu() {
        System.out.println("\nMenu Options:");
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println((option.ordinal() + 1) + ". " + option);
        }
        System.out.print("Choose an option: ");
    }

    private static int getUserChoice() {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice >= 1 && choice <= MenuOptions.values().length) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a valid option between 1 and " + MenuOptions.values().length);
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        return choice;
    }

    private static void addEmployee() {
        System.out.print("Enter Employee Name (letters only): ");
        String name = scanner.nextLine().trim();

        while (!name.matches("^[A-Za-z\\s]+$")) {
            System.out.println("Invalid input. Please enter letters only.");
            System.out.print("Enter Employee Name (letters only): ");
            name = scanner.nextLine().trim();
        }

        System.out.println("Select Manager Type:");
        for (ManagerType type : ManagerType.values()) {
            System.out.println((type.ordinal() + 1) + ". " + type);
        }
        int managerIndex = getUserChoice();
        ManagerType managerType = ManagerType.values()[managerIndex - 1];

        System.out.println("Select Department:");
        for (Department dept : Department.values()) {
            System.out.println((dept.ordinal() + 1) + ". " + dept);
        }
        int deptIndex = getUserChoice();
        Department department = Department.values()[deptIndex - 1];

        employees.add(new Employee(name, managerType, department));
        System.out.println("Employee added successfully!");
    }

    private static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees available.");
        } else {
            System.out.println("\nEmployee List:");
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }

    /**
     * Method to search for an employee by name.
     */
    private static void searchEmployee() {
       System.out.print("Enter Employee Name to Search (letters only): ");
    String searchName = scanner.nextLine().trim();

    // Validate input to allow only alphabetic characters and spaces
    while (!searchName.matches("^[A-Za-z\\s]+$")) {
        System.out.println("Invalid input. Please enter letters only, no numbers or symbols.");
        System.out.print("Enter Employee Name to Search (letters only): ");
        searchName = scanner.nextLine().trim();
    }

    boolean found = false;
    System.out.println("\nSearch Results:");

    for (Employee e : employees) {
        if (e.getName().toLowerCase().contains(searchName.toLowerCase())) {
            System.out.println(e);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No employee found with the name containing: " + searchName);
    }
}

        boolean found = false;
        

}
    
       
      





            
    
    

