package model.Sales;

public class SalesModel {
    private String productName;
    private int quantitySold;
    private double unitPrice;

    public SalesModel(String productName, int quantitySold, double unitPrice) {
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
    }

    public String getProductName() { return productName; }
    public int getQuantitySold() { return quantitySold; }
    public double getUnitPrice() { return unitPrice; }

    public double getTotalRevenue() {
        return quantitySold * unitPrice;
    }
}
