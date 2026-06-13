import java.util.*;
import java.io.*;
public class Node {

        Node next;
        String name;
        String department;
        int employee_id;
        String education;
        float experience;
        char grade;
        int salary;
        
        Node(String name,String department,int employee_id,String education,float experience,char grade,int salary){

                this.name=name;
                this.department=department;
                this.employee_id=employee_id;
                this.education=education;
                this.experience=experience;
                this.grade=grade;
                this.salary=salary;
        } 
}

class Employee_Management_System{
    Node head;
    Node tail;

        public static void main(String ar[]){

            Scanner ip=new Scanner(System.in);
            Employee_Management_System emp=new Employee_Management_System();
            emp.alreadyAddedEmployees();
            emp.loadFromFile();   // employees loaded from file


            boolean System_On=true;
            while (System_On) {
                 System.out.println("====EmployeesPRO==== ");

            System.out.println("1.Managers View");
            System.out.println("2.Employees view");
            System.out.println("3.Exit");

            System.out.print("Enter your Status.. ");
            int choice=ip.nextInt();
            ip.nextLine();

            if(choice == 1){
                 System.out.println("----Manager's View----");

            System.out.print("Manager: ");
            String name=ip.nextLine();
            System.out.print("PASSWORD: ");
            String password=ip.nextLine();

            if(password.equalsIgnoreCase("admin12")){
           
            
            boolean managerLogin=true;
            
            while(managerLogin){
            
                        int selected=emp.displayMenu();

                    if(selected==6){
                        System.out.println("Exiting from Manager's View");
                        managerLogin=false;
                    }
                    else
                    emp.functioning(selected);
            }
        }

    }
        else if(choice == 2){

            boolean emp_view=true;

            while(emp_view){
                    System.out.println("----Employees's view----");
            System.out.println("See Your Personal Info");
            
            System.out.print("(0 to Exit)Enter your Employee_ID: ");
            int id=ip.nextInt();
            ip.nextLine();
            if(id==0){
                System.out.println("Exiting the Employee's View");
                emp_view=false;
                break;
            }
            System.out.print("Enter your CODE: ");
            String code=ip.nextLine();

             
            if(id>=101 && id<=115 && code.equalsIgnoreCase("old")){ //previous employees

                    emp.searchById(id); // it will display employees data
                    
            }
            else if(id>=116 && code.equalsIgnoreCase("new")){ // new employees

                    emp.searchById(id); // it will display employees data
            }
            else{

                System.out.println("Invalid ID or CODE Entered..");
            }

        }

        }
        else if(choice == 3){
            emp.saveToFile(); // saving everything taken place to file
            System.out.println("Exiting the System!"); //program end
            System_On=false;
            break;
        }
                
            }
       
        }

        public void addEmployees(String name,String department,int employee_id,String education,float experience,char grade,int salary){

                    Node newNode=new Node(name,department,employee_id,education,experience,grade,salary);

                    if(head==null){
                        head=tail=newNode;
                    }
                    else{
                    tail.next=newNode;
                    tail=newNode;
                    
                }

                System.out.println("Employee Added Successfully");
                System.out.println();
                  
        }

        public void viewEmployees(){

                    Node current=head;

                    while(current!=null){

                        System.out.println("Name: "+current.name);
                        System.out.println("Department: "+current.department);
                        System.out.println("Employee ID: "+current.employee_id);
                        System.out.println("Education: "+current.education);
                        System.out.println("Experience: "+current.experience);
                        System.out.println("Officer Grade: "+current.grade);
                        System.out.println("Salary: "+current.salary);

                        current=current.next;
                        
                        System.out.println();

                    }

        }

        public void TotalEmployees(){

                int count=0;
                Node current=head;

                if(head==null){
                        System.out.println("No Employees are there!");
                        return;

                }

                while(current!=null){
                    
                    count++;
                    current=current.next;
                }

                System.out.println("The Overall Population of Employees Currently is: "+count);
        }

