package view.SellerHomePage;

import javax.swing.*;
import java.awt.*;

public class SellerHomePageView extends JFrame {
    public JButton manageProductsButton = new JButton("Manage Products");
    public JButton salesReportButton = new JButton("View Sales Report");
    public JButton notifyButton = new JButton("Send Notification");
    public JButton logoutButton = new JButton("Logout");


    public SellerHomePageView(String username) {
        setTitle("Seller Dashboard");
        setSize(400, 200);
        setLocationRelativeTo(null); // Center screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.add(manageProductsButton);
        buttonPanel.add(salesReportButton);
        buttonPanel.add(notifyButton);
        buttonPanel.add(logoutButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));


        setLayout(new BorderLayout());
        add(welcomeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
}
