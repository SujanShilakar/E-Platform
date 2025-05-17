package controller.Product;

import controller.Login.LoginController;
import controller.Notification.NotificationController;
import model.Login.LoginRepo;
import model.Notification.NotificationRepo;
import model.Product.ProductModel;
import model.Product.ProductRepo;
import model.Sales.SalesRepo;
import view.Login.LoginView;
import view.Product.ProductView;
import view.Sales.SalesView;
import view.SellerHomePage.SellerHomePageView;

public class ProductController {
    private ProductRepo repo;
    private ProductView view;
    private String username;
    private final NotificationRepo notificationRepo;

    public ProductController(ProductRepo repo, ProductView view, String username, NotificationRepo notificationRepo,SellerHomePageView home) {
        this.repo = repo;
        this.view = view;
        this.username = username;
        this.notificationRepo = notificationRepo;

        refreshTable();

        // Add product
        view.addButton.addActionListener(e -> {
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
        });

        // Delete product
        view.deleteButton.addActionListener(e -> {
            try {
                int deleteId = Integer.parseInt(view.deleteIdField.getText());
                repo.deleteProductById(deleteId);
                view.removeProductFromTable(deleteId);
                view.showMessage("Product deleted!");
            } catch (NumberFormatException ex) {
                view.showMessage("Invalid ID!");
            }
        });

        // Update product
        view.updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(view.idField.getText().trim());

                // Get field values (may be empty)
                String name = view.nameField.getText().trim();
                String priceText = view.priceField.getText().trim();
                String stockText = view.stockField.getText().trim();

                boolean updated = false;

                for (ProductModel p : repo.getAllProducts()) {
                    if (p.getId() == id) {
                        // Only update non-empty fields
                        if (!name.isEmpty()) {
                            p.setName(name);
                        }

                        if (!priceText.isEmpty()) {
                            double price = Double.parseDouble(priceText);
                            p.setPrice(price);
                        }

                        if (!stockText.isEmpty()) {
                            int stock = Integer.parseInt(stockText);
                            p.setStock(stock);
                        }

                        updated = true;
                        break;
                    }
                }

                if (updated == true) {
                    view.showMessage("Product updated!");
                    refreshTable();
                    view.idField.setText("");
                    view.nameField.setText("");
                    view.priceField.setText("");
                    view.stockField.setText("");


                } else {
                    view.showMessage("Product not found!");
                }
            } catch (NumberFormatException ex) {
                view.showMessage("Invalid number format in price or stock.");
            } catch (Exception ex) {
                view.showMessage("Something went wrong: " + ex.getMessage());
            }
        });


        view.backButton.addActionListener(e -> {
            view.dispose(); // Close product window

            home.setVisible(true);

//            home.manageProductsButton.addActionListener(ev -> {
//                ProductView newProductView = new ProductView();
//                new ProductController(repo, newProductView, username, notificationRepo);
//            });

           /* home.salesReportButton.addActionListener(ev -> {
                SalesRepo salesRepo = new SalesRepo();
                new SalesView(salesRepo.getAllSales());
            });*/
         /*   home.notifyButton.addActionListener(ev -> {
                new NotificationController(username, notificationRepo);
            });*/

            home.logoutButton.addActionListener(ev -> {
                home.dispose();
                LoginView newLogin = new LoginView();
                new LoginController(new LoginRepo(), newLogin);
                newLogin.setVisible(true);

            });
            home.setVisible(true);
        });

    }

    private void refreshTable() {
        view.tableModel.setRowCount(0); // Clear table
        for (ProductModel p : repo.getAllProducts()) {
            view.addProductToTable(p.getId(), p.getName(), p.getPrice(), p.getStock());
        }
    }
}
