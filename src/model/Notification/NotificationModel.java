package model.Notification;

public class NotificationModel {
    private String message;
    private String sender;

    public NotificationModel(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}
