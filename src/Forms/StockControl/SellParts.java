package Forms.StockControl;

import Database.Part;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class SellParts {

    private JPanel mainPanel;
    private JLabel orderPartsLabel;
    private JScrollPane partsScrollPane;
    private JTextField searchParts;
    private JButton searchButton;
    private JLabel partNameLabel;
    private JTextField partName;
    private JLabel amountLabel;
    private JTextField amount;
    private JLabel priceLabel;
    private JTextField price;
    private JLabel totalPriceLabel;
    private JTextField totalPrice;
    private JLabel paymentDateLabel;
    private JTextField paymentDate;
    private JLabel companyNameLabel;
    private JTextField companyName;
    private JLabel cardTypeLabel;
    private JTextField cardType;
    private JLabel cardNumberLabel;
    private JTextField cardNumber;
    private JLabel expiryDateLabel;
    private JTextField expiryDate;
    private JLabel searchForSparePartsLabel;
    private JButton sellButton;
    private JTable partsTable;

    public SellParts() {
        partsScrollPane.setPreferredSize(new Dimension(500,500));

        Vector partHeaders = new Vector();
        partHeaders.addElement("Part Name");
        partHeaders.addElement("Manufacturer");
        partHeaders.addElement("Vehicle Type");
        partHeaders.addElement("Year");
        partHeaders.addElement("Price");
        partHeaders.addElement("Quantity");
        partHeaders.addElement("Low Threshold");
        Vector partRows = new Vector();
        partsTable = new JTable(partRows, partHeaders);
        DefaultTableModel partsTableModel = (DefaultTableModel) partsTable.getModel();
        partsScrollPane.setViewportView(partsTable);
        partsTable.setVisible(true);

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
                partsTableModel.addRow(row);
                fillResults();
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

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
                        partsScrollPane.setViewportView(searchResults);
                        searchResults.setVisible(true);
                    }
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String companyNameText = companyName.getText();
                    String cardTypeText = cardType.getText();
                    String cardNumberText = cardNumber.getText();
                    String expiryDateText = expiryDate.getText();
                    String totalPriceText = totalPrice.getText();
                    String paymentDateText = paymentDate.getText();

                    String partNameText = partName.getText();
                    String amountText = amount.getText();

                    connection.setAutoCommit(false);

                    PreparedStatement stmt = connection.prepareStatement("SELECT partID from SpareParts WHERE name = ?");
                    stmt.setString(1, partNameText);
                    ResultSet resultSet = stmt.executeQuery();

                    while (resultSet.next()) {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO Payments (companyName, cardType, cardNumber, expiryDate, amount, paymentDate, partID) VALUES (?,?,?,?,?,?,?)");
                        statement.setString(1, companyNameText);
                        statement.setString(2, cardTypeText);
                        statement.setString(3, cardNumberText);
                        statement.setString(4, expiryDateText);
                        statement.setString(5, totalPriceText);
                        statement.setString(6, paymentDateText);
                        statement.setInt(7, resultSet.getInt("partID"));
                        statement.executeUpdate();

                        PreparedStatement selectStmt = connection.prepareStatement("SELECT quantity FROM SpareParts WHERE name = ?");
                        selectStmt.setString(1, partNameText);
                        ResultSet rs = selectStmt.executeQuery();

                        while (rs.next()) {
                            PreparedStatement updateStmt = connection.prepareStatement("UPDATE SpareParts SET quantity = ? WHERE name = ?");
                            int sum = rs.getInt("quantity") - Integer.parseInt(amountText);
                            updateStmt.setInt(1, sum);
                            updateStmt.setString(2, partNameText);
                            updateStmt.executeUpdate();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Part sold!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void fillResults() {
        partsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = partsTable.getSelectedRow();
                partName.setText(partsTable.getModel().getValueAt(selectedRow, 0).toString());
                price.setText(partsTable.getModel().getValueAt(selectedRow, 4).toString());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
