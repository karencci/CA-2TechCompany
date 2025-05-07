package ca_2techcompany;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */
public class Employee  {
   private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private ManagerType managerType;
    private Department department;

    /**
     * Constructor for Employee class.
     */
    public Employee(int id, String firstName, String lastName, String email, String gender, ManagerType managerType, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.managerType = managerType;
        this.department = department;
    }

    /**
     * Converts employee data to CSV format.
     */
    public String toCSV() {
        return id + "," + firstName + "," + lastName + "," + email + "," + gender + "," + managerType + "," + department;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + firstName + " " + lastName + ", Email: " + email + ", Gender: " + gender +
               ", Manager: " + managerType + ", Department: " + department;
    }
}
    
    

