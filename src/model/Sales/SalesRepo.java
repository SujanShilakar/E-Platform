package model.Sales;

import java.util.ArrayList;
import java.util.List;

public class SalesRepo {
    public List<SalesModel> getAllSales() {
        List<SalesModel> sales = new ArrayList<>();

        // Simulated data
        sales.add(new SalesModel("Shirt", 10, 25.00));
        sales.add(new SalesModel("Jeans", 5, 40.00));
        sales.add(new SalesModel("Jacket", 3, 60.00));

        return sales;
    }
}
