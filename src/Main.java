import controller.Customer.CustomerController;
import model.Notification.NotificationRepo;
import model.Login.LoginModel;
import model.Login.LoginRepo;
import view.Customer.CustomerView;
import view.Login.LoginView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginRepo loginRepo = new LoginRepo();
            LoginView loginView = new LoginView();

            // Show login window
            loginView.setVisible(true);

            loginView.loginButton.addActionListener(e -> {
                String username = loginView.usernameField.getText();
                String password = new String(loginView.passwordField.getPassword());
                String role = loginView.roleComboBox.getSelectedItem().toString().toLowerCase();

                LoginModel user = loginRepo.authenticate(username, password, role);

                if (user != null) {
                    loginView.dispose(); // Close login window

                    if (role.equals("customer")) {
                        // Open Customer Dashboard
                        CustomerController controller = new CustomerController();
                        CustomerView customerView = new CustomerView(new NotificationRepo());

                        // For now use the username as customer ID
                        String customerId = user.getUsername();

                        // Add button actions
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
                        customerView.checkoutButton.addActionListener(a -> controller.checkout(customerId));
                        customerView.trackOrdersButton.addActionListener(a -> controller.trackOrders(customerId));
                        customerView.logoutButton.addActionListener(a -> customerView.dispose());
                        customerView.backButton.addActionListener(a -> customerView.dispose());
                    }

                    // 👉 You can add Seller Dashboard logic here if needed
                    else if (role.equals("seller")) {
                        System.out.println("Seller login successful (you can link SellerView here).");
                    }

                } else {
                    loginView.showMessage("Invalid credentials. Try again.");
                }
            });
        });
    }
}
