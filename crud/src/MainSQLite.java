import java.sql.*;
import java.util.Scanner;
import static java.lang.System.exit;

public class MainSQLite {

    private static final String DB_URL = "jdbc:sqlite:employees.db";

    // -------------------------------------------
    // SINGLE CLEAN CONNECTION METHOD (SQLite)
    // -------------------------------------------
    public static Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(DB_URL);
    }

    // -------------------------------------------
    // INITIALIZE DATABASE
    // -------------------------------------------
    public static void initializeDatabase() {
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS employees (" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT NOT NULL," +
                    "department TEXT NOT NULL," +
                    "location TEXT NOT NULL" +
                    ")";

            stmt.execute(createTableSQL);
            System.out.println("\t\t\t✓ Database initialized successfully!");

        } catch (Exception e) {
            System.out.println("\t\t\t✗ Database initialization error: " + e.getMessage());
        }
    }

    // -------------------------------------------
    // CREATE DATA
    // -------------------------------------------
    public static void createData() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\t\t\t*Please Remember 'Your ID' number for further Updatation.....");
        System.out.print("\t\t\tEnter employee ID number : ");
        int id = sc.nextInt();
        System.out.print("\t\t\tEnter employee name : ");
        String name = sc.next();
        System.out.print("\t\t\tEnter employee department : ");
        String department = sc.next();
        System.out.print("\t\t\tEnter employee Location : ");
        String location = sc.next();

        String query = "INSERT INTO employees (id, name, department, location) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, department);
            stmt.setString(4, location);

            int rows = stmt.executeUpdate();

            if (rows == 1) System.out.println("\t\t\tRow inserted successfully!");
            else System.out.println("\t\t\tSomething is Wrong!");

            menu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------
    // READ DATA
    // -------------------------------------------
    public static void readData() {
        String query = "SELECT * FROM employees";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            boolean found = false;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                String location = rs.getString("location");

                System.out.println("\t\t\tEmp ID: " + id + "  Name: " + name + "  Dept: " + department + "  Location: " + location);
                found = true;
            }

            if (!found) {
                System.out.println("\t\t\tNo employees found!");
            }

            menu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------
    // DELETE DATA
    // -------------------------------------------
    public static void deleteData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\t\t\tEnter Employee ID number : ");
        int id = sc.nextInt();
        String query = "DELETE FROM employees WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows == 1) System.out.println("\t\t\tRow deleted!");
            else {
                System.out.println("\t\t\tIncorrect ID. Try again.");
                deleteData();
            }

            menu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------
    // UPDATE DATA
    // -------------------------------------------
    public static void updateData() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\t\t\t1 - Update ID");
        System.out.println("\t\t\t2 - Update Name");
        System.out.println("\t\t\t3 - Update Location");
        System.out.println("\t\t\t4 - Update Department");
        System.out.print("\t\t Choose Option -->> ");
        int opt = sc.nextInt();

        String query = null;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();

            if (opt == 1) {
                System.out.print("\n\t\t\tEnter Name: ");
                String name = sc.next();
                System.out.print("\t\t\tEnter New ID: ");
                int newId = sc.nextInt();
                query = "UPDATE employees SET id=? WHERE name=?";
                stmt = con.prepareStatement(query);
                stmt.setInt(1, newId);
                stmt.setString(2, name);
            } else if (opt == 2) {
                System.out.print("\n\t\t\tEnter ID: ");
                int id = sc.nextInt();
                System.out.print("\t\t\tEnter New Name: ");
                String newName = sc.next();
                query = "UPDATE employees SET name=? WHERE id=?";
                stmt = con.prepareStatement(query);
                stmt.setString(1, newName);
                stmt.setInt(2, id);
            } else if (opt == 3) {
                System.out.print("\n\t\t\tEnter ID: ");
                int id = sc.nextInt();
                System.out.print("\t\t\tEnter New Location: ");
                String newLoc = sc.next();
                query = "UPDATE employees SET location=? WHERE id=?";
                stmt = con.prepareStatement(query);
                stmt.setString(1, newLoc);
                stmt.setInt(2, id);
            } else if (opt == 4) {
                System.out.print("\n\t\t\tEnter ID: ");
                int id = sc.nextInt();
                System.out.print("\t\t\tEnter New Department: ");
                String newDept = sc.next();
                query = "UPDATE employees SET department=? WHERE id=?";
                stmt = con.prepareStatement(query);
                stmt.setString(1, newDept);
                stmt.setInt(2, id);
            } else {
                System.out.println("\n\t\t\tInvalid option!");
                if (con != null) con.close();
                updateData();
                return;
            }

            int rows = stmt.executeUpdate();

            if (rows == 1) System.out.println("\t\t\tUpdated successfully!");
            else System.out.println("\t\t\tUpdate failed!");

            menu();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        Scanner sc = new Scanner(System.in);
        int select = sc.nextInt();

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
        System.out.println("\n\t\t\t*** SQLite CRUD Application ***\n");
        initializeDatabase();
        menu();
    }
}
