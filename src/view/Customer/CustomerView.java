package view.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerView extends JFrame {
    public CustomerView() {
        setTitle("Customer Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Welcome, Customer! Here you can browse products.");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        add(label, BorderLayout.CENTER);
        setVisible(true);
    }
}
