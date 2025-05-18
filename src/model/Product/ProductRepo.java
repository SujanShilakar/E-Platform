package model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    private List<ProductModel> products = new ArrayList<>();

    public ProductRepo() {

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
