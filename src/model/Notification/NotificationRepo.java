package model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationRepo {
    private static final List<NotificationModel> notifications = new ArrayList<>();

    public void addNotification(NotificationModel n) {
        notifications.add(n);
    }

    public List<NotificationModel> getAllNotifications() {
        return new ArrayList<>(notifications); // return a copy
    }
}
