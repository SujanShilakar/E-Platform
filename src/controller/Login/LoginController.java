package controller.Login;

import model.Login.LoginModel;
import model.Login.LoginRepo;
import view.Login.LoginView;
import view.Product.ProductView;
import model.Product.ProductRepo;
import controller.Product.ProductController;
import view.Customer.CustomerView;

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
                String role = ((String)loginView.roleComboBox.getSelectedItem()).toLowerCase();

                LoginModel user = userRepo.authenticate(username, password, role);
                if (user != null) {
                    loginView.showMessage("Login Successful as " + role);
                    loginView.dispose();

                    if ("seller".equals(role)) {
                        ProductRepo productRepo = new ProductRepo();
                        ProductView productView = new ProductView();
                        new ProductController(productRepo, productView);
                    } else if ("customer".equals(role)) {
                        new CustomerView();
                    }

                } else {
                    loginView.showMessage("Invalid credentials or role.");
                }
            }
        });
    }
}
