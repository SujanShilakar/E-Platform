package model.Product;

import model.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public void deleteProductById(int id) {
        products.removeIf(p -> p.getId() == id);
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
