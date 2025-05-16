package controller.Login;

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

                    if ("seller".equals(role)) {
                        // Open seller dashboard instead of product view
                        SellerHomePageView sellerHome = new SellerHomePageView(user.getUsername());
                        sellerHome.setVisible(true);

                        // Handle button clicks in Home Screen
                        sellerHome.manageProductsButton.addActionListener(ev -> {
                            ProductRepo productRepo = new ProductRepo();
                            ProductView productView = new ProductView();
                            NotificationRepo notificationRepo = new NotificationRepo();
                            new ProductController(productRepo, productView , username , notificationRepo);
                            sellerHome.dispose();
                        });
                        sellerHome.logoutButton.addActionListener(ev -> {
                            sellerHome.dispose(); // Close seller dashboard
                            LoginView newLogin = new LoginView();
                            new LoginController(userRepo, newLogin); // Rebuild login screen
                            newLogin.setVisible(true);
                        });


                        sellerHome.salesReportButton.addActionListener(ev -> {
                            SalesRepo salesRepo = new SalesRepo();
                            new SalesView(salesRepo.getAllSales());
                        });
                    }
                    if ("customer".equals(role)) {
                        NotificationRepo notificationRepo = new NotificationRepo(); // create shared notification store
                        CustomerView customerView = new CustomerView(notificationRepo);
                        customerView.setVisible(true);

                        customerView.logoutButton.addActionListener(ev -> {
                            customerView.dispose(); // Close customer view
                            LoginView newLogin = new LoginView();
                            new LoginController(userRepo, newLogin); // Reopen login
                            newLogin.setVisible(true);
                        });
                    }

                } else {
                    loginView.showMessage("Invalid credentials or role.");
                }
            }
        });



    }
}
