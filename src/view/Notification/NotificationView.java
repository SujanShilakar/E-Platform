package view.Notification;

import javax.swing.*;
import java.awt.*;

public class NotificationView extends JFrame {
    public JTextArea messageArea = new JTextArea(5, 30);
    public JButton sendButton = new JButton("Send");

    public NotificationView() {
        setTitle("Send Notification");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        JPanel center = new JPanel();
        center.setBorder(BorderFactory.createTitledBorder("Message"));
        center.add(new JScrollPane(messageArea));

        JPanel bottom = new JPanel();
        bottom.add(sendButton);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
