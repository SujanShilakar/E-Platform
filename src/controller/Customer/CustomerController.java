package controller.Customer;

import model.Customer.Cart;
import model.Customer.OrderModel;
import model.Customer.CustomerRepo;
import model.Notification.NotificationModel;
import model.Notification.NotificationRepo;
import model.Product.ProductModel;
import model.Product.ProductRepo;
import view.Customer.CustomerView;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private Cart cart = new Cart();
    private List<OrderModel> orderList = new ArrayList<>();
    private ProductRepo productRepo = new ProductRepo();
    private CustomerRepo customerRepo = new CustomerRepo();
    private CustomerView view;
    private NotificationRepo notificationRepo = new NotificationRepo();

    private boolean paymentMade = false;

    public CustomerController(CustomerView view) {
        this.view = view;

        view.displayArea.setText("✅ Welcome to Your Dashboard!\n\n");
        view.displayArea.append("🛍️ Available Products:\n");
        for (ProductModel p : productRepo.getAllProducts()) {
            view.displayArea.append("- " + p.getName() + " | $" + p.getPrice() + "\n");
        }

        view.displayArea.append("\n📢 Notifications:\n");
        List<NotificationModel> notifications = new NotificationRepo().getAllNotifications();
        if (!notifications.isEmpty()) {
            for (NotificationModel n : notifications) {
                view.displayArea.append("From " + n.getSender() + ": " + n.getMessage() + "\n");
            }
        } else {
            view.displayArea.append("No notifications at this time.\n");
        }
    }

    public void browseProducts() {
        view.displayArea.setText("");
        view.displayArea.append("Available Products:\n");
        for (ProductModel p : productRepo.getAllProducts()) {
            view.displayArea.append("- " + p.getName() + " | $" + p.getPrice() + "\n");
        }
    }

    public void searchProduct(String keyword) {
        view.displayArea.setText("");
        view.displayArea.append("Search Results:\n");
        boolean found = false;
        for (ProductModel p : productRepo.getAllProducts()) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                view.displayArea.append("- " + p.getName() + " | $" + p.getPrice() + "\n");
                found = true;
            }
        }
        if (!found) {
            view.displayArea.append("No such product found. Sorry!\n");
        }
    }

    public void addToCart(String productName) {
        boolean found = false;
        for (ProductModel p : productRepo.getAllProducts()) {
            if (p.getName().equalsIgnoreCase(productName)) {
                cart.addProduct(productName);
                notificationRepo.addNotification(new NotificationModel("System", productName + " has been added to your cart."));
                view.displayArea.append("Notification: " + productName + " has been added to your cart.\n");
                found = true;
                break;
            }
        }
        if (!found) {
            view.displayArea.append("Product not found.\n");
        } else {
            view.displayArea.append("\nUpdated Cart:\n");
            viewCart();
        }
    }

    public void viewCart() {
        if (paymentMade) {
            view.displayArea.append("Payment has already been made. Cart is now locked.\n");
            return;
        }

        List<String> cartItems = cart.getProducts();
        if (cartItems.isEmpty()) {
            view.displayArea.append("Cart is empty.\n");
        } else {
            for (String item : cartItems) {
                for (ProductModel p : productRepo.getAllProducts()) {
                    if (p.getName().equalsIgnoreCase(item)) {
                        view.displayArea.append("- " + p.getName() + " | $" + p.getPrice() + "\n");
                        break;
                    }
                }
            }
        }
    }

    public void makePayment(String customerId) {
        if (cart.getProducts().isEmpty()) {
            view.displayArea.setText("Cart is empty. Add products to proceed with payment.\n");
            paymentMade = false;
            return;
        }
        view.displayArea.setText("Payment successful for customer: " + customerId + "\n");
        paymentMade = true;
    }

    public void checkout(String customerId) {
        if (!paymentMade) {
            view.displayArea.setText("Please make payment before checkout.\n");
            return;
        }

        view.displayArea.setText("Checkout:\n");
        List<String> cartItems = cart.getProducts();
        if (cartItems.isEmpty()) {
            view.displayArea.append("Cart is empty. Cannot proceed to checkout.\n");
            return;
        }

        int count = 1;
for (String product : cartItems) {
    String orderId = "ORD" + count++;
    OrderModel order = new OrderModel(orderId, customerId, product, 1, "Pending");
    orderList.add(order);
    view.displayArea.append("Product: " + product + " | Order ID: " + orderId + " | Status: Pending\n");
}

view.displayArea.append("\n✅ Thank you for shopping with us!\n");

cart.clearCart();
paymentMade = false;


    }

    public void trackOrders(String customerId) {
    view.displayArea.setText("📦 Order Tracking\n--------------------------\n");
    boolean found = false;

    for (OrderModel order : orderList) {
        if (order.getCustomerId().equals(customerId)) {
            view.displayArea.append("🛍️ Product: " + order.getProductName() + "\n");
            view.displayArea.append("🆔 Order ID: " + order.getOrderId() + "\n");
            view.displayArea.append("📄 Status: " + order.getStatus() + "\n");
            view.displayArea.append("--------------------------\n");
            found = true;
        }
    }

    if (!found) {
        view.displayArea.append("No orders found for your account.\n");
    } else {
        view.displayArea.append("✅ These are your current orders. Thank you!\n");
    }
}

}
