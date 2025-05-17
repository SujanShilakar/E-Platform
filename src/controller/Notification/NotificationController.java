package controller.Notification;

import model.Notification.NotificationModel;
import model.Notification.NotificationRepo;
import view.Notification.NotificationView;
import view.SellerHomePage.SellerHomePageView;

public class NotificationController {
    public NotificationController(String sellerName, NotificationRepo repo) {
        NotificationView view = new NotificationView();
        view.sendButton.addActionListener(e -> {
            String message = view.messageArea.getText().trim();
            if (message.isEmpty()) {
                view.showMessage("Message cannot be empty.");
                return;
            }

            NotificationModel n = new NotificationModel(sellerName, message);
            repo.addNotification(n);
            view.showMessage("Notification sent to customers!");
            view.dispose();
        });

    }
}
