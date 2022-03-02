package Forms.Reception;

import javax.swing.*;

public class Invoice {
    private JLabel invoiceLabel;
    private JPanel mainPanel;
    private JLabel customerNameLabel;
    private JTextField customerName;
    private JLabel vehicleRegistrationNumberLabel;
    private JTextField vehicleRegNo;
    private JLabel makeLabel;
    private JTextField make;
    private JLabel modelLabel;
    private JTextField model;
    private JLabel descriptionOfWorkLabel;
    private JList descriptionWork;
    private JTable itemsUsed;
    private JTable labour;
    private JButton printButton;
    private JLabel itemsUsedLabel;
    private JLabel labourLabel;
    private JTextPane address;
    private JTextPane customerAddressTextPane;

    public Invoice() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
