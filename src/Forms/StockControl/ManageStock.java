package Forms.StockControl;

import Database.Part;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ManageStock {
    private JLabel manageStockTitle;
    private JPanel mainPanel;
    private JLabel searchForSparePartsLabel;
    private JTextField searchParts;
    private JButton searchButton;
    private JLabel tableOfSparePartsLabel;
    private JScrollPane scrollPane;
    private JLabel partNameLabel;
    private JTextField partName;
    private JButton updateStockButton;
    private JLabel quantityLabel;
    private JTextField quantity;
    private JLabel lowThresholdLabel;
    private JTextField lowThreshold;

    public ManageStock() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchParts.getText();
                ArrayList<Part> partList = new ArrayList<>();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT name, code, manufacturer, vehicleType, year, price, quantity, lowThreshold from SpareParts where name = ?");
                    statement.setString(1, text);
                    ResultSet rs = statement.executeQuery();
                    Part part;

                    while (rs.next()) {
                        String name = rs.getString("name");
                        String code = rs.getString("code");
                        String manufacturer = rs.getString("manufacturer");
                        String vehicleType = rs.getString("vehicleType");
                        String year = rs.getString("year");
                        String price = rs.getString("price");
                        int quantity = rs.getInt("quantity");
                        int lowThreshold = rs.getInt("lowThreshold");

                        part = new Part(name, code, manufacturer, vehicleType, year, price, quantity, lowThreshold);
                        partList.add(part);

                        Object[] row = new Object[8];
                        for (int i = 0; i < partList.size(); i++) {
                            row[0] = part.getName();
                            row[1] = part.getCode();
                            row[2] = part.getManufacturer();
                            row[3] = part.getVehicleType();
                            row[4] = part.getYear();
                            row[5] = part.getPrice();
                            row[6] = part.getQuantity();
                            row[7] = part.getLowThreshold();
                        }

                        Object[][] data =  {row};
                        String[] columnNames = {"Part Name", "Code", "Manufacturer", "Vehicle Type", "Year", "Price", "Quantity", "Low Threshold"};
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

        updateStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String partNameText = partName.getText();
                    String quantityText = quantity.getText();
                    String lowThresholdText = lowThreshold.getText();

                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("UPDATE SpareParts SET quantity = ?, lowThreshold = ? WHERE name = ?")) {
                        statement.setString(1, quantityText);
                        statement.setString(2, lowThresholdText);
                        statement.setString(3, partNameText);
                        statement.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Stock updated!");
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
