/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca_2techcompany;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

  //Main class for the Employee Management System.
 
public class CA_2TechCompany {

    private static Scanner scanner = new Scanner(System.in);
    private static List<User> users = new ArrayList<>();
    private static Random random = new Random();
    private static List<Employee> employees = new ArrayList<>();

public static void main(String[] args) {
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
                    case GENERATE_RANDOM_USERS:
                        generateRandomEmployees();
                        break;
                    case SEARCH_EMPLOYEE:
                        searchEmployee();
                        break;
                    case SORT_DUMMY_LIST:
                        System.out.println("Sort Dummy List functionality is under development.");
                        break;
                    case EXIT:
                        running = false;
                        System.out.println("Exiting program...");
                        break;
                }
            } else {
                System.out.println("Invalid option. Please choose a valid number.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMenu Options:");
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println((option.ordinal() + 1) + ". " + option);
        }
        System.out.print("Choose an option: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    /**
     * Adds a new employee.
     */
    private static void addEmployee() {
         String firstName = getValidString("Enter First Name (letters only): ", "^[A-Za-z]+$");
        String lastName = getValidString("Enter Last Name (letters only): ", "^[A-Za-z]+$");
        String email = getValidString("Enter Email: ", "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        String gender = getValidString("Enter Gender (Male/Female): ", "^(Male|Female)$");
        ManagerType managerType = getManagerType();
        Department department = getDepartment();

        int id = 100 + random.nextInt(900);
        Employee newEmployee = new Employee(id, firstName, lastName, email, gender, managerType, department);
        employees.add(newEmployee);

        System.out.println("Employee added successfully:\n" + newEmployee);
    }
     private static ManagerType getManagerType() {
        while (true) {
            System.out.println("\nSelect Manager Type:");
            for (ManagerType type : ManagerType.values()) {
                System.out.println((type.ordinal() + 1) + ". " + type);
            }
            System.out.print("Choose a Manager Type: ");

            int choice = getUserChoice();
            if (choice >= 1 && choice <= ManagerType.values().length) {
                return ManagerType.values()[choice - 1];
            }
            System.out.println("Invalid selection. Please choose a valid option.");
        }
    }

    /**
     * Method to get a valid Department.
     */
    private static Department getDepartment() {
        while (true) {
            System.out.println("\nSelect Department:");
            for (Department dept : Department.values()) {
                System.out.println((dept.ordinal() + 1) + ". " + dept);
            }
            System.out.print("Choose a Department: ");

            int choice = getUserChoice();
            if (choice >= 1 && choice <= Department.values().length) {
                return Department.values()[choice - 1];
            }
            System.out.println("Invalid selection. Please choose a valid option.");
        }
    }
     private static String getValidString(String prompt, String regex) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches(regex)) {
                return input;
            }
            System.out.println("Invalid input. Please try again.");
        }
     }
    private static void generateRandomEmployees() {
        System.out.print("Enter number of employees to generate: ");
        int count = getUserChoice();

        for (int i = 0; i < count; i++) {
            int id = 100 + random.nextInt(900);
            String firstName = "User" + random.nextInt(100);
            String lastName = "Last" + random.nextInt(100);
            String email = "user" + random.nextInt(100) + "@example.com";
            String gender = random.nextBoolean() ? "Male" : "Female";
            ManagerType managerType = ManagerType.values()[random.nextInt(ManagerType.values().length)];
            Department department = Department.values()[random.nextInt(Department.values().length)];

            Employee employee = new Employee(id, firstName, lastName, email, gender, managerType, department);
            employees.add(employee);
        }

        System.out.println("Generated " + count + " employees.");
    }

    /**
     * Searches for an employee by name.
     */
    private static void searchEmployee() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine().trim();
        boolean found = false;

        for (Employee employee : employees) {
            if (employee.toString().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(employee);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No employee found with the name: " + name);
    
    }
   }
 }

    
       
      





            
    
    