        public int displayMenu(){
            Scanner ip=new Scanner(System.in);

            System.out.println("----Manager's View---- ");

            System.out.println("Press 1 to Display all Employees Info");
            System.out.println("Press 2 to View Total number of Employees");
            System.out.println("Press 3 to Add new Employees");
            System.out.println("Press 4 to Remove Exising Employees");
            System.out.println("Press 5 to Search for Employees");
            System.out.println("Press 6 to Exit");

            System.out.print("Enter your choice...");
            int choice=ip.nextInt();

            return  choice;
        }

            public void remove(){

                Scanner ip=new Scanner(System.in);

                    System.out.println("Enter 1 to Remove by name");
                    System.out.println("Enter 2 to Remove by ID");
                     System.out.print("Enter Your Choice..");
                    int choice=ip.nextInt();

                    if(choice==1){

                        System.out.print("Enter the name...");
                        ip.nextLine();
                        String name=ip.nextLine();
                        removeByName(name);

                    }else if(choice==2){

                        System.out.print("Enter ID...");
                        int id=ip.nextInt();
                        removeByID(id);
                    }else{

                        System.out.println("Wrong choice entered!");
                    }

            }

        public void removeByName(String name){

                if(head==null){
                    System.out.println("No employees are there");
                    return;
                }
                
                boolean valid=false;

                if(head.name.equalsIgnoreCase(name)){
                    head=head.next;
                    valid=true;
                }
                else{

                    Node current=head;
                    while(current.next!=null){
                        
                       if(current.next.name.equalsIgnoreCase(name)){

                            valid=true;
                            current.next=current.next.next;
                            break;    
                       }

                        current=current.next;
                  }
                
                  if(valid)
                  System.out.println("Employee deleted Successfully");
                  else
                  System.out.println("Employee Not Found");

                  System.out.println();
                  
            }
                    
        }

        public void removeByID(int id){

                if(id<=0){
                    System.out.println("Invalid ID! ID doesn't exist");
                }

                if(head==null){
                    System.out.println("No employees are there");
                    return;
                }

                boolean valid=false;

                if(head.employee_id==id){

                    head=head.next;
                    valid=true;
                }else{

                Node current=head;

                while(current.next!=null){

                       if(current.next.employee_id==id){
                            valid=true;
                             current.next=current.next.next;
                            break;
                       }

                        current=current.next;
                }
                
            }

            if(valid){
                System.out.println("Employee deleted Successfully");
                
            }else
                System.out.println("ID not found! Maybe Invalid");

                System.out.println();
        }

