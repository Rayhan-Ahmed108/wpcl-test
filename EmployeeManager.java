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
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee);
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                System.out.println(line);
                String[] employees = line.split(",");
                Random rand = new Random();
                int index = rand.nextInt(employees.length);
                System.out.println(employees[index]);
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true));
                String newEmployee = args[0].substring(1);
                writer.write(", " + newEmployee);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                boolean found = false;
                String searchEmployee = args[0].substring(1);
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchEmployee)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                char[] chars = line.toCharArray();
                boolean inWord = false;
                int count = 0;
                for (char c : chars) {
                    if (c == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(count + " word(s) found " + chars.length);
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String updateEmployee = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(updateEmployee)) {
                        employees[i] = "Updated";
                    }
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
                writer.write(String.join(",", employees));
                writer.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String deleteEmployee = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(deleteEmployee);
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeList));
                writer.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Deleted.");
        }
    }
}