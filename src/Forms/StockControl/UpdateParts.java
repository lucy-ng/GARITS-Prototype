package Forms.StockControl;

import Database.Part;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class UpdateParts {
    private JLabel updatePartsTitle;
    private JPanel mainPanel;
    private JLabel partNameLabel;
    private JTextField partName;
    private JLabel searchForSparePartsLabel;
    private JTextField searchParts;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JLabel codeLabel;
    private JTextField code;
    private JLabel manufacturerLabel;
    private JTextField manufacturer;
    private JLabel vehicleTypeLabel;
    private JTextField vehicleType;
    private JLabel yearLabel;
    private JTextField year;
    private JLabel priceLabel;
    private JTextField price;
    private JLabel quantityLabel;
    private JTextField quantity;
    private JButton updateButton;
    private JLabel lowThresholdLabel;
    private JTextField lowThreshold;
    private JLabel oldPartNameLabel;
    private JTextField oldPartName;

    public UpdateParts() {
        scrollPane.setPreferredSize(new Dimension(500,500));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchParts.getText();
                ArrayList<Part> partList = new ArrayList<>();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT name, manufacturer, vehicleType, year, price, quantity, lowThreshold from SpareParts where name = ?");
                    statement.setString(1, text);
                    ResultSet rs = statement.executeQuery();
                    Part part;

                    while (rs.next()) {
                        String name = rs.getString("name");
                        String manufacturer = rs.getString("manufacturer");
                        String vehicleType = rs.getString("vehicleType");
                        String year = rs.getString("year");
                        BigDecimal price = rs.getBigDecimal("price");
                        int quantity = rs.getInt("quantity");
                        int lowThreshold = rs.getInt("lowThreshold");

                        part = new Part(name, manufacturer, vehicleType, year, price, quantity, lowThreshold);
                        partList.add(part);

                        Object[] row = new Object[7];
                        for (int i = 0; i < partList.size(); i++) {
                            row[0] = part.getName();
                            row[1] = part.getManufacturer();
                            row[2] = part.getVehicleType();
                            row[3] = part.getYear();
                            row[4] = part.getPrice();
                            row[5] = part.getQuantity();
                            row[6] = part.getLowThreshold();
                        }

                        Object[][] data =  {row};
                        String[] columnNames = {"Part Name", "Manufacturer", "Vehicle Type", "Year", "Price", "Quantity", "Low Threshold"};
                        JTable searchResults = new JTable(data, columnNames);
                        scrollPane.setViewportView(searchResults);
                        searchResults.setVisible(true);
                    }
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();;
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String partNameText = partName.getText();
                    String codeText = code.getText();
                    String manufacturerText = manufacturer.getText();
                    String vehicleTypeText = vehicleType.getText();
                    String yearText = year.getText();
                    String priceText = price.getText();
                    String quantityText = quantity.getText();
                    String lowThresholdText = lowThreshold.getText();
                    String oldPartNameText = oldPartName.getText();

                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("UPDATE SpareParts SET name = ?, code = ?, manufacturer = ?, vehicleType = ?, year = ?, price = ?, quantity = ?, lowThreshold = ? WHERE name = ?")) {
                        statement.setString(1, partNameText);
                        statement.setString(2, codeText);
                        statement.setString(3, manufacturerText);
                        statement.setString(4, vehicleTypeText);
                        statement.setString(5, yearText);
                        statement.setString(6, priceText);
                        statement.setString(7, quantityText);
                        statement.setString(8, lowThresholdText);
                        statement.setString(9, oldPartNameText);
                        statement.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Part updated!");
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
