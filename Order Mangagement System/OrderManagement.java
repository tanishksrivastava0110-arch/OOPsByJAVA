import java.sql.*;
import java.util.Scanner;

public class OrderManagement {

    static final String URL = "jdbc:mysql://localhost:3306/ecommerce_db";

    static final String USER = "root";
    static final String PASSWORD = "Tanishk@123";

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in);
                Connection con = DriverManager.getConnection(
                        URL, USER, PASSWORD)) {

            System.out.println("Database Connected Successfully!");

            int choice;

            do {
                System.out.println("\n===== ORDER MANAGEMENT SYSTEM =====");
                System.out.println("1. Insert Three Orders");
                System.out.println("2. Search Orders by Status");
                System.out.println("3. Increase Order Quantity");
                System.out.println("4. Search and Update Order Status");
                System.out.println("5. Total Order Value and Delete");
                System.out.println("0. Exit");

                System.out.print("Enter Choice: ");

                while (!sc.hasNextInt()) {
                    sc.next();
                    System.out.print("Enter a valid number: ");
                }

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        insertOrders(con);
                        break;

                    case 2:
                        searchOrders(con, sc);
                        break;

                    case 3:
                        increaseQuantity(con, sc);
                        break;

                    case 4:
                        updateStatus(con, sc);
                        break;

                    case 5:
                        totalAndDelete(con, sc);
                        break;

                    case 0:
                        System.out.println("Exiting Application...");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }

            } while (choice != 0);

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    // (a) Insert at least three order records.
    // Handle duplicate order IDs.

    static void insertOrders(Connection con) {

        String sql = "INSERT INTO Orders "
                + "(order_id, customer_name, product_name, "
                + "quantity, unit_price, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Order[] data = {

                new Order(101, "Rahul", "Laptop",
                        2, 45000.0, "Pending"),

                new Order(102, "Priya", "Mobile",
                        3, 20000.0, "Shipped"),

                new Order(103, "Amit", "Headphones",
                        5, 1500.0, "Cancelled")
        };

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            for (Order o : data) {

                ps.setInt(1, o.getOrderId());
                ps.setString(2, o.getCustomerName());
                ps.setString(3, o.getProductName());
                ps.setInt(4, o.getQuantity());
                ps.setDouble(5, o.getUnitPrice());
                ps.setString(6, o.getStatus());

                try {

                    ps.executeUpdate();

                    System.out.println(
                            "Order inserted: " + o.getOrderId());

                } catch (SQLException e) {

                    if (e.getErrorCode() == 1062) {

                        System.out.println(
                                "Duplicate Order ID "
                                        + o.getOrderId() + "; skipped.");

                    } else {
                        System.out.println(
                                "Insert failed: " + e.getMessage());
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // (b) Search orders by status and quantity > 2.
    // Sort by unit price in descending order.

    static void searchOrders(Connection con, Scanner sc) {

        System.out.print("Enter Order Status: ");
        String status = sc.nextLine();

        String sql = "SELECT * FROM Orders "
                + "WHERE status = ? AND quantity > 2 "
                + "ORDER BY unit_price DESC";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                System.out.println("\nOrder Details:");

                while (rs.next()) {

                    found = true;
                    printOrder(rs);
                }

                if (!found) {
                    System.out.println("No matching orders found.");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // (c) Increase the existing order quantity.

    static void increaseQuantity(Connection con, Scanner sc) {

        System.out.print("Enter Order ID: ");
        int id = readInt(sc);

        System.out.print("Enter Additional Quantity: ");
        int qty = readInt(sc);

        if (qty <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }

        String sql = "UPDATE Orders "
                + "SET quantity = quantity + ? "
                + "WHERE order_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, qty);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Order Quantity Updated Successfully!");
            } else {
                System.out.println("Order Does Not Exist!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // (d) Retrieve order details and update valid status.

    static void updateStatus(Connection con, Scanner sc) {

        System.out.print("Enter Order ID: ");
        int id = readInt(sc);

        String select = "SELECT * FROM Orders WHERE order_id = ?";

        try (PreparedStatement ps = con.prepareStatement(select)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {

                    System.out.println("Order Does Not Exist!");
                    return;
                }

                System.out.println("\nCurrent Order Details:");
                printOrder(rs);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Enter New Status (Pending/Shipped/Delivered): ");
        String newStatus = sc.nextLine();

        if (!newStatus.equalsIgnoreCase("Pending") &&
                !newStatus.equalsIgnoreCase("Shipped") &&
                !newStatus.equalsIgnoreCase("Delivered")) {

            System.out.println("Invalid Order Status!");
            return;
        }

        String update = "UPDATE Orders SET status = ? "
                + "WHERE order_id = ?";

        try (PreparedStatement ps = con.prepareStatement(update)) {

            ps.setString(1, newStatus);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("\nOrder Status Updated Successfully!");
                System.out.println("Updated Order Details:");

                try (PreparedStatement check = con.prepareStatement(select)) {

                    check.setInt(1, id);

                    try (ResultSet rs = check.executeQuery()) {

                        if (rs.next()) {
                            printOrder(rs);
                        }
                    }
                }

            } else {
                System.out.println("Order Not Updated!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // (e) Calculate total order value and delete
    // only if status is Cancelled.

    static void totalAndDelete(Connection con, Scanner sc) {

        String total = "SELECT COALESCE(SUM(quantity * unit_price), 0) "
                + "AS total_value FROM Orders";

        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(total)) {

            if (rs.next()) {

                System.out.printf(
                        "Total Order Value: %.2f%n",
                        rs.getDouble("total_value"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("\nEnter Order ID to Delete: ");
        int id = readInt(sc);

        String sql = "DELETE FROM Orders "
                + "WHERE order_id = ? AND status = 'Cancelled'";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Order Deleted Successfully!");

            } else {

                System.out.println(
                        "Cannot Delete! Order does not exist "
                                + "or its status is not Cancelled.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Display order details using Order object.

    static void printOrder(ResultSet rs) throws SQLException {

        Order o = new Order(
                rs.getInt("order_id"),
                rs.getString("customer_name"),
                rs.getString("product_name"),
                rs.getInt("quantity"),
                rs.getDouble("unit_price"),
                rs.getString("status"));

        o.display();
    }

    // Read integer safely.

    static int readInt(Scanner sc) {

        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Enter an integer: ");
        }

        int n = sc.nextInt();
        sc.nextLine();

        return n;
    }
}
