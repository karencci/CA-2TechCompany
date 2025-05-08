/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca_2techcompany;
//agregar una funcion al maincode (filereader ) 


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    private static final Scanner SC = new Scanner(System.in);
    private static final List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        //Load and sort names
        List<String> names = loadNames();
        names = mergeSort(names);
        names = names.subList(0, Math.min(20, names.size()));
        for (String n : names) {
            employees.add(new Employee(n, ManagerType.ASSISTANT_MANAGER, Department.CUSTOMER_SERVICE));
        }
        //Menu loop
        while (true) {
            MenuOption opt = promptMenu();
            switch (opt) {
                case SORT:
                    sortAndDisplay();
                    break;
                case SEARCH:
                    searchByName();
                    break;
                case ADD:
                    addEmployee();
                    break;
                case GENERATE_RANDOM:
                    generateRandomEmployees();
                    break;
                case EXIT:
                    System.out.println("Goodbye!");
                    return;
                    default:
        // never happens, but good practice
        throw new IllegalStateException("Unexpected value: " + opt);
            }
        }
    }

    private static List<String> loadNames() {
        List<String> list = new ArrayList<>();
        while (true) {
            System.out.print("Please enter the filename to read: ");
            String input = SC.nextLine();
            // Strip stray quotes
            String file = input.replace("\"", "").trim();

            try {
                Path path = Paths.get(file);
                for (String line : Files.readAllLines(path)) {
                    if (!line.isBlank()) {
                        list.add(line.trim());
                    }
                }
                System.out.println("File read successfully!");
                return list;

            } catch (InvalidPathException ipe) {
                System.out.println("Invalid filename – please avoid quotes or illegal characters.");
            } catch (IOException ioe) {
                System.out.println("Could not read that file. Make sure it exists and is readable.");
            }
        }
    }

    private static MenuOption promptMenu() {
        while (true) {
            System.out.println("\nSelect an option:");
            for (MenuOption m : MenuOption.values()) {
                System.out.printf(" %d. %s%n", m.getCode(), m.name());
            }
            try {
                int choice = Integer.parseInt(SC.nextLine());
                MenuOption opt = MenuOption.fromInt(choice);
                if (opt != null) {
                    return opt;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void sortAndDisplay() {
        System.out.println("\n-- SORTED EMPLOYEES --");
        employees.sort(Comparator.comparing(Employee::getName));
        employees.stream()
                .limit(20)
                .forEach(e -> System.out.println(e.getName()));
    }

    private static void searchByName() {
        System.out.print("Enter name to search: ");
        String key = SC.nextLine();
        if (key.isEmpty()) {
            System.out.println("Search term cannot be empty.");
            return;
        }
        String keyLower = key.toLowerCase();

        if (employees.isEmpty()) {
            System.out.println("No employees loaded to search.");
            return;
        }
        employees.sort(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER));

        int idx = binarySearchPrefix(employees, keyLower);
        if (idx < 0) {
            System.out.printf("No matching record found for \"%s\".%n", key);
            return;
        }

        // Walk backwards to the first matching prefix
        int first = idx;
        while (first > 0
                && employees.get(first - 1).getName().toLowerCase().startsWith(keyLower)) {
            first--;
        }

        // From there, print every continuous prefix match
        for (int i = first; i < employees.size(); i++) {
            String nameLower = employees.get(i).getName().toLowerCase();
            if (!nameLower.startsWith(keyLower)) break;
            System.out.println(employees.get(i).toString());
        }
    }

    private static int binarySearchPrefix(List<Employee> employees, String keyLower) {
        return 0;
    }


    private static void addEmployee() {
        System.out.print("Enter new employee name: ");
        String name = SC.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be blank.");
            return;
        }
        ManagerType mt = promptEnum("Manager Type", ManagerType.values());
        Department dp = promptEnum("Department", Department.values());
        employees.add(new Employee(name, mt, dp));
        System.out.printf("\"%s\" added as \"%s\" to \"%s\".%n", name, mt, dp);
    }

    private static <T extends Enum<T>> T promptEnum(String label, T[] vals) {
        while (true) {
            System.out.printf("Select %s:%n", label);
            for (int i = 0; i < vals.length; i++) {
                System.out.printf(" %d. %s%n", i + 1, vals[i].name());
            }
            try {
                int c = Integer.parseInt(SC.nextLine());
                if (c >= 1 && c <= vals.length) {
                    return vals[c - 1];
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid. Try again.");
        }
    }

    private static void generateRandomEmployees() {
        System.out.print("How many to generate? ");
        int n;
        try {
            n = Integer.parseInt(SC.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("That's not a number.");
            return;
        }
        String[] sampleNames = {
                "Alice", "Bob", "Charlie", "Diana",
                "Eve", "Frank", "Grace", "Hank"
        };
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            String name = sampleNames[rnd.nextInt(sampleNames.length)]
                    + "-" + (rnd.nextInt(900) + 100);
            ManagerType mt = ManagerType.values()[rnd.nextInt(ManagerType.values().length)];
            Department dp = Department.values()[rnd.nextInt(Department.values().length)];
            employees.add(new Employee(name, mt, dp));
        }
        System.out.printf("%d random employees added.%n", n);
    }

    public static List<String> mergeSort(List<String> list) {
        if (list.size() <= 1) return list;
        int mid = list.size() / 2;
        List<String> left = mergeSort(list.subList(0, mid));
        List<String> right = mergeSort(list.subList(mid, list.size()));
        return merge(left, right);
    }

    private static List<String> merge(List<String> a, List<String> b) {
        List<String> out = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i).compareToIgnoreCase(b.get(j)) <= 0) {
                out.add(a.get(i++));
            } else {
                out.add(b.get(j++));
            }
        }
        while (i < a.size()) out.add(a.get(i++));
        while (j < b.size()) out.add(b.get(j++));
        return out;
    }

    public static int binarySearch(List<String> arr, String key) {
        int lo = 0, hi = arr.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = arr.get(mid).compareToIgnoreCase(key);
            if (cmp == 0) return mid;
            else if (cmp < 0) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
        }
}





