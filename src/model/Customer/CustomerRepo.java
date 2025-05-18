package model.Customer;


import model.Customer.CustomerModel;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    private static List<CustomerModel> customerList = new ArrayList<>();

    // Register a new customer
    public void addCustomer(CustomerModel customer) {
        customerList.add(customer);
    }

    // Get all customers
    public List<CustomerModel> getAllCustomers() {
        return customerList;
    }

    // Find customer by email
    public CustomerModel findByEmail(String email) {
        for (CustomerModel c : customerList) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    // Validate login
    public boolean validateLogin(String email, String password) {
        for (CustomerModel c : customerList) {
            if (c.getEmail().equalsIgnoreCase(email) && c.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
