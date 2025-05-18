import controller.Product.ProductController;
import model.Login.LoginRepo;
import controller.Login.LoginController;
import model.Notification.NotificationRepo;
import model.Product.*;
import view.Login.LoginView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ProductRepo sharedProductRepo = new ProductRepo();
            NotificationRepo sharedNotificationRepo = new NotificationRepo();
            LoginRepo userRepo = new LoginRepo();
            LoginView loginView = new LoginView();
            new LoginController(userRepo, loginView, sharedProductRepo, sharedNotificationRepo);
            loginView.setVisible(true);
        });
    }
}