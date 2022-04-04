package Forms.StockControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddParts {
    private JLabel addPartsTitle;
    private JTextField partName;
    private JTextField code;
    private JTextField manufacturer;
    private JTextField vehicleType;
    private JTextField year;
    private JTextField price;
    private JButton addButton;
    private JLabel partNameLabel;
    private JLabel codeLabel;
    private JLabel manufacturerLabel;
    private JLabel vehicleTypeLabel;
    private JLabel yearLabel;
    private JLabel priceLabel;
    private JPanel mainPanel;
    private JLabel quantityLabel;
    private JTextField quantity;
    private JLabel lowThresholdLabel;
    private JTextField lowThreshold;

    public AddParts() {
        addButton.addActionListener(new ActionListener() {
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

                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO SpareParts (name, code, manufacturer, vehicleType, year, price, quantity, lowThreshold) VALUES (?,?,?,?,?,?,?,?)")) {
                        statement.setString(1, partNameText);
                        statement.setString(2, codeText);
                        statement.setString(3, manufacturerText);
                        statement.setString(4, vehicleTypeText);
                        statement.setString(5, yearText);
                        statement.setString(6, priceText);
                        statement.setString(7, quantityText);
                        statement.setString(8, lowThresholdText);
                        statement.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Part added!");
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
