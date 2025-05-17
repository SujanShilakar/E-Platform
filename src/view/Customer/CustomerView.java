package view.Customer;

import model.Notification.NotificationRepo;
import model.Notification.NotificationModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerView extends JFrame {
    public JButton logoutButton = new JButton("Logout");
    public JButton backButton = new JButton("Back");

    public CustomerView(NotificationRepo repo) {
        setTitle("Customer Dashboard");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

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

        // Buttons panel (Logout and Back)
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        controlPanel.add(logoutButton);
        controlPanel.add(backButton);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
