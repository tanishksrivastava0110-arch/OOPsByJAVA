
public class Order {

    private int orderId;
    private String customerName;
    private String productName;
    private int quantity;
    private double unitPrice;
    private String status;

    public Order(int orderId, String customerName,
            String productName, int quantity,
            double unitPrice, String status) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getStatus() {
        return status;
    }

    public void display() {
        System.out.println(
                orderId + " | " + customerName + " | " +
                        productName + " | " + quantity + " | " +
                        unitPrice + " | " + status);
    }
}
