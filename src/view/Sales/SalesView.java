package view.Sales;

import model.Sales.SalesModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SalesView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public SalesView(List<SalesModel> sales) {
        setTitle("Sales Report");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{"Product", "Qty Sold", "Unit Price", "Total Revenue"}, 0);
        table = new JTable(tableModel);

        for (SalesModel record : sales) {
            tableModel.addRow(new Object[]{
                    record.getProductName(),
                    record.getQuantitySold(),
                    record.getUnitPrice(),
                    record.getTotalRevenue()
            });
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }
}
