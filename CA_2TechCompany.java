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
                    case QUERY_EMPLOYEE:
                        queryEmployee();
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
                scanner.nextLine();  // Consume the newline character

                if (choice >= 1 && choice <= MenuOptions.values().length) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number between 1 and " + MenuOptions.values().length);
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        return choice;
    }

    private static void addEmployee() {
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

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

    private static void queryEmployee() {
        System.out.print("Enter Employee Name to Search: ");
        String searchName = scanner.nextLine().trim();

        boolean found = false;
        for (Employee e : employees) {
            if (e.getName().equalsIgnoreCase(searchName)) {
                System.out.println("Employee Found: " + e);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Employee not found.");
        }
   
    
        } 
    }

            
    
    

