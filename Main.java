
 
//Welcome to Tech company using a load file "Applicants_Form.txt,
//search employeees of you company,  add new employees, and generate random 
//employee data.

package ca_2techcompany;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    //  Getting user input
    private static final Scanner SC = new Scanner(System.in);
    // List to store all the employee objects
    private static final List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        // Function to load names from file and sort them
        List<String> names = loadNames();
        names = mergeSort(names);
        // Limit the list to the first 20 names, or less if the list is smaller
        names = names.subList(0, Math.min(20, names.size()));
        // Create Employee objects and employees list
        for (String n : names) {
            employees.add(new Employee(n, ManagerType.ASSISTANT_MANAGER, Department.CUSTOMER_SERVICE));
           
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
                    System.out.println("Thank you, see you later!");
                    return;
                    default:
                        throw new IllegalStateException("Unexpected value: " + opt);
            }
        }
    }
    }
    // Method to load names from Applicants file 
    private static List<String> loadNames() {
        List<String> list = new ArrayList<>();
        while (true) {
            System.out.print("Hello, please enter the filename to read: ");
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
                System.out.println("Thank you, your file has been read successfully!");
                return list;

            } catch (InvalidPathException ipe) {
                System.out.println("Sorry your file is not correct.");
            } catch (IOException ioe) {
                System.out.println("Sorry, Could not read that file. Please insert a correct file.");
            }
        }
    }
    // Method to display the menu and get the user's choice
    private static MenuOption promptMenu() {
        while (true) {
            System.out.println("\n Please select an option:");
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
            System.out.println("Sorry your choice isn't valid. Please try again.");
            }
    }
    // Method to sort and display up to 20 employees
    private static void sortAndDisplay() {
        System.out.println("\n Ordered team ");
         // Sort the list alphabetically by name
        employees.sort(Comparator.comparing(Employee::getName));
        // Display the first 20 or less employees
        employees.stream().limit(20)
                .forEach(e -> System.out.println(e.toString()));
    }
    // Method to search for employees by name  
    private static void searchByName() {
        System.out.print("Please enter a name to search: ");
        String key = SC.nextLine();
        
         if (key.isEmpty()) {
            System.out.println("Please enter a valid name.");
            return;
        }
        String keyLower = key.toLowerCase();
        
        if (employees.isEmpty()) {
            System.out.println("Sorry, the employee list is empty.");
            return;
        }
    // Sort the list before searching to ensure proper binary search
        employees.sort(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER));
    
   
     // Find the first matching employee
    int idx = binarySearchPrefix(employees, keyLower);

    if (idx <0) {
        System.out.println("Sorry, no matching records found.");
        return;
        }
        int first =idx;
        // Move backwards to find the first occurrence of the prefix
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
        // Method to find the first occurrence of a prefix using binary search
         private static int binarySearchPrefix(List<Employee> employees, String keyLower) {
         return 0;
        }
         // Method to add a new employee
         private static void addEmployee() {
            System.out.print("Please enter new employee name: ");
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
    
    // Method to prompt for enum values Manager Type or Department
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
            System.out.println("Please try again.");
        }
    }
      // Method to generate random employees
    private static void generateRandomEmployees() {
        System.out.print("How many employees would you like to generate? ");
        int n;
        try {
            n = Integer.parseInt(SC.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("That's not a number.");
            return;
        }
        String[] sampleNames = {
                "Alice", "Bob", "Charlie", "Diana",
                "Eve", "Frank", "Grace", "Hank","Isla",
                "Finn"};
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
     // Merge sort implementation to sort names
        public static List<String> mergeSort(List<String> list) {
            if (list.size() <= 1) return list;
            int mid = list.size() / 2;
            List<String> left = mergeSort(list.subList(0, mid));
            List<String> right = mergeSort(list.subList(mid, list.size()));
            return merge(left, right);
    }
        // Merge sort implementation to sort names
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





