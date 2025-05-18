package controller.Login;

import controller.Customer.CustomerController;
import controller.Notification.NotificationController;
import controller.Product.ProductController;
import model.Login.LoginModel;
import model.Login.LoginRepo;
import model.Notification.NotificationRepo;
import model.Product.ProductRepo;
import model.Sales.SalesRepo;
import view.Customer.CustomerView;
import view.Login.LoginView;
import view.Product.ProductView;
import view.Sales.SalesView;
import view.SellerHomePage.SellerHomePageView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginRepo userRepo;
    private LoginView loginView;
    private ProductRepo productRepo;
    private NotificationRepo notificationRepo;

    public LoginController(LoginRepo userRepo, LoginView loginView, ProductRepo productRepo, NotificationRepo notificationRepo) {
        this.userRepo = userRepo;
        this.loginView = loginView;
        this.productRepo = productRepo;
        this.notificationRepo = notificationRepo;

        loginView.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginView.usernameField.getText();
                String password = new String(loginView.passwordField.getPassword());
                String role = ((String) loginView.roleComboBox.getSelectedItem()).toLowerCase();

                LoginModel user = userRepo.authenticate(username, password, role);

                if (user != null) {
                    loginView.showMessage("✅ Login Successful as " + role);
                    loginView.dispose();

                    if ("seller".equals(role)) {
                        launchSellerDashboard(username);
                    } else if ("customer".equals(role)) {
                        launchCustomerDashboard(username);
                    }

                } else {
                    loginView.showMessage("❌ Invalid credentials or role.");
                }
            }
        });
    }

    private void launchSellerDashboard(String username) {
        SellerHomePageView sellerHome = new SellerHomePageView(username);

        // Show seller dashboard after wiring events
        sellerHome.setVisible(true);

        // Manage Products
        sellerHome.manageProductsButton.addActionListener(ev -> {
            ProductView productView = new ProductView();
            new ProductController(productRepo, productView, username, notificationRepo, sellerHome);
        });

        // Sales Report
        sellerHome.salesReportButton.addActionListener(ev -> {
            SalesRepo salesRepo = new SalesRepo();
            new SalesView(salesRepo.getAllSales());
        });

        // Notifications
        sellerHome.notifyButton.addActionListener(ev -> {
            new NotificationController(username, notificationRepo);
        });

        // Logout
        sellerHome.logoutButton.addActionListener(ev -> {
            sellerHome.dispose();
            showLoginScreen();
        });
    }

    private void launchCustomerDashboard(String customerId) {
        CustomerView customerView = new CustomerView(notificationRepo);
        CustomerController controller = new CustomerController(customerView, productRepo, notificationRepo);
        customerView.setVisible(true);

        // Navigation
        customerView.logoutButton.addActionListener(ev -> {
            customerView.dispose();
            showLoginScreen();
        });

        customerView.backButton.addActionListener(ev -> {
            customerView.dispose();
            showLoginScreen();
        });

        // Functional buttons
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
    }

    private void showLoginScreen() {
        LoginView newLogin = new LoginView();
        new LoginController(userRepo, newLogin, productRepo, notificationRepo);
        newLogin.setVisible(true);
    }
}
