//File Name EmployeeManager.java
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java EmployeeManager <option>");
            System.out.println("Options:");
            System.out.println("  line - List all employees");
            System.out.println("  s - Show a random employee");
            System.out.println("  +<name> - Add a new employee");
            System.out.println("  ?<name> - Search for an employee");
            System.out.println("  c - Count the number of words in the file");
            System.out.println("  u<name> - Update an employee's name to 'Updated'");
            System.out.println("  d<name> - Delete an employee");
            return;
        }

        try {
            if (args[0].equals("l")) {
                System.out.println("Loading data ...");
                for (String employee : readEmployeesFromFile()) {
                    System.out.println(employee);
                }
                System.out.println("Data Loaded.");
            } else if (args[0].equals("s")) {
                System.out.println("Loading data ...");
                String[] employees = readEmployeesFromFile();
                System.out.println(employees[new Random().nextInt(employees.length)]);
                System.out.println("Data Loaded.");
            } else if (args[0].contains("+")) {
                System.out.println("Loading data ...");
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                bufferedWriter.write(", " + args[0].substring(1));
                bufferedWriter.close();
                System.out.println("Data Loaded.");
            } else if (args[0].contains("?")) {
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
            } else if (args[0].contains("c")) {
                System.out.println("Loading data ...");
                long wordCount = Arrays.stream(readEmployeesFromFile())
                        .flatMap(employee -> Stream.of(employee.split(" ")))
                        .count();
                System.out.println(wordCount + " word(s) found");
                System.out.println("Data Loaded.");
            } else if (args[0].contains("u")) {
                System.out.println("Loading data ...");
                String[] employees = readEmployeesFromFile();
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(args[0].substring(1))) {
                        employees[i] = "Updated";
                    }
                }
                writeEmployeesToFile(employees);
                System.out.println("Data Updated.");
            } else if (args[0].contains("d")) {
                System.out.println("Loading data ...");
                List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeesFromFile()));
                employeeList.remove(args[0].substring(1));
                writeEmployeesToFile(employeeList.toArray(new String[0]));
                System.out.println("Data Deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] readEmployeesFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("employees.txt")));
        String line = bufferedReader.readLine();
        bufferedReader.close();
        return line.split(",");
    }

    private static void writeEmployeesToFile(String[] employees) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("employees.txt"));
        bufferedWriter.write(String.join(",", employees));
        bufferedWriter.close();
    }
}
