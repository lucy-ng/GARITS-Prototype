package Forms.StockControl;

import Database.Part;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class OrderParts {
    private JPanel mainPanel;
    private JLabel orderPartsLabel;
    private JTextPane fjordSuppliesAddress;
    private JScrollPane fjordSuppliesScrollPane;
    private JTextPane halfordsAddress;
    private JScrollPane halfordsScrollPane;
    private JButton orderButton;
    private JLabel orderDateLabel;
    private JTextField orderDate;
    private JTextField partName;
    private JLabel partNameLabel;
    private JLabel quantityLabel;
    private JTextField quantity;
    private JTable fjordSuppliesTable;
    private JTable halfordsTable;

    public OrderParts() {
        fjordSuppliesScrollPane.setPreferredSize(new Dimension(500,500));

        Vector fjordHeaders = new Vector();
        fjordHeaders.addElement("Part Name");
        fjordHeaders.addElement("Vehicle Type");
        fjordHeaders.addElement("Year");
        fjordHeaders.addElement("Price");
        Vector fjordRows = new Vector();
        fjordSuppliesTable = new JTable(fjordRows, fjordHeaders);
        DefaultTableModel fjordSuppliesModel = (DefaultTableModel) fjordSuppliesTable.getModel();
        fjordSuppliesScrollPane.setViewportView(fjordSuppliesTable);
        fjordSuppliesTable.setVisible(true);

        ArrayList<Part> partList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT * from SpareParts");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String manufacturer = rs.getString("manufacturer");
                String vehicleType = rs.getString("vehicleType");
                String year = rs.getString("year");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                int lowThreshold = rs.getInt("lowThreshold");

                Part part;
                part = new Part(name, manufacturer, vehicleType, year, price, quantity, lowThreshold);
                partList.add(part);

                Object[] row = new Object[4];
                for (int i = 0; i < partList.size(); i++) {
                    row[0] = part.getName();
                    row[1] = part.getVehicleType();
                    row[2] = part.getYear();
                    row[3] = part.getPrice();
                }
                fjordSuppliesModel.addRow(row);
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        halfordsScrollPane.setPreferredSize(new Dimension(500,500));

        Vector halfordsHeaders = new Vector();
        halfordsHeaders.addElement("Part Name");
        halfordsHeaders.addElement("Vehicle Type");
        halfordsHeaders.addElement("Year");
        halfordsHeaders.addElement("Price");
        Vector halfordsRows = new Vector();
        halfordsTable = new JTable(halfordsRows, halfordsHeaders);
        DefaultTableModel halfordsModel = (DefaultTableModel) halfordsTable.getModel();
        halfordsScrollPane.setViewportView(halfordsTable);
        halfordsTable.setVisible(true);

        ArrayList<Part> partsList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT * from SpareParts WHERE manufacturer <> 'Fjord Supplies'");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String manufacturer = rs.getString("manufacturer");
                String vehicleType = rs.getString("vehicleType");
                String year = rs.getString("year");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                int lowThreshold = rs.getInt("lowThreshold");

                Part parts;
                parts = new Part(name, manufacturer, vehicleType, year, price, quantity, lowThreshold);
                partsList.add(parts);

                Object[] rows = new Object[4];
                for (int i = 0; i < partsList.size(); i++) {
                    rows[0] = parts.getName();
                    rows[1] = parts.getVehicleType();
                    rows[2] = parts.getYear();
                    rows[3] = parts.getPrice();
                }
                halfordsModel.addRow(rows);
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String orderDateText = orderDate.getText();
                    String partNameText = partName.getText();
                    String quantityText = quantity.getText();

                    connection.setAutoCommit(false);
                    try (PreparedStatement stmt = connection.prepareStatement("SELECT partID from SpareParts WHERE name = ?")) {
                        stmt.setString(1, partNameText);
                        ResultSet rs = stmt.executeQuery();

                        while (rs.next()) {
                            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO SparePartsOrder (partOrderDate, amountOrdered, partID) VALUES (?,?,?)")) {
                                statement.setString(1, orderDateText);
                                statement.setString(2, quantityText);
                                statement.setString(3, rs.getString("partID"));
                                statement.executeUpdate();
                            }
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Part ordered!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