        public void search(){
            Scanner ip=new Scanner(System.in);

                System.out.println("Enter 1 to Search by Name");
                System.out.println("Enter 2 to Search by ID");
                System.out.println("Enter 3 to Search by Department");
                System.out.println("Enter 4 to Search by Experience");

                System.out.print("Enter your Choice.."); 
                  int choice=ip.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter Name to Search...");
                            ip.nextLine();
                            String name=ip.nextLine();
                            searchByName(name);
                            break;

                        case 2:
                            System.out.print("Enter ID to Search...");
                            int id=ip.nextInt();
                            searchById(id);
                            break;

                        case 3:
                            System.out.print("Enter Department to Search...");
                            ip.nextLine();
                            String department=ip.nextLine();
                            searchByDepartment(department);
                            break;

                        case 4:
                            System.out.print("Enter Experience to Search...");
                            float experience=ip.nextFloat();
                            searchByExperience(experience);;
                            break;        
                        default:
                            break;
                    }

        }

        public void searchByName(String name){
            System.out.println();

                if(head==null){
                    return;
                }
                
                boolean valid=false;

                Node current=head;

                while(current!=null){

                        if(current.name.equalsIgnoreCase(name)){
                            valid=true;

                        System.out.println("Name: "+current.name);
                        System.out.println("Department: "+current.department);
                        System.out.println("Employee ID: "+current.employee_id);
                        System.out.println("Education: "+current.education);
                        System.out.println("Experience: "+current.experience);
                        System.out.println("Officer Grade: "+current.grade);
                        System.out.println("Salary: "+current.salary);
                        System.out.println();
                            
                        }

                      current=current.next;  

                }

                if(valid){
                    System.out.println("Name found!");
                }else{
                    System.out.println("Name not found!");
                }
                    System.out.println();
                  
        }

        public void searchByDepartment(String department){
            System.out.println();

                if(head==null){
                    return;
                }
                
                boolean valid=false;

                Node current=head;

                while(current!=null){

                        if(current.department.equalsIgnoreCase(department)){
                            valid=true;

                        System.out.println("Name: "+current.name);
                        System.out.println("Department: "+current.department);
                        System.out.println("Employee ID: "+current.employee_id);
                        System.out.println("Education: "+current.education);
                        System.out.println("Experience: "+current.experience);
                        System.out.println("Officer Grade: "+current.grade);
                        System.out.println("Salary: "+current.salary);
                            System.out.println();
                        }
                      current=current.next;  

                }

               if(valid){
                    System.out.println("Name found in department!");
                }else{
                    System.out.println("Either Name or Department not found!");
                }
                System.out.println();
                  
        }

        public void searchByExperience(float experience){
            System.out.println();

                if(experience<0.0){

                    System.out.println("Invalid number entered");
                }
                if(head==null){
                    return;
                }
                
                boolean valid=false;

                Node current=head;

                while(current!=null){

                        if(current.experience==experience){
                            valid=true;

                        System.out.println("Name: "+current.name);
                        System.out.println("Department: "+current.department);
                        System.out.println("Employee ID: "+current.employee_id);
                        System.out.println("Education: "+current.education);
                        System.out.println("Experience: "+current.experience);
                        System.out.println("Officer Grade: "+current.grade);
                        System.out.println("Salary: "+current.salary);
                        System.out.println();
                            
                        }

                      current=current.next;  

                }

                if(valid){
                    System.out.println("Name found!");
                }else{
                    System.out.println("Name not found!");
                }
                System.out.println();
                
        }

            public void searchById(int id){
                System.out.println();

                if(id<=0){

                    System.out.println("Invalid number entered!");
                }
                if(head==null){
                    return;
                }
                
                boolean valid=false;

                Node current=head;

                while(current!=null){

                        if(current.employee_id==id){
                            valid=true;

                        System.out.println("Name: "+current.name);
                        System.out.println("Department: "+current.department);
                        System.out.println("Employee ID: "+current.employee_id);
                        System.out.println("Education: "+current.education);
                        System.out.println("Experience: "+current.experience);
                        System.out.println("Officer Grade: "+current.grade);
                        System.out.println("Salary: "+current.salary);

                        System.out.println();

                        }
                        
                      current=current.next;  

                }

                if(valid){
                    System.out.println("Name with ID found!");
                }else{
                    System.out.println("Name with ID not found!");
                }
                System.out.println();
                  
        }
        
        public void alreadyAddedEmployees(){

            addEmployees("Alice Johnson", "Human Resources", 101, "MBA in HR", 5.0f, 'B', 65000);
            addEmployees("Robert Smith", "Human Resources", 102, "BBA HR", 3.5f, 'C', 52000);
            addEmployees("Sophie Turner", "Human Resources", 103, "MBA in HR", 6.0f, 'A', 78000);

            addEmployees("Liam Brown", "Finance", 104, "B.Com", 7.5f, 'A', 80000);
            addEmployees("Grace Davis", "Finance", 105, "M.Com", 2.0f, 'C', 48000);
            addEmployees("Noah Wilson", "Finance", 106, "CA", 9.0f, 'A', 90000);

            addEmployees("Catherine Lee", "IT", 107, "BS Computer Science", 3.0f, 'C', 55000);
            addEmployees("Frank Miller", "IT", 108, "BS Software Engineering", 6.0f, 'A', 78000);
            addEmployees("Oliver Harris", "IT", 109, "MS Computer Science", 8.0f, 'A', 92000);
            addEmployees("Mia Patel", "IT", 110, "BS Information Technology", 4.0f, 'B', 64000);

            addEmployees("David Wilson", "Marketing", 111, "MBA Marketing", 4.5f, 'B', 60000);
            addEmployees("Jack Anderson", "Marketing", 112, "BBA Marketing", 4.0f, 'B', 58000);
            addEmployees("Nora Collins", "Marketing", 113, "MBA Marketing", 6.0f, 'A', 72000);

            addEmployees("Emma Brown", "Research & Development", 114, "MS Physics", 10.0f, 'A', 95000);
            addEmployees("Lucas Adams", "Research & Development", 115, "PhD Chemistry", 12.0f, 'A', 105000);
            addEmployees("Ava Martinez", "Research & Development", 116, "MS Biotechnology", 8.0f, 'B', 87000);

            addEmployees("Henry Clark", "Administration", 117, "BBA", 8.0f, 'A', 85000);
            addEmployees("Ella Scott", "Administration", 118, "MBA Management", 5.5f, 'B', 70000);

            addEmployees("Isabella Garcia", "Customer Support", 119, "BA English", 1.5f, 'D', 40000);
            addEmployees("Benjamin Young", "Customer Support", 120, "BA Communications", 2.5f, 'C', 45000);
            addEmployees("Harper Lewis", "Customer Support", 121, "BA Psychology", 3.0f, 'B', 50000);

            addEmployees("Ethan Wright", "Sales", 122, "BBA Marketing", 5.0f, 'B', 62000);
            addEmployees("Amelia Green", "Sales", 123, "B.Com Marketing", 3.0f, 'C', 52000);
            addEmployees("Daniel Thompson", "Sales", 124, "MBA Sales", 7.0f, 'A', 83000);

        }

        void employeeDataGathering(){
            Scanner ip=new Scanner(System.in);

            System.out.println("ENTER THE NEW EMPLOYEE'S INFORMATION!!");


            System.out.print("Enter Employee's Name: ");
            String name=ip.nextLine();

            System.out.print("Enter Employee's Department: ");
            String department=ip.nextLine();

            System.out.print("Enter Employee's Employee ID: ");
            int id=ip.nextInt();

            ip.nextLine();

            System.out.print("Enter Employee's Education: ");
            String education=ip.nextLine();

            System.out.print("Enter Employee's Experience: ");
            float experience = ip.nextFloat();

            System.out.print("Enter Employee's Grade: ");
            char grade=ip.next().charAt(0); 

            System.out.print("Enter Employee's Salary: ");
            int salary=ip.nextInt();

            addEmployees(name, department, id, education, experience, grade, salary);

        }

        public void functioning(int choice){

               Scanner ip=new Scanner(System.in);

                switch (choice) {
                    case 1:
                        viewEmployees();
                        break;
                    case 2:
                        TotalEmployees();
                         break;
                    case 3:
                            employeeDataGathering();
                            break;
                    case 4:
                        remove();
                        break;
                    case 5:
                        search();
                        break;
                    case 6:
                        System.out.println("Manager View EXITS!");
                        return;   
                    default:
                          System.out.println("Invalid Choice! Try Again!");
                          choice=ip.nextInt();
                        break;
                }

        }

                public void saveToFile() { //file writing
            try {
               FileWriter fw = new FileWriter("D:/BS CS AI/3rd Semester/DSA/DSA Theory Project/employees.txt");
                Node current = head;

                while (current != null) {
                    fw.write(
                        current.name + "," +
                        current.department + "," +
                        current.employee_id + "," +
                        current.education + "," +
                        current.experience + "," +
                        current.grade + "," +
                        current.salary + "\n"
                    );
                    current = current.next;
                }
                fw.close();
                System.out.println("Employees saved to file.");
            } catch (Exception e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
            }


                    public void loadFromFile() { // file reading
            try {
                File file = new File("D:/BS CS AI/3rd Semester/DSA/DSA Theory Project/employees.txt");
                if (!file.exists()) return; // No file found → skip

                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");

                    String name = data[0];
                    String department = data[1];
                    int id = Integer.parseInt(data[2]);
                    String education = data[3];
                    float experience = Float.parseFloat(data[4]);
                    char grade = data[5].charAt(0);
                    int salary = Integer.parseInt(data[6]);

                    addEmployees(name, department, id, education, experience, grade, salary);
                }

                br.close();
                System.out.println("Employees loaded from file.");
            } catch (Exception e) {
                System.out.println("Error loading file: " + e.getMessage());
            }
        }


}