package model.Customer;


public class OrderModel {
    private String orderId;
    private String customerId;
    private String productName;
    private int quantity;
    private String status;

    public OrderModel(String orderId, String customerId, String productName, int quantity, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productName = productName;
        this.quantity = quantity;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
