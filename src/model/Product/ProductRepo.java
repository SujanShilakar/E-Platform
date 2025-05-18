package model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    private List<ProductModel> products = new ArrayList<>();

    public ProductRepo() {
        //
        products.add(new ProductModel(1, "Laptop", 1000.00, 10));
        products.add(new ProductModel(2, "Phone", 700.00, 15));
        products.add(new ProductModel(3, "Headphones", 150.00, 20));
    }

    public void addProduct(ProductModel p) {
        products.add(p);
    }

    public void deleteProductById(int id) {
        products.removeIf(p -> p.getId() == id);
    }

    public List<ProductModel> getAllProducts() {
        return products;
    }
}
