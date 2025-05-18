package controller.Login;

import controller.Customer.CustomerController;
import controller.Notification.NotificationController;
import model.Login.LoginModel;
import model.Login.LoginRepo;
import view.Login.LoginView;
import view.Product.ProductView;
import model.Product.ProductRepo;
import controller.Product.ProductController;
import view.Customer.CustomerView;
import view.SellerHomePage.SellerHomePageView;
import view.Sales.SalesView;
import model.Sales.SalesRepo;
import model.Notification.NotificationRepo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginRepo userRepo;
    private LoginView loginView;

    public LoginController(LoginRepo userRepo, LoginView loginView) {
        this.userRepo = userRepo;
        this.loginView = loginView;

        loginView.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginView.usernameField.getText();
                String password = new String(loginView.passwordField.getPassword());
                String role = ((String) loginView.roleComboBox.getSelectedItem()).toLowerCase();
                LoginModel user = userRepo.authenticate(username, password, role);
                if (user != null) {
                    loginView.showMessage("Login Successful as " + role);
                    loginView.dispose();

                   /* if ("seller".equals(role)) {
                        // Open seller dashboard instead of product view
                        ProductRepo productRepo = new ProductRepo();
                        SellerHomePageView sellerHome = new SellerHomePageView(user.getUsername());
                        sellerHome.setVisible(true);

                        // Handle button clicks in Home Screen
                       sellerHome.manageProductsButton.addActionListener(ev -> {
                            ProductRepo productRepo = new ProductRepo();
                            ProductView productView = new ProductView();

                            new ProductController(productRepo, productView, username, notificationRepo);
                            sellerHome.dispose();
                        });
                        sellerHome.manageProductsButton.addActionListener(ev -> {
                            ProductView productView = new ProductView();
                            new ProductController(productRepo, productView, user.getUsername(), notificationRepo);
                        });
                        sellerHome.logoutButton.addActionListener(ev -> {
                            sellerHome.dispose(); // Close seller dashboard
                            LoginView newLogin = new LoginView();
                            new LoginController(userRepo, newLogin); // Rebuild login screen
                            newLogin.setVisible(true);
                        });
                        sellerHome.notifyButton.addActionListener(ev -> {
                            new NotificationController(user.getUsername(), notificationRepo);
                        });


                        sellerHome.salesReportButton.addActionListener(ev -> {
                            SalesRepo salesRepo = new SalesRepo();
                            new SalesView(salesRepo.getAllSales());
                        });
                    }*/
                    if ("seller".equals(role)) {
                        ProductRepo productRepo = new ProductRepo(); // ✅ shared repo
                        SellerHomePageView sellerHome = new SellerHomePageView(user.getUsername());
                        NotificationRepo notificationRepo = new NotificationRepo();


                        sellerHome.manageProductsButton.addActionListener(ev -> {
                            ProductView productView = new ProductView();
                            new ProductController(productRepo, productView, user.getUsername(), notificationRepo,sellerHome);
                        });

                        sellerHome.salesReportButton.addActionListener(ev -> {
                            SalesRepo salesRepo = new SalesRepo();
                            new SalesView(salesRepo.getAllSales());
                        });

                        sellerHome.notifyButton.addActionListener(ev -> {
                            new NotificationController(user.getUsername(), notificationRepo);
                        });

                        sellerHome.logoutButton.addActionListener(ev -> {
                            sellerHome.dispose();
                            LoginView newLogin = new LoginView();
                            new LoginController(userRepo, newLogin);
                            newLogin.setVisible(true);
                        });

                        sellerHome.setVisible(true); // ✅ only show after wiring buttons
                    }

                    if ("customer".equals(role)) {
                        NotificationRepo notificationRepo = new NotificationRepo();

                        CustomerView customerView = new CustomerView(notificationRepo);
                        CustomerController controller = new CustomerController(customerView);
                        String customerId = user.getUsername();
                        customerView.setVisible(true);

                        customerView.logoutButton.addActionListener(ev -> {
                            customerView.dispose(); // Close customer view
                            LoginView newLogin = new LoginView();
                            new LoginController(userRepo, newLogin); // Reopen login
                            newLogin.setVisible(true);
                        });



                            customerView.browseButton.addActionListener(a -> controller.browseProducts());
                            customerView.searchButton.addActionListener(a -> {
                                String keyword = customerView.searchField.getText();
                                controller.searchProduct(keyword);
                            });
                            customerView.addToCartButton.addActionListener(a -> {
                                String productName = customerView.cartInputField.getText();
                                controller.addToCart(productName);
                            });
                            customerView.viewCartButton.addActionListener(a -> controller.viewCart());
                            customerView.payButton.addActionListener(a -> controller.makePayment(customerId));
                            customerView.checkoutButton.addActionListener(a -> controller.checkout(customerId));
                            customerView.trackOrdersButton.addActionListener(a -> controller.trackOrders(customerId));
                            customerView.logoutButton.addActionListener(a -> customerView.dispose());
                            customerView.backButton.addActionListener(a -> customerView.dispose());

                    }

                } else {
                    loginView.showMessage("Invalid credentials or role.");
                }
            }
        });


    }
}
