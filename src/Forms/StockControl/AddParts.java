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

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
