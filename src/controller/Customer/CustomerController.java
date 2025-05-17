package controller.Customer;

import model.Customer.Cart;
import model.Customer.CustomerModel;
import model.Customer.OrderModel;
import model.Customer.CustomerRepo;
import model.Product.ProductModel;
import model.Product.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private Cart cart = new Cart();
    private List<OrderModel> orderList = new ArrayList<>();
    private ProductRepo productRepo = new ProductRepo();
    private CustomerRepo customerRepo = new CustomerRepo();
    private Scanner scanner = new Scanner(System.in);

    public void browseProducts() {
        System.out.println("Available Products:");
        for (ProductModel p : productRepo.getAllProducts()) {
            System.out.println("- " + p.getName() + " | $" + p.getPrice());
        }
    }

    public void searchProduct(String keyword) {
        System.out.println("Search Results:");
        for (ProductModel p : productRepo.getAllProducts()) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("- " + p.getName() + " | $" + p.getPrice());
            }
        }
    }

    public void addToCart(String productName) {
        cart.addProduct(productName);
        System.out.println(productName + " added to cart.");
    }

    public void viewCart() {
        System.out.println("Your Cart:");
        for (String item : cart.getProducts()) {
            System.out.println("- " + item);
        }
    }

    public void checkout(String customerId) {
        System.out.println("Checkout:");
        int count = 1;
        for (String product : cart.getProducts()) {
            String orderId = "ORD" + count++;
            OrderModel order = new OrderModel(orderId, customerId, product, 1, "Pending");
            orderList.add(order);
            System.out.println("Order Placed: " + product + " | Order ID: " + orderId);
        }
        cart.clearCart();
    }

    public void trackOrders(String customerId) {
        System.out.println("Your Orders:");
        for (OrderModel order : orderList) {
            if (order.getCustomerId().equals(customerId)) {
                System.out.println("- " + order.getProductName() + " | Status: " + order.getStatus());
            }
        }
    }
}
