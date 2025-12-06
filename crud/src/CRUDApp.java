import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.exit;

public class CRUDApp {

    // Mock Employee class
    static class Employee {
        int id;
        String name;
        String department;
        String location;

        Employee(int id, String name, String department, String location) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.location = location;
        }

        @Override
        public String toString() {
            return "Emp ID: " + id + "  Name: " + name + "  Dept: " + department + "  Location: " + location;
        }
    }

    // Mock database (ArrayList)
    static List<Employee> employees = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    // -------------------------------------------
    // CREATE DATA
    // -------------------------------------------
    public static void createData() {
        try {
            System.out.println("\t\t\t*Please Remember 'Your ID' number for further Updatation.....");
            System.out.print("\t\t\tEnter employee ID number : ");
            int id = scanner.nextInt();
            
            // Check if ID already exists
            for (Employee emp : employees) {
                if (emp.id == id) {
                    System.out.println("\t\t\tEmployee with this ID already exists!");
                    menu();
                    return;
                }
            }
            
            System.out.print("\t\t\tEnter employee name : ");
            String name = scanner.next();
            System.out.print("\t\t\tEnter employee department : ");
            String department = scanner.next();
            System.out.print("\t\t\tEnter employee Location : ");
            String location = scanner.next();

            employees.add(new Employee(id, name, department, location));
            System.out.println("\t\t\tRow inserted successfully!");
            menu();
        } catch (Exception e) {
            System.out.println("\t\t\tError: " + e.getMessage());
            menu();
        }
    }

    // -------------------------------------------
    // READ DATA
    // -------------------------------------------
    public static void readData() {
        try {
            if (employees.isEmpty()) {
                System.out.println("\t\t\tNo employees found!");
            } else {
                System.out.println("\t\t\t--- Employee Records ---");
                for (Employee emp : employees) {
                    System.out.println("\t\t\t" + emp);
                }
            }
            menu();
        } catch (Exception e) {
            System.out.println("\t\t\tError: " + e.getMessage());
            menu();
        }
    }

    // -------------------------------------------
    // DELETE DATA
    // -------------------------------------------
    public static void deleteData() {
        try {
            System.out.print("\t\t\tEnter Employee ID number : ");
            int id = scanner.nextInt();

            boolean found = false;
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).id == id) {
                    employees.remove(i);
                    System.out.println("\t\t\tRow deleted!");
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.out.println("\t\t\tIncorrect ID. Try again.");
            }

            menu();
        } catch (Exception e) {
            System.out.println("\t\t\tError: " + e.getMessage());
            menu();
        }
    }

    // -------------------------------------------
    // UPDATE DATA
    // -------------------------------------------
    public static void updateData() {
        try {
            System.out.println("\t\t\t1 - Update ID");
            System.out.println("\t\t\t2 - Update Name");
            System.out.println("\t\t\t3 - Update Location");
            System.out.println("\t\t\t4 - Update Department");
            System.out.print("\t\t Choose Option -->> ");
            int opt = scanner.nextInt();

            boolean found = false;
            
            if (opt == 1) {
                System.out.print("\n\t\t\tEnter Name: ");
                String name = scanner.next();
                System.out.print("\t\t\tEnter New ID: ");
                int newId = scanner.nextInt();
                
                // Check if new ID already exists
                for (Employee emp : employees) {
                    if (emp.id == newId) {
                        System.out.println("\t\t\tID already exists!");
                        menu();
                        return;
                    }
                }
                
                for (Employee emp : employees) {
                    if (emp.name.equals(name)) {
                        emp.id = newId;
                        found = true;
                        break;
                    }
                }
            } else if (opt == 2) {
                System.out.print("\n\t\t\tEnter ID: ");
                int id = scanner.nextInt();
                System.out.print("\t\t\tEnter New Name: ");
                String newName = scanner.next();
                
                for (Employee emp : employees) {
                    if (emp.id == id) {
                        emp.name = newName;
                        found = true;
                        break;
                    }
                }
            } else if (opt == 3) {
                System.out.print("\n\t\t\tEnter ID: ");
                int id = scanner.nextInt();
                System.out.print("\t\t\tEnter New Location: ");
                String newLoc = scanner.next();
                
                for (Employee emp : employees) {
                    if (emp.id == id) {
                        emp.location = newLoc;
                        found = true;
                        break;
                    }
                }
            } else if (opt == 4) {
                System.out.print("\n\t\t\tEnter ID: ");
                int id = scanner.nextInt();
                System.out.print("\t\t\tEnter New Department: ");
                String newDept = scanner.next();
                
                for (Employee emp : employees) {
                    if (emp.id == id) {
                        emp.department = newDept;
                        found = true;
                        break;
                    }
                }
            } else {
                System.out.println("\n\t\t\tInvalid option!");
                menu();
                return;
            }

            if (found) System.out.println("\t\t\tUpdated successfully!");
            else System.out.println("\t\t\tUpdate failed! ID or Name not found.");

            menu();
        } catch (Exception e) {
            System.out.println("\t\t\tError: " + e.getMessage());
            menu();
        }
    }

    // -------------------------------------------
    // MENU
    // -------------------------------------------
    public static void menu() {
        System.out.println("\n\n\t\t\t--------- CRUD OPERATION USING JAVA --------\n");
        System.out.println("\t\t\t1 - Create Data");
        System.out.println("\t\t\t2 - Read Data");
        System.out.println("\t\t\t3 - Update Data");
        System.out.println("\t\t\t4 - Delete Data");
        System.out.println("\t\t\t5 - Exit");
        System.out.print("\t\t Select Option -->>> ");

        int select = scanner.nextInt();

        switch (select) {
            case 1 -> createData();
            case 2 -> readData();
            case 3 -> updateData();
            case 4 -> deleteData();
            case 5 -> {
                System.out.println("\t\tExit... Thank You ♥");
                exit(0);
            }
            default -> {
                System.out.println("Wrong Option!");
                menu();
            }
        }
    }

    // -------------------------------------------
    // MAIN
    // -------------------------------------------
    public static void main(String[] args) {
        System.out.println("\n\t\t\t*** CRUD Application (In-Memory Storage) ***\n");
        menu();
    }
}
