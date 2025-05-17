package controller.Seller;

import controller.Notification.NotificationController;
import controller.Product.ProductController;
import model.Login.LoginRepo;
import model.Notification.NotificationRepo;
import model.Product.ProductRepo;
import model.Sales.SalesRepo;
import view.Login.LoginView;
import view.Product.ProductView;
import view.Sales.SalesView;
import view.SellerHomePage.SellerHomePageView;
import controller.Login.LoginController;

public class SellerController {
    private final String username;
    private final ProductRepo productRepo;
    private final NotificationRepo notificationRepo;
    private final LoginRepo loginRepo;

    public SellerController(String username, ProductRepo productRepo, NotificationRepo notificationRepo, LoginRepo loginRepo) {
        this.username = username;
        this.productRepo = productRepo;
        this.notificationRepo = notificationRepo;
        this.loginRepo = loginRepo;

        showDashboard();
    }

    private void showDashboard() {
        SellerHomePageView home = new SellerHomePageView(username);

        home.manageProductsButton.addActionListener(e -> {
            ProductView productView = new ProductView();
            new ProductController(productRepo, productView, username, notificationRepo, home); // passing home for back
        });

        home.salesReportButton.addActionListener(e -> {
            SalesRepo salesRepo = new SalesRepo();
            new SalesView(salesRepo.getAllSales());
        });

        home.notifyButton.addActionListener(e -> {
            new NotificationController(username, notificationRepo);
        });

        home.logoutButton.addActionListener(e -> {
            home.dispose();
            LoginView loginView = new LoginView();
            new LoginController(loginRepo, loginView);
            loginView.setVisible(true);
        });

        home.setVisible(true);
    }
}

