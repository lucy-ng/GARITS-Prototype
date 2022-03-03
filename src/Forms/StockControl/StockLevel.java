package Forms.StockControl;

import javax.swing.*;

public class StockLevel {
    private JPanel mainPanel;
    private JLabel stockLevelTitle;
    private JTextPane address;
    private JTable tblStockLevel;
    private JTextField reportPeriod1;
    private JTextField reportDate;
    private JButton printButton;
    private JLabel tableOfStockLabel;
    private JLabel reportPeriodLabel;
    private JLabel reportDateLabel;
    private JTextField reportPeriod2;

    public StockLevel() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
