package model.Sales;

public class SalesModel {
    private final String productName;
    private final int quantitySold;
    private final double unitPrice;

    public SalesModel(String productName, int quantitySold, double unitPrice) {
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalRevenue() {
        return quantitySold * unitPrice;
    }
}
