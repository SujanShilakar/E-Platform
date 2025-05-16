import controller.Product.ProductController;
import model.Login.LoginRepo;
import controller.Login.LoginController;
import model.Product.*;
import view.Login.LoginView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginRepo userRepo = new LoginRepo();
            LoginView loginView = new LoginView();
            new LoginController(userRepo, loginView);
            loginView.setVisible(true);
        });
    }
}
