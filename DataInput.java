/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2techcompany;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


 class DataInput {
    public static List<Employee> readFromFile(String filename) {
        List<Employee> employees = new ArrayList<>();
        
        
        if (filename == null || filename.isEmpty()) {
            System.out.println("Error: Filename is null or empty.");
            return employees;
        }

        File file = new File(filename);

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("Error: File not found - " + filename);
            return employees;
        }
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                
                if (data.length == 3) {
                    String name = data[0].trim();
                    ManagerType managerType = ManagerType.valueOf(data[1].trim().toUpperCase());
                    Department department = Department.valueOf(data[2].trim().toUpperCase());
                    employees.add(new Employee(name, managerType, department));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + filename);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return employees;
    
    }
 }




    


