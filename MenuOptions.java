/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ca_2techcompany;

/**
 *
 * @author Administrator
 */
public enum MenuOptions {
    SORT,
    SEARCH,
    RANDOM_USERS,
    ADD_RECORDS,
    EXIT;

    public static void displayMenu() {
        System.out.println("1. SORT");
        System.out.println("2. SEARCH");
        System.out.println("3. RANDOM USERS");
        System.out.println("4. ADD RECORDS");
        System.out.println("5. EXIT");
        
}
}
