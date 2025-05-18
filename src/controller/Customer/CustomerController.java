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
    private ProductRepo productRepo;
    private CustomerRepo customerRepo = new CustomerRepo();
    private CustomerView view;
    private NotificationRepo notificationRepo;
    private boolean paymentMade = false;

    public CustomerController(CustomerView view, ProductRepo productRepo, NotificationRepo notificationRepo) {
        this.view = view;
        this.productRepo = productRepo;
        this.notificationRepo = notificationRepo;

        refreshDashboard();
    }

    private void refreshDashboard() {
        view.displayArea.setText("✅ Welcome to Your Dashboard!\n\n");
        view.displayArea.append("🛍️ Available Products:\n");
        List<ProductModel> products = productRepo.getAllProducts();

        if (products.isEmpty()) {
            view.displayArea.append("No products available.\n");
        } else {
            for (ProductModel p : products) {
                view.displayArea.append("- " + p.getName() + " | $" + p.getPrice() + "\n");
            }
        }

        view.displayArea.append("\n📢 Notifications:\n");
        List<NotificationModel> notifications = notificationRepo.getAllNotifications();

        if (!notifications.isEmpty()) {
            for (NotificationModel n : notifications) {
                view.displayArea.append("From " + n.getSender() + ": " + n.getMessage() + "\n");
            }
        } else {
            view.displayArea.append("No notifications at this time.\n");
        }
    }

    public void browseProducts() {
        view.displayArea.setText("🛍️ Browsing All Products:\n");
        for (ProductModel p : productRepo.getAllProducts()) {
            view.displayArea.append("- " + p.getName() + " | $" + p.getPrice() + "\n");
        }
    }

    public void searchProduct(String keyword) {
        view.displayArea.setText("🔍 Search Results:\n");
        boolean found = false;
        for (ProductModel p : productRepo.getAllProducts()) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                view.displayArea.append("- " + p.getName() + " | $" + p.getPrice() + "\n");
                found = true;
            }
        }
        if (!found) {
            view.displayArea.append("No matching product found.\n");
        }
    }

    public void addToCart(String productName) {
        boolean found = false;
        for (ProductModel p : productRepo.getAllProducts()) {
            if (p.getName().equalsIgnoreCase(productName)) {
                cart.addProduct(productName);
                notificationRepo.addNotification(new NotificationModel("System", productName + " added to your cart."));
                view.displayArea.append("🛒 Added to cart: " + productName + "\n");
                found = true;
                break;
            }
        }

        if (!found) {
            view.displayArea.append("⚠️ Product not found.\n");
        } else {
            view.displayArea.append("\n🧺 Your Current Cart:\n");
            viewCart();
        }
    }

    public void viewCart() {
        if (paymentMade) {
            view.displayArea.append("✅ Payment already made. Cart is locked.\n");
            return;
        }

        List<String> cartItems = cart.getProducts();
        if (cartItems.isEmpty()) {
            view.displayArea.append("🛒 Your cart is empty.\n");
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
            view.displayArea.setText("⚠️ Your cart is empty. Add products before payment.\n");
            paymentMade = false;
            return;
        }

        paymentMade = true;
        view.displayArea.setText("💳 Payment successful for customer: " + customerId + "\n");
    }

    public void checkout(String customerId) {
        if (!paymentMade) {
            view.displayArea.setText("⚠️ Please complete payment before checking out.\n");
            return;
        }

        List<String> cartItems = cart.getProducts();
        if (cartItems.isEmpty()) {
            view.displayArea.append("⚠️ Cart is empty. Cannot proceed to checkout.\n");
            return;
        }

        view.displayArea.setText("📦 Checkout Complete:\n");
        int count = 1;
        for (String product : cartItems) {
            String orderId = "ORD" + System.currentTimeMillis() + count++;
            OrderModel order = new OrderModel(orderId, customerId, product, 1, "Pending");
            orderList.add(order);
            view.displayArea.append("🛍️ " + product + " | Order ID: " + orderId + " | Status: Pending\n");
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
            view.displayArea.append("✅ These are your current orders.\n");
        }
    }
}
