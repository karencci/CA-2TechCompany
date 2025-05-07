/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2techcompany;

/**
 *
 * @author Administrator
 */
public class User {
    int id;
    String firstName;
    String lastName;
    String email;
    String gender;

    /**
     * Constructor for User.
     */
    public User(int id, String firstName, String lastName, String email, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }


    /**
     * Converts the user data to CSV format.
     */
    public String toCSV() {
        return id + "," + firstName + "," + lastName + "," + email + "," + gender;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + firstName + " " + lastName + ", Email: " + email + ", Gender: " + gender;
    }
}

    


