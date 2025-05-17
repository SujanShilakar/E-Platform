package view.Customer;

import model.Notification.NotificationRepo;
import model.Notification.NotificationModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerView extends JFrame {

    public JButton logoutButton = new JButton("Logout");
    public JButton backButton = new JButton("Back");

    public JButton browseButton = new JButton("Browse Products");
    public JButton searchButton = new JButton("Search Product");
    public JButton addToCartButton = new JButton("Add to Cart");
    public JButton viewCartButton = new JButton("View Cart");
    public JButton checkoutButton = new JButton("Checkout");
    public JButton trackOrdersButton = new JButton("Track Orders");

    public JTextField searchField = new JTextField(15);
    public JTextField cartInputField = new JTextField(15);

    public CustomerView(NotificationRepo repo) {
        setTitle("Customer Dashboard");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Notifications from Sellers");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        // Notifications area
        JTextArea area = new JTextArea(10, 30);
        area.setEditable(false);
        List<NotificationModel> notifications = repo.getAllNotifications();
        if (notifications.isEmpty()) {
            area.setText("No notifications yet.");
        } else {
            for (NotificationModel n : notifications) {
                area.append("From " + n.getSender() + ": " + n.getMessage() + "\n\n");
            }
        }
        add(new JScrollPane(area), BorderLayout.CENTER);

        // Action Panel for Customer Features
        JPanel actionPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Customer Actions"));

        actionPanel.add(browseButton);
        actionPanel.add(new JLabel("Search Product:"));
        actionPanel.add(searchField);
        actionPanel.add(searchButton);
        actionPanel.add(new JLabel("Product to Add to Cart:"));
        actionPanel.add(cartInputField);
        actionPanel.add(addToCartButton);
        actionPanel.add(viewCartButton);
        actionPanel.add(checkoutButton);
        actionPanel.add(trackOrdersButton);

        add(actionPanel, BorderLayout.EAST);

        // Buttons panel (Logout and Back)
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        controlPanel.add(logoutButton);
        controlPanel.add(backButton);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
