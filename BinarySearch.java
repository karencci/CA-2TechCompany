/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2techcompany;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class BinarySearch {
    public static int search(List<Employee> employees, String name, int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            Employee midEmployee = employees.get(mid);
            if (midEmployee.getName().equalsIgnoreCase(name)) {
                return mid;
            }
            if (midEmployee.getName().compareToIgnoreCase(name) < 0) {
                return search(employees, name, mid + 1, right);
            } else {
                return search(employees, name, left, mid - 1);
            }
        }
        return -1;
}
}