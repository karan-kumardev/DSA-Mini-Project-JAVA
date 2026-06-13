import java.io.*;
import java.util.*;

public class EmployeeFileHandler {

    // Save employees from linked list to a text file
    public static void saveEmployees(Node head, String path) {
        try (FileWriter fw = new FileWriter(path)) {
            Node current = head;
            while (current != null) {
                fw.write(current.employee_id + "," +
                         current.name + "," +
                         current.department + "," +
                         current.education + "," +
                         current.experience + "," +
                         current.grade + "," +
                         current.salary + "\n");
                current = current.next;
            }
            fw.flush();
            System.out.println("Employees saved to file successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load employees from a text file into linked list
    public static void loadEmployees(Employee_Management_System emp, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    emp.addEmployees(
                        data[1],                       // name
                        data[2],                       // department
                        Integer.parseInt(data[0]),     // id
                        data[3],                       // education
                        Float.parseFloat(data[4]),     // experience
                        data[5].charAt(0),             // grade
                        Integer.parseInt(data[6])      // salary
                    );
                }
            }
            System.out.println("Employees loaded from file successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found. No employees loaded.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
