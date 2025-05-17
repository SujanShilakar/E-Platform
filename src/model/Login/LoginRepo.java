package model.Login;

import java.util.ArrayList;
import java.util.List;

import model.Login.LoginModel;


public class LoginRepo {
    private List<LoginModel> users = new ArrayList<>();

    public LoginRepo() {
        // Hardcoded users for demo
        users.add(new LoginModel("sujan", "sujan", "seller"));
        users.add(new LoginModel("admin", "admin", "seller"));
        users.add(new LoginModel("muna", "muna", "customer"));
        users.add(new LoginModel("roshan", "roshan", "customer"));
    }

    public LoginModel authenticate(String username, String password, String role) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username)
                        && u.getPassword().equals(password)
                        && u.getRole().equalsIgnoreCase(role))
                .findFirst()
                .orElse(null);
    }
}

