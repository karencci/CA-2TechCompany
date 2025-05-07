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
    private String name;
    private ManagerType managerType;
    private Department department;

    public Employee(String name, ManagerType managerType, Department department) {
        this.name = name;
        this.managerType = managerType;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " - " + managerType + " - " + department;
    }

    public int compareTo(Employee other) {
        return this.name.compareTo(other.name);
    }
}
    
    

