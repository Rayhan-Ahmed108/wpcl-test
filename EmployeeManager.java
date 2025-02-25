import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments provided. Please provide a valid argument.");
            return;
        }

        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                Random rand = new Random();
                int index = rand.nextInt(employees.length);
                System.out.println(employees[index]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                String newEmployee = args[0].substring(1);
                appendEmployeeToFile(newEmployee);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                boolean found = false;
                String searchEmployee = args[0].substring(1);
                for (String employee : employees) {
                    if (employee.equals(searchEmployee)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                int count = employees.length;
                System.out.println(count + " word(s) found");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                String updateEmployee = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(updateEmployee)) {
                        employees[i] = "Updated";
                    }
                }
                writeEmployeesToFile(employees);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                String deleteEmployee = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(deleteEmployee);
                writeEmployeesToFile(employeeList.toArray(new String[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Deleted.");
        }
    }

    private static String[] readEmployeesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }

    private static void writeEmployeesToFile(String[] employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
        writer.write(String.join(",", employees));
        writer.close();
    }

    private static void appendEmployeeToFile(String employee) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true));
        writer.write(", " + employee);
        writer.close();
    }
}
