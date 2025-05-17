package model.Customer;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<String> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addProduct(String productName) {
        products.add(productName);
    }

    public void removeProduct(String productName) {
        products.remove(productName);
    }

    public List<String> getProducts() {
        return products;
    }

    public void clearCart() {
        products.clear();
    }
}
