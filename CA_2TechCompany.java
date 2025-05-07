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

    // Random data arrays
    private static final String[] firstNames = {"Abby", "Ada", "Aaron", "Ava", "Alex", "Alice", "John", "Jane", "Michael", "Anna"};
    private static final String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Wilson", "Martinez"};
    private static final String[] domains = {"gmail.com", "yahoo.com", "outlook.com", "hotmail.com"};
    private static Random random = new Random();

    /**
     * Main method to start the program.
     */
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
                        generateRandomUsers();
                        break;

                    case SEARCH_EMPLOYEE:
                        searchEmployee();
                        break;

                    case SORT_DUMMY_LIST:
                        sortDummyList();
                        break;

                    case EXIT:
                        running = false;
                        System.out.println("Exiting program...");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Invalid option. Please enter a number between 1 and " + MenuOptions.values().length);
            }
        }
    }

    /**
     * Displays the menu options.
     */
    private static void displayMenu() {
        System.out.println("\nMenu Options:");
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println((option.ordinal() + 1) + ". " + option);
        }
        System.out.print("Choose an option: ");
    }

    /**
     * Gets user choice with input validation.
     */
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
                    System.out.println("Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        return choice;
    }

    /**
     * Generates random users and saves them to a CSV file.
     */
    private static void generateRandomUsers() {
        System.out.print("Enter the number of random users to generate: ");
        int numberOfUsers = getUserChoice();

        users = generateUserList(numberOfUsers);
        writeUsersToCSV(users, "random_users.csv");

        System.out.println("\nGenerated Random Users:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * Generates a list of random users.
     */
    private static List<User> generateUserList(int numberOfUsers) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < numberOfUsers; i++) {
            userList.add(generateRandomUser());
        }
        return userList;
    }

    /**
     * Generates a single random user.
     */
    private static User generateRandomUser() {
        int id = 100 + random.nextInt(900);
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String email = (firstName.charAt(0) + lastName + random.nextInt(100) + "@" + domains[random.nextInt(domains.length)]).toLowerCase();
        String gender = random.nextBoolean() ? "Male" : "Female";
        return new User(id, firstName, lastName, email, gender);
    }

    /**
     * Writes the generated users to a CSV file.
     */
    private static void writeUsersToCSV(List<User> users, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("ID,First Name,Last Name,Email,Gender\n");
            for (User user : users) {
                writer.write(user.toCSV() + "\n");
            }
            System.out.println("Data successfully written to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    /**
     * Dummy method for adding employee.
     */
    private static void addEmployee() {
     System.out.print("Enter Employee First Name: ");
    String firstName = scanner.nextLine().trim();

    while (!firstName.matches("^[A-Za-z]+$")) {
        System.out.println("Invalid name. Please enter letters only.");
        System.out.print("Enter Employee First Name: ");
        firstName = scanner.nextLine().trim();
    }

    System.out.print("Enter Employee Last Name: ");
    String lastName = scanner.nextLine().trim();

    while (!lastName.matches("^[A-Za-z]+$")) {
        System.out.println("Invalid name. Please enter letters only.");
        System.out.print("Enter Employee Last Name: ");
        lastName = scanner.nextLine().trim();
    }

    System.out.print("Enter Employee Email: ");
    String email = scanner.nextLine().trim();

    while (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")) {
        System.out.println("Invalid email format. Try again.");
        System.out.print("Enter Employee Email: ");
        email = scanner.nextLine().trim();
    }

    String gender;
    while (true) {
        System.out.print("Enter Gender (Male/Female): ");
        gender = scanner.nextLine().trim();
        if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
            break;
        }
        System.out.println("Invalid gender. Please enter 'Male' or 'Female'.");
    }

    int id = 100 + random.nextInt(900);
    User newUser = new User(id, firstName, lastName, email, gender);
    users.add(newUser);

    System.out.println("Employee added successfully:\n" + newUser);
   }

    /**
     * Dummy method for sorting dummy list.
     */
    private static void sortDummyList() {
        System.out.println("Sort Dummy List functionality is under development.");
    }

    /**
     * Dummy method for searching employees.
     */
    private static void searchEmployee() {
       System.out.print("Enter Employee Name to Search: ");
    String searchName = scanner.nextLine().trim();

    while (!searchName.matches("^[A-Za-z]+$")) {
        System.out.println("Invalid input. Please enter letters only.");
        System.out.print("Enter Employee Name to Search: ");
        searchName = scanner.nextLine().trim();
    }

    boolean found = false;

    System.out.println("\nSearch Results:");
    for (User user : users) {
        if (user.firstName.equalsIgnoreCase(searchName) || user.lastName.equalsIgnoreCase(searchName)) {
            System.out.println(user);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No employee found with the name: " + searchName); 
    }
}

/**
 * User class representing a random user.
 */
class User {
    int id;
    String firstName;
    String lastName;
    String email;
    String gender;

    public User(int id, String firstName, String lastName, String email, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public String toCSV() {
        return id + "," + firstName + "," + lastName + "," + email + "," + gender;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + firstName + " " + lastName + ", Email: " + email + ", Gender: " + gender;
    }
}

    
       
      





            
    
    

