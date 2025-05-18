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

            loginView.setVisible(true);

            loginView.loginButton.addActionListener(e -> {
                String username = loginView.usernameField.getText();
                String password = new String(loginView.passwordField.getPassword());
                String role = loginView.roleComboBox.getSelectedItem().toString().toLowerCase();

                LoginModel user = loginRepo.authenticate(username, password, role);

                if (user != null) {
                    loginView.dispose();

                    if (role.equals("customer")) {
                        NotificationRepo notificationRepo = new NotificationRepo();
                        CustomerView customerView = new CustomerView(notificationRepo);
                        CustomerController controller = new CustomerController(customerView);
                        String customerId = user.getUsername();

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
                    } else {
                        System.out.println("Seller login successful (GUI pending).\n");
                    }
                } else {
                    loginView.showMessage("Invalid credentials. Try again.");
                }
            });
        });
    }
}
