package Forms.StockControl;

import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class SearchParts {
    private JTextField searchParts;
    private JButton searchButton;
    private JPanel mainPanel;
    private JLabel searchPartsTitle;
    private JLabel searchForSparePartsLabel;
    private JLabel tableOfSparePartsLabel;
    private JScrollPane scrollPane;

    public SearchParts() {
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
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
