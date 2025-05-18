package view.Customer;

import model.Notification.NotificationRepo;
import model.Notification.NotificationModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerView extends JFrame {
    public JButton browseButton = new JButton("Browse Products");
    public JButton searchButton = new JButton("Search Product");
    public JButton addToCartButton = new JButton("Add to Cart");
    public JButton viewCartButton = new JButton("View Cart");
    public JButton payButton = new JButton("Make Payment");
    public JButton checkoutButton = new JButton("Checkout");
    public JButton trackOrdersButton = new JButton("Track Orders");
    public JButton logoutButton = new JButton("Logout");
    public JButton backButton = new JButton("Back");
    


    public JTextField searchField = new JTextField(15);
    public JTextField cartInputField = new JTextField(15);
    public JTextArea displayArea = new JTextArea(10, 30);

    public CustomerView(NotificationRepo repo) {
        setTitle("VIT SUPERMARKET");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Header
        JLabel title = new JLabel("Customer Dashboard");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        // Notifications area
        displayArea.setEditable(false);
        List<NotificationModel> notifications = repo.getAllNotifications();
        if (notifications.isEmpty()) {
            displayArea.setText("No notifications yet.\n");
        } else {
            for (NotificationModel n : notifications) {
                displayArea.append("From " + n.getSender() + ": " + n.getMessage() + "\n\n");
            }
        }
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Right panel with customer actions
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Customer Actions"));

        controlPanel.add(browseButton);
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(new JLabel("Search Product:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(new JLabel("Product to Add to Cart:"));
        controlPanel.add(cartInputField);
        controlPanel.add(addToCartButton);
        controlPanel.add(viewCartButton);
         controlPanel.add(payButton);
        controlPanel.add(checkoutButton);
        controlPanel.add(trackOrdersButton);
       


        add(controlPanel, BorderLayout.EAST);

        // Bottom buttons (Logout & Back)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(logoutButton);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
