// File Name EmployeeManager.java
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length != 1) {
            printUsage();
            return;
        }

        try {
            switch (args[0].charAt(0)) {
                case 'l':
                    if (args[0].equals("l")) {
                        System.out.println("Loading data ...");
                        for (String employee : readEmployeesFromFile()) {
                            System.out.println(employee);
                        }
                        System.out.println("Data Loaded.");
                    } else {
                        printInvalidOption();
                    }
                    break;
                case 's':
                    if (args[0].equals("s")) {
                        System.out.println("Loading data ...");
                        String[] employees = readEmployeesFromFile();
                        System.out.println(employees[new Random().nextInt(employees.length)]);
                        System.out.println("Data Loaded.");
                    } else {
                        printInvalidOption();
                    }
                    break;
                case '+':
                    System.out.println("Loading data ...");
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("employees.txt", true));
                    writer.write(", " + args[0].substring(1));
                    writer.close();
                    System.out.println("Data Loaded.");
                    break;
                case '?':
                    System.out.println("Loading data ...");
                    String[] employees = readEmployeesFromFile();
                    String searchName = args[0].substring(1);
                    boolean found = Arrays.stream(employees).anyMatch(employee -> employee.contains(searchName));
                    if (found) {
                        System.out.println("Employee found!");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    System.out.println("Data Loaded.");
                    break;
                case 'c':
                    if (args[0].equals("c")) {
                        System.out.println("Loading data ...");
                        long wordCount = Arrays.stream(readEmployeesFromFile())
                                .flatMap(employee -> Stream.of(employee.split(" ")))
                                .count();
                        System.out.println(wordCount + " word(s) found");
                        System.out.println("Data Loaded.");
                    } else {
                        printInvalidOption();
                    }
                    break;
                case 'u':
                    System.out.println("Loading data ...");
                    String[] employeesToUpdate = readEmployeesFromFile();
                    for (int i = 0; i < employeesToUpdate.length; i++) {
                        if (employeesToUpdate[i].equals(args[0].substring(1))) {
                            employeesToUpdate[i] = "Updated";
                        }
                    }
                    writeEmployeesToFile(employeesToUpdate);
                    System.out.println("Data Updated.");
                    break;
                case 'd':
                    System.out.println("Loading data ...");
                    List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeesFromFile()));
                    employeeList.remove(args[0].substring(1));
                    writeEmployeesToFile(employeeList.toArray(new String[0]));
                    System.out.println("Data Deleted.");
                    break;
                default:
                    printInvalidOption();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Prints usage instructions
    private static void printUsage() {
        System.out.println("Usage: java EmployeeManager <option>");
        System.out.println("Options:");
        System.out.println("  l - List all employees");
        System.out.println("  s - Show a random employee");
        System.out.println("  +<name> - Add a new employee");
        System.out.println("  ?<name> - Search for an employee");
        System.out.println("  c - Count the number of words in the file");
        System.out.println("  u<name> - Update an employee's name to 'Updated'");
        System.out.println("  d<name> - Delete an employee");
    }

    // Prints invalid option message
    private static void printInvalidOption() {
        System.out.println("Invalid option. Please use one of the following options:");
        printUsage();
    }

    // Reads employees from the file and returns them as an array of strings
    private static String[] readEmployeesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("employees.txt")));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }

    // Writes the given array of employees to the file
    private static void writeEmployeesToFile(String[] employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("employees.txt"));
        writer.write(String.join(",", employees));
        writer.close();
    }
}
