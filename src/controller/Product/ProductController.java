package controller.Product;

import model.Product.Product;
import model.Product.ProductRepo;
import view.Product.ProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductController {
    private ProductRepo repo;
    private ProductView view;

    public ProductController(ProductRepo repo, ProductView view) {
        this.repo = repo;
        this.view = view;

        view.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(view.idField.getText());
                    String name = view.nameField.getText();
                    double price = Double.parseDouble(view.priceField.getText());
                    int stock = Integer.parseInt(view.stockField.getText());

                    Product product = new Product(id, name, price, stock);
                    repo.addProduct(product);
                    view.addProductToTable(id, name, price, stock);
                    view.showMessage("Product added!");
                } catch (NumberFormatException ex) {
                    view.showMessage("Invalid input!");
                }
            }
        });

        view.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int deleteId = Integer.parseInt(view.deleteIdField.getText());
                    repo.deleteProductById(deleteId);
                    view.removeProductFromTable(deleteId);
                    view.showMessage("Product deleted!");
                } catch (NumberFormatException ex) {
                    view.showMessage("Invalid ID!");
                }
            }
        });
    }
}
