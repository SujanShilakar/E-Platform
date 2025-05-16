package view.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductView extends JFrame {
    public JTextField idField = new JTextField(5);
    public JTextField nameField = new JTextField(10);
    public JTextField priceField = new JTextField(7);
    public JTextField stockField = new JTextField(5);
    public JTextField deleteIdField = new JTextField(5);
    public JButton addButton = new JButton("Add Product");
    public JButton deleteButton = new JButton("Delete Product");
    public JButton viewReportButton = new JButton("View Sales Report");
    public JButton updateButton = new JButton("Update Product");
    public JButton backButton = new JButton("Back");

    public JTable productTable;
    public DefaultTableModel tableModel;

    public ProductView() {
        setTitle("Product Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 400);
        setLocationRelativeTo(null); // ✅ Center on screen

        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Stock:"));
        inputPanel.add(stockField);
        inputPanel.add(addButton);

        // Table
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Price", "Stock"}, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        controlPanel.add(new JLabel("Delete ID:"));
        controlPanel.add(deleteIdField);
        controlPanel.add(deleteButton);
        controlPanel.add(updateButton);
        controlPanel.add(viewReportButton);
        controlPanel.add(backButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void addProductToTable(int id, String name, double price, int stock) {
        tableModel.addRow(new Object[]{id, name, price, stock});
    }

    public void removeProductFromTable(int id) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == id) {
                tableModel.removeRow(i);
                break;
            }
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
