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
    public JTable productTable;
    public DefaultTableModel tableModel;

    public ProductView() {
        setTitle("Product Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Stock:"));
        inputPanel.add(stockField);
        inputPanel.add(addButton);

        // Delete panel
        JPanel deletePanel = new JPanel();
        deletePanel.add(new JLabel("Delete ID:"));
        deletePanel.add(deleteIdField);
        deletePanel.add(deleteButton);

        // Table
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Price", "Stock"}, 0);
        productTable = new JTable(tableModel);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(productTable), BorderLayout.CENTER);
        add(deletePanel, BorderLayout.SOUTH);

        setSize(800, 300);
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
