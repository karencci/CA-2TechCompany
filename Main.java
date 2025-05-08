/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca_2techcompany;
//agregar una funcion al maincode (filereader ) 

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = DataInput.readFromFile("Applicants_Form.txt");

         if (!employees.isEmpty()) {
            System.out.println("Loaded Employees:");
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        } else {
            System.out.println("No data loaded.");
            
        while (true) {
            MenuOptions.displayMenu();
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    employees.sort(Comparator.comparing(Employee::getName));
                    System.out.println("Sorted List (First 20):");
                    for (int i = 0; i < Math.min(20, employees.size()); i++) {
                        System.out.println(employees.get(i));
                    }
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                    String name = scanner.nextLine();
                    int index = BinarySearch.search(employees, name, 0, employees.size() - 1);
                    if (index != -1) {
                        System.out.println("Found: " + employees.get(index));
                    } else {
                        System.out.println("Not found!");
                    }
                    break;
                case 3:
                    System.out.println("Generating Random Users...");
                    Employee randomEmployee = new Employee("Random Name", ManagerType.ASSISTANT_MANAGER, Department.HR);
                    employees.add(randomEmployee);
                    System.out.println("Added: " + randomEmployee);
                    break;
                case 4:
                    System.out.print("Enter Employee Name: ");
                    String empName = scanner.nextLine();
                    System.out.print("Enter Manager Type (1-HEAD_MANAGER, 2-ASSISTANT_MANAGER, 3-TEAM_LEAD): ");
                    int mgrChoice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Department (1-CUSTOMER_SERVICE, 2-TECHNICAL_SUPPORT, 3-HR): ");
                    int deptChoice = scanner.nextInt();
                    scanner.nextLine();
                    employees.add(new Employee(empName, ManagerType.values()[mgrChoice - 1], Department.values()[deptChoice - 1]));
                    System.out.println("Employee Added Successfully!");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid Option!");
        }
     }
    }
     }
}





