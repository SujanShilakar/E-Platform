package view.Login;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public JTextField usernameField = new JTextField(15);
    public JPasswordField passwordField = new JPasswordField(15);
    public JComboBox<String> roleComboBox = new JComboBox<>(new String[] {"Seller", "Customer"});
    public JButton loginButton = new JButton("Login");

    public LoginView() {
        setTitle("E-Retail Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 220);
        setResizable(false);
        setLocationRelativeTo(null); // ✅ Center on screen

        // Layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Role
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        panel.add(roleComboBox, gbc);

        // Login button
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        add(panel);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
