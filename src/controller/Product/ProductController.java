package controller.Product;
import model.Product.ProductModel;
import model.Product.ProductRepo;
import view.Product.ProductView;
import view.Sales.SalesView;
import model.Sales.SalesRepo;
import  model.Sales.SalesModel;
import view.SellerHomePage.SellerHomePageView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

                    ProductModel product = new ProductModel(id, name, price, stock);
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
        view.viewReportButton.addActionListener(e -> {
            SalesRepo salesRepo = new SalesRepo();
            List<SalesModel> records = salesRepo.getAllSales();
            new SalesView(records); // open new window
        });
        view.backButton.addActionListener(e -> {
            view.dispose(); // close product window


            SellerHomePageView home = new SellerHomePageView("Seller");
            home.setVisible(true);

            home.manageProductsButton.addActionListener(ev -> {
                ProductView newProductView = new ProductView();
                new ProductController(repo, newProductView);
            });

            home.salesReportButton.addActionListener(ev -> {
                SalesRepo salesRepo = new SalesRepo();
                new SalesView(salesRepo.getAllSales());
            });
        });



        view.updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(view.idField.getText());
                String name = view.nameField.getText();
                double price = Double.parseDouble(view.priceField.getText());
                int stock = Integer.parseInt(view.stockField.getText());

                boolean updated = false;
                for (ProductModel p : repo.getAllProducts()) {
                    if (p.getId() == id) {
                        // Update fields
                        p.setStock(stock);
                        // You can also add setters for name and price if you want to allow updating them
                        // p.setName(name); p.setPrice(price);
                        updated = true;
                        break;
                    }
                }

                if (updated) {
                    view.showMessage("Product updated!");
                    refreshTable();
                } else {
                    view.showMessage("Product not found!");
                }
            } catch (NumberFormatException ex) {
                view.showMessage("Invalid input!");
            }
        });

    }
    private void refreshTable() {
        view.tableModel.setRowCount(0); // Clear table
        for (model.Product.ProductModel p : repo.getAllProducts()) {
            view.addProductToTable(p.getId(), p.getName(), p.getPrice(), p.getStock());
        }
    }
}
